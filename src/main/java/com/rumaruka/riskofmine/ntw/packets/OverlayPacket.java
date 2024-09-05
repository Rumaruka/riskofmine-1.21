package com.rumaruka.riskofmine.ntw.packets;


import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class OverlayPacket implements CustomPacketPayload {

    public static final Type<OverlayPacket> TYPE = new Type<>(rl("overlay"));
    public static final StreamCodec<FriendlyByteBuf, OverlayPacket> CODEC = StreamCodec.unit(new OverlayPacket());


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {

            //            Money money = Money.of(ROMUtils.getPlayer());
//            Lunar lunar = Lunar.of(ROMUtils.getPlayer());
//            if (money != null) {
//                money.detectAndSendChanges();
//
//            }
//            if (lunar != null) {
//                lunar.detectAndSendChanges();
//            }
        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
