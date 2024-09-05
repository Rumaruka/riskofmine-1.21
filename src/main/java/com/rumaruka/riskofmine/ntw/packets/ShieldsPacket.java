package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.utils.ROMUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record ShieldsPacket(int entityID, int value) implements CustomPacketPayload {
    public static final Type<ShieldsPacket> TYPE = new Type<>(rl("shields"));


    public static final StreamCodec<FriendlyByteBuf, ShieldsPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, ShieldsPacket::entityID,
            ByteBufCodecs.INT, ShieldsPacket::value,

            ShieldsPacket::new);


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityID());
            if (entity != null) {
                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                Shields data = Shields.get((LivingEntity) entity);
                byteBuf.writeUtf(entity.getStringUUID());
                byteBuf.writeInt(entityID());
                byteBuf.writeInt(data.getCurrentShields());

                data.setShields(value());

            }


        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
