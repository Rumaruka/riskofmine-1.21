package com.rumaruka.riskofmine.common.events.abilites;

import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


@EventBusSubscriber
public class ShieldsEvents {
    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Shields barrier = Shields.get(player);
            barrier.setShields(barrier.getCurrentShields());
        }


    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.SHIELDS.get())) {
            event.getEntity().getData(ROMAttachment.SHIELDS.get()).setShields(event.getOriginal().getData(ROMAttachment.SHIELDS.get()).getCurrentShields());

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Shields data = Shields.get(entity);
            data.setShields(data.getCurrentShields());
        }
    }


    @SubscribeEvent
    public static void onRegenShieldsWithShaped(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player) {
            Shields shields = Shields.get(player);

            if (player.getOffhandItem().getItem() == ROMItems.SHAPED_GLASS) {


                if (!event.isInvulnerable() && player.hurtTime == 0) {

                    shields.setShields(16);
                }


            }
        }

    }

    @SubscribeEvent
    public static void onDamagePlayerWithShield(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();


        if (entity instanceof Player player) {
            Shields shields = Shields.get(player);
            event.setInvulnerable(true);
            shields.removeShields(1);

            if (!shields.hasShields()) {

                event.setInvulnerable(false);
            }


        }


    }


}
