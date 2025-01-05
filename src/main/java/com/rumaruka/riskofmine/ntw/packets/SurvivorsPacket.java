package com.rumaruka.riskofmine.ntw.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class SurvivorsPacket implements CustomPacketPayload {
    public static final Type<SurvivorsPacket> TYPE = new Type<>(rl("survivors"));

    public static final StreamCodec<FriendlyByteBuf, SurvivorsPacket> CODEC = StreamCodec.unit(new SurvivorsPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
