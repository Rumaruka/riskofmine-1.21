package com.rumaruka.riskofmine.common.events.cap_event;

import com.rumaruka.riskofmine.common.cap.Timer;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.init.ROMAttachment;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class TimerEvents {
    private static long time;
    @Getter
    public static int value;

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Timer barrier = Timer.get(player);
            barrier.setTimer(barrier.getCurrentTimer());
        }


    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.TIMER.get())) {
            event.getEntity().getData(ROMAttachment.TIMER.get()).setTimer(event.getOriginal().getData(ROMAttachment.SHIELDS.get()).getCurrentShields());

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Timer data = Timer.get(entity);
            data.setTimer(data.getCurrentTimer());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player){
            Timer timer = Timer.get(player);
            if (ROMConfig.TIME_UPDATE_TIMER > 0) {
                if (time == 0L) {
                    time = (long) player.level().getDayTimePerTick();
                }
                long minute = ROMConfig.TIME_UPDATE_TIMER * 60L * 1000L;
                if (player.level().getDayTimePerTick() - time > minute) {
                    value+=1;
                    timer.addTimer((int) 1);

                }
            }
        }

    }


}

