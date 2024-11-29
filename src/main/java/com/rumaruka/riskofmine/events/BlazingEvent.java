package com.rumaruka.riskofmine.events;

import com.rumaruka.riskofmine.api.entity.IBlazing;
import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.BlazingPacket;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class BlazingEvent {
    @SubscribeEvent
    public static void onTrackedEntity(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        Player entity = event.getEntity();
        if (((IBlazing) target).isBlazing()) {
            ROMNetwork.sendToClient(new BlazingPacket(target.getId(), true), (ServerPlayer) entity);
        }
    }
}
