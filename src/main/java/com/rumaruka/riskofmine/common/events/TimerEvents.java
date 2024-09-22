package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.common.cap.Timer;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.init.ROMEffects;
import lombok.Getter;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber
public class TimerEvents {
    private static long time;
    @Getter
    public static int value;

    @net.neoforged.bus.api.SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Timer barrier = Timer.get(player);
            barrier.setTimer(barrier.getCurrentTimer());
        }


    }

    @net.neoforged.bus.api.SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.TIMER.get())) {
            event.getEntity().getData(ROMAttachment.TIMER.get()).setTimer(event.getOriginal().getData(ROMAttachment.SHIELDS.get()).getCurrentShields());

        }
    }

    @net.neoforged.bus.api.SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Timer data = Timer.get(entity);
            data.setTimer(data.getCurrentTimer());
        }
    }

//    @SubscribeEvent
//    public static void onPlayerTick(PlayerTickEvent.Post event) {
//        Timer timer = Timer.get(event.getEntity());
//        if (ROMConfig.TIME_UPDATE_TIMER > 0) {
//            if (time == 0L) {
//                time = System.currentTimeMillis();
//            }
//            long minute = ROMConfig.TIME_UPDATE_TIMER * 60L * 1000L;
//            if (System.currentTimeMillis() - time > minute) {
//               value+=1;
//               timer.addTimer(value);
//
//            }
//        }
//    }


}

