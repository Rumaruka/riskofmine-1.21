package com.rumaruka.riskofmine.common.data;

import com.rumaruka.riskofmine.api.SkillData;
import com.rumaruka.riskofmine.init.ROMData;
import com.rumaruka.riskofmine.ntw.SyncSkills;
import com.rumaruka.riskofmine.ntw.packets.PacketSyncSkillData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.zeith.hammerlib.event.player.PlayerLoadedInEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

@EventBusSubscriber
public class PlayerDataManager {
    private static final Map<UUID, SkillData> DATAS = new HashMap<>();
    private static final ThreadLocal<Player> LPLAYER = ThreadLocal.withInitial(() -> null);

    public static void handleDataSafely(Player player, Consumer<SkillData> acceptor)
    {
        SkillData psd = getDataFor(player);
        if(psd != null) acceptor.accept(psd);
    }

    public static <T> T handleDataSafely(Player player, Function<SkillData, T> acceptor, T defaultValue)
    {
        SkillData psd = getDataFor(player);
        if(psd != null)
            return acceptor.apply(psd);
        return defaultValue;
    }

    public static SkillData getDataFor(Player player)
    {
        if(player == null || player instanceof FakePlayer) return null;
        if(player.level().isClientSide)
        {
            //TODO: Init Data
            if(player.isLocalPlayer()) return SyncSkills.getData();
            return player.getData(ROMData.SKILL_DATA);
        }

        LPLAYER.set(player);
        SkillData data = getDataFor(player.getGameProfile().getId());

        // Update player reference -- keep it up-to-date
        if(data != null && data.getPlayer() != player)
            DATAS.put(player.getGameProfile().getId(), data = SkillData.deserialize(player, data.serializeNBT(player.registryAccess())));

        return data != null ? data.toCurrent(player) : null;
    }

    private static SkillData getDataFor(UUID id)
    {
        if(id == null) return null;
        if(DATAS.containsKey(id)) return DATAS.get(id);

        Player epl = LPLAYER.get();

        // This should never happen, but if it does, we try to reconstruct player's data from the persistent tag in case it's there. (remote players, maybe?!)
        if(epl instanceof ServerPlayer mp)
        {
            var data = mp.getData(ROMData.SKILL_DATA);
            DATAS.put(mp.getUUID(), data);
        }

        return null;
    }

    ///////////////////// DATA LIFECYCLE EVENTS /////////////////////

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Pre e)
    {
        PlayerDataManager.handleDataSafely(e.getEntity(), SkillData::handleTick);
    }

    @SubscribeEvent
    public static void playerClone(PlayerEvent.Clone e)
    {
        if(e.isWasDeath()) return;
        var oldPlayer = e.getOriginal();
        var newPlayer = e.getEntity();

        var od = getDataFor(oldPlayer);
        if(od == null) return;

        od.player = newPlayer;

        var nd = getDataFor(newPlayer);

        if(od == nd) return;

        nd.deserializeNBT(newPlayer.registryAccess(), od.serializeNBT(oldPlayer.registryAccess()));
    }

    @SubscribeEvent
    public static void playerLoadedIn(PlayerLoadedInEvent e)
    {
        PlayerDataManager.handleDataSafely(e.getEntity(), SkillData::sync);
    }

    @SubscribeEvent
    public static void respawn(PlayerEvent.PlayerRespawnEvent e)
    {
        if(e.getEntity() instanceof ServerPlayer mp)
            PacketSyncSkillData.sync(mp);
    }

    @SubscribeEvent
    public static void serverTick(ServerTickEvent.Post e)
    {
        MinecraftServer mcs = e.getServer();
        PlayerDataManager.DATAS.keySet().removeIf(uuid ->
        {
            var mp = mcs.getPlayerList().getPlayer(uuid);
            if(mp == null) return true;
            SkillData data = PlayerDataManager.DATAS.get(uuid);
            data.player = mp;
            return false;
        });
    }

    @SubscribeEvent
    public static void loadPlayerFromFile(PlayerEvent.LoadFromFile e)
    {
        CompoundTag nbt = null;

        try
        {
            File mainFile = e.getPlayerFile(".rom_skills.dat");

            if(mainFile.isFile())
                nbt = NbtIo.readCompressed(new FileInputStream(mainFile), NbtAccounter.unlimitedHeap());
        } catch(Exception error)
        {
            error.printStackTrace();

            File oldFile = e.getPlayerFile(".rom_skills.dat_old");
            if(oldFile.isFile())
            {
                try
                {
                    nbt = NbtIo.readCompressed(new FileInputStream(oldFile), NbtAccounter.unlimitedHeap());
                } catch(Exception error2)
                {
                    error2.printStackTrace();
                }
            }
        }

        if(nbt != null) DATAS.put(UUID.fromString(e.getPlayerUUID()), SkillData.deserialize(e.getEntity(), nbt));
        else DATAS.put(UUID.fromString(e.getPlayerUUID()), new SkillData(e.getEntity()));
    }

    @SubscribeEvent
    public static void savePlayerToFile(PlayerEvent.SaveToFile e)
    {
        SkillData data = getDataFor(e.getEntity());
        if(data == null)
            return;
        try
        {
            CompoundTag nbt = data.serializeNBT(e.getEntity().registryAccess());
            File tmp = e.getPlayerFile(".rom_skills.dat.tmp");
            File main = e.getPlayerFile(".rom_skills.dat");
            File mainOld = e.getPlayerFile(".rom_skills.dat_old");
            NbtIo.writeCompressed(nbt, new FileOutputStream(tmp));
            if(mainOld.isFile()) mainOld.delete();
            if(main.exists()) main.renameTo(mainOld);
            tmp.renameTo(main);
        } catch(Exception var5)
        {
            var5.printStackTrace();
        }
    }
}
