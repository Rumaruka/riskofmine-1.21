
//TODO: Skill Data class Development

package com.rumaruka.riskofmine.api;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.SyncSkills;
import com.rumaruka.riskofmine.ntw.packets.PacketSyncSkillData;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;
import org.zeith.hammerlib.net.Network;
import org.zeith.hammerlib.util.mcf.Resources;

import java.util.*;

public class SkillData implements INBTSerializable<CompoundTag> {


    public Player player;

    // PRIMARY DATA
    private final Map<ResourceLocation, Short> stats = new HashMap<>();
    private final List<ResourceLocation> skills = new ArrayList<>();
    private final List<ResourceLocation> disabledSkills = new ArrayList<>();
    private final List<ResourceLocation> abilities = new ArrayList<>();
    public ResourceLocation prevDim;
    public CompoundTag persistedData = new CompoundTag();

    public SkillData(Player player) {
        this.player = player;
    }

    public void handleTick()
    {
        long start = System.currentTimeMillis();

        if(player == null || player.isSpectator())
            return;

        var level = player.level();

        // stat_scrolls.clear();

        Map<ResourceLocation, Long> updates = new HashMap<>();

        var skillReg = RiskOfMine.SKILLS;


        for(var value : skillReg)
        {
            var start0 = System.currentTimeMillis();
            var rn = value.getRegisterName();
            value.tick(this, !disabledSkills.contains(rn));
            updates.put(rn, System.currentTimeMillis() - start0);
        }

   

        if(level.isClientSide && !Objects.equals(prevDim, level.dimension().location()))
        {
            prevDim = level.dimension().location();
            requestSync();
        }

        if(!level.isClientSide)
        {
            player.sendSystemMessage(Component.translatable("chat." + RiskOfMine.MODID + ".guide"));
            sync();
        }


 

        long end = System.currentTimeMillis();

        if(end - start > 50L)
            RiskOfMine.logger.warn("Skill tick took too long! ({} ms, expected <50 ms!). Time map: {}",
                    (end - start),
                    updates.entrySet().stream().sorted(Comparator.<Map.Entry<ResourceLocation, Long>> comparingLong(Map.Entry::getValue).reversed()).toList()
            );
    }

    public void sync()
    {
        if( player instanceof ServerPlayer sp)
            PacketSyncSkillData.sync(sp);
    }

    public void requestSync()
    {
        if(player != null && player.isLocalPlayer())
            Network.sendToServer(new PacketSyncSkillData());
    }


    public void setSkillLevel(SkillBase stat, Number lvl)
    {
        setSkillLevelNoSync(stat, lvl);
        sync();
    }

    public void setSkillLevelNoSync(SkillBase stat, Number lvl)
    {
        stats.put(stat.getRegisterName(), lvl.shortValue());
    }



    public boolean atTickRate(int i)
    {
        return i > 0 && player.tickCount % i == 0;
    }



    public boolean hasSkillScroll(SkillBase skill)
    {
        return skill != null && skills.contains(skill.getRegisterName());
    }



    public boolean unlockSkillScroll(SkillBase skill, boolean sync)
    {
        if(skill != null &&player != null && !player.level().isClientSide && !skills.contains(skill.getRegisterName()))
        {
            skills.add(skill.getRegisterName());
            if(sync) sync();
            return true;
        }

        return false;
    }


    public boolean lockSkillScroll(SkillBase skill, boolean sync)
    {
        if(skill != null && player != null && !player.level().isClientSide && skills.remove(skill.getRegisterName()))
        {
            if(sync) sync();
            return true;
        }
        return false;
    }

    public boolean isSkillActive(SkillBase skill)
    {
        return !disabledSkills.contains(skill.getRegisterName());
    }

    public void setSkillState(SkillBase skill, boolean active)
    {
        if(active)
        {
            if(disabledSkills.remove(skill.getRegisterName()))
                sync();
        } else if(!disabledSkills.contains(skill.getRegisterName()))
        {
            disabledSkills.add(skill.getRegisterName());
            sync();
        }
    }

    public int getAbilityCount()
    {
        return abilities.size();
    }

    public Player getPlayer()
    {
        if(SyncSkills.is(this)) return ROMUtils.getPlayer();
        return player;
    }

    public SkillData toCurrent(Player playerReference)
    {
        if(player != playerReference) this.player = playerReference;
        return this;
    }


    @Override
    public CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag nbt = new CompoundTag();

       

        var reg = RiskOfMine.SKILLS;
        nbt.put("Persisted", persistedData);
      
        ListTag list = new ListTag();
        for(var skillKey : stats.keySet())
        {
            SkillBase stat = reg.get(skillKey);

            if(stat == null)
            {
                RiskOfMine.logger.warn("[SAVE] Skill '" + skillKey + "' wasn't found. Maybe you removed the addon? Skipping unregistered ability.");
                continue;
            }

            CompoundTag tag = new CompoundTag();
            tag.putString("Id", stat.getRegisterName().toString());
            list.add(tag);
        }
        nbt.put("Levels", list);

        list = new ListTag();
        for(var scroll : skills)
            list.add(StringTag.valueOf(scroll.toString()));
        nbt.put("Scrolls", list);

        list = new ListTag();
        for(var scroll : disabledSkills)
            list.add(StringTag.valueOf(scroll.toString()));
        nbt.put("DisabledSkills", list);



        return nbt;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt)
    {


        var reg = RiskOfMine.SKILLS;
        ListTag lvls = nbt.getList("Levels", Tag.TAG_COMPOUND);
        for(int i = 0; i < lvls.size(); ++i)
        {
            CompoundTag tag = lvls.getCompound(i);
            String sstat = tag.getString("Id");

            var sk = reg.get(ResourceLocation.tryParse(sstat));

            if(sk == null)
            {
                continue;
            }

            setSkillLevel(sk, tag.getShort("Lvl"));
        }

        skills.clear();
        disabledSkills.clear();
        abilities.clear();

        ListTag list = nbt.getList("Scrolls", Tag.TAG_STRING);
        for(int i = 0; i < list.size(); ++i)
            skills.add(Resources.location(list.getString(i)));

        list = nbt.getList("DisabledSkills", Tag.TAG_STRING);
        for(int i = 0; i < list.size(); ++i)
            disabledSkills.add(Resources.location(list.getString(i)));

        list = nbt.getList("Abilities", Tag.TAG_STRING);
        for(int i = 0; i < list.size(); ++i)
            abilities.add(Resources.location(list.getString(i)));



    }

    public static SkillData deserialize(Player player, CompoundTag nbt)
    {
        SkillData data = new SkillData(player);
        data.prevDim = player.level().dimension().location();
        data.deserializeNBT(player.registryAccess(), nbt);
        return data;
    }
}