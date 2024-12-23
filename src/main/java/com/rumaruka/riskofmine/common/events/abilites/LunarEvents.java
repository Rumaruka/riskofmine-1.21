package com.rumaruka.riskofmine.common.events.abilites;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


@EventBusSubscriber(modid = RiskOfMine.MODID)
public class LunarEvents {


    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Lunar barrier = Lunar.get(player);
            barrier.setLunar(barrier.getCurrentLunar());
        }


    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.LUNAR.get())) {
            event.getEntity().getData(ROMAttachment.LUNAR.get()).setLunar(event.getOriginal().getData(ROMAttachment.LUNAR.get()).getCurrentLunar());

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Lunar data = Lunar.get(entity);
            data.setLunar(data.getCurrentLunar());
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {

        if (event.getSource().getEntity() instanceof ServerPlayer) {
            LivingEntity livingEntity = event.getEntity();
            Level level = livingEntity.level();


            if (!level.isClientSide) {
                if (livingEntity.tickCount % 10 == 0) {
                    ItemEntity itemEntity = new ItemEntity(level, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), new ItemStack(ROMItems.LUNAR_COIN));
                    level.addFreshEntity(itemEntity);

                }


            }

        }
        if (event.getSource().getEntity() instanceof AmbientCreature && event.getEntity() instanceof ServerPlayer player) {
            Level world = player.level();

            if (!world.isClientSide()) {
                Lunar lunar = Lunar.get(player);
                if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    return;
                } else {
                    lunar.setLunar(0);


                }


            }
        }
    }
}
