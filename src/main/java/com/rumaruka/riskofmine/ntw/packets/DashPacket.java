package com.rumaruka.riskofmine.ntw.packets;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record DashPacket() implements CustomPacketPayload {
    public static final StreamCodec<FriendlyByteBuf, DashPacket> CODEC = StreamCodec.unit(new DashPacket());
    public static final Type<DashPacket> TYPE = new Type<>(rl("dash"));

    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> handlePacket(this, ctx.player()));
    }


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private static void handlePacket(DashPacket packet, Player player) {
        ServerLevel lvl = (ServerLevel) player.level();

        var width = player.getBbWidth() / 2;
        var height = player.getBbHeight() * 0.3F;

        lvl.sendParticles(ParticleTypes.POOF, player.getX(), player.getY() + height, player.getZ(), 30, width, height, width, 0.08F);
        lvl.sendParticles(ParticleTypes.ENCHANTED_HIT, player.getX(), player.getY() + height, player.getZ(), 10, width, height, width, 0.2F);


    }

}

