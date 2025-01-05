package com.rumaruka.riskofmine.common.events.cap_event;

import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


@EventBusSubscriber
public class BarrierEvents {
    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Barrier barrier = Barrier.get(player);
            barrier.setBarrier(barrier.getCurrentBarrier());
        }


    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.BARRIER.get())) {
            event.getEntity().getData(ROMAttachment.BARRIER.get()).setBarrier(event.getOriginal().getData(ROMAttachment.BARRIER.get()).getCurrentBarrier());

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Barrier data = Barrier.get(entity);
            data.setBarrier(data.getCurrentBarrier());
        }
    }

    @SubscribeEvent
    public static void onDeathEntity(LivingDeathEvent event) {
        /**
         Player kill Entity
         */
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            Entity livingEntity = event.getEntity();
            Level world = livingEntity.level();

            if (!world.isClientSide()) {

                if (ROMUtils.checkInventory(player, ROMItems.TOPAZ_BROOCH.getDefaultInstance())) {
                    Barrier barrier = Barrier.get(player);
                    barrier.addBarrier(10);

                }


            }

        }

    }

    @SubscribeEvent
    public static void onDamagePlayerWithBarrier(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();


        if (entity instanceof Player player) {
            Barrier barrier = Barrier.get(player);

            if (barrier.hasBarrier()) {

                event.setInvulnerable(true);

            }


        }


    }

    @SubscribeEvent
    public static void onTick(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            Level world = livingEntity.level();

            if (!world.isClientSide()) {
                Barrier barrier = Barrier.get(player);
                if (barrier.hasBarrier() && player.tickCount % 10 == 0) {
                    barrier.removeBarrier(1);
                }

            }
        }

    }
}
