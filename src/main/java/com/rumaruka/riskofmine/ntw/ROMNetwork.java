package com.rumaruka.riskofmine.ntw;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.ntw.packets.*;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;


@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ROMNetwork {


    @SubscribeEvent
    public static void setup(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar network = event.registrar(RiskOfMine.MODID).optional();

            network.playToClient(ItemActivationPacket.TYPE,ItemActivationPacket.STREAM_CODEC,ItemActivationPacket::handle);
            network.playToServer(DoubleJumpPacket.TYPE,DoubleJumpPacket.STREAM_CODEC,DoubleJumpPacket::handle);

            network.playToClient(OverloadingPacket.TYPE,OverloadingPacket.CODEC,OverloadingPacket::handle);
            network.playToClient(BlazingPacket.TYPE,BlazingPacket.CODEC,BlazingPacket::handle);

            network.playToClient(BarrierPacket.TYPE,BarrierPacket.CODEC,BarrierPacket::handle);
            network.playToClient(LunarPacket.TYPE,LunarPacket.CODEC,LunarPacket::handle);
            network.playToClient(MoneyPacket.TYPE,MoneyPacket.CODEC,MoneyPacket::handle);
            network.playToClient(ShieldsPacket.TYPE,ShieldsPacket.CODEC,ShieldsPacket::handle);
            network.playToClient(TimerPacket.TYPE,TimerPacket.CODEC,TimerPacket::handle);
            network.playToClient(OverlayPacket.TYPE,OverlayPacket.CODEC,OverlayPacket::handle);
    }

    public static <MSG extends CustomPacketPayload> void sendToServer(MSG message) {
        PacketDistributor.sendToServer(message);
    }

    public static <MSG extends CustomPacketPayload> void sendToClient(MSG message, ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, message);
    }

    public static <MSG extends CustomPacketPayload> void sendToClientsTrackingEntity(MSG message, Entity entity) {
        PacketDistributor.sendToPlayersTrackingEntity(entity, message);
    }

    public static <MSG extends CustomPacketPayload> void sendToClientsTrackingEntityAndSelf(MSG message, Entity entity) {
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, message);
    }




}
