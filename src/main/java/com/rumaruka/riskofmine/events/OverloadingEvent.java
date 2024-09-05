package com.rumaruka.riskofmine.events;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class OverloadingEvent {
    @SubscribeEvent
    public static void onTrackedEntity(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();
        Player entity = event.getEntity();
        if (((IOverloading) target).isOverloading()) {
            ROMNetwork.sendToClient(new OverloadingPacket(target.getId(), true), (ServerPlayer) entity);
        }
    }
}
