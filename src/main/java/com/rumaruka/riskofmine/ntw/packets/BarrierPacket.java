package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.init.ROMAttachment;
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

public record BarrierPacket(int entityID, int value) implements CustomPacketPayload {
    public static final Type<BarrierPacket> TYPE = new Type<>(rl("barrier"));


    public static final StreamCodec<FriendlyByteBuf, BarrierPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, BarrierPacket::entityID,
            ByteBufCodecs.INT, BarrierPacket::value,

            BarrierPacket::new);


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityID());
            if (entity != null) {
                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                Barrier data = Barrier.get((LivingEntity) entity);
                byteBuf.writeUtf(entity.getStringUUID());
                byteBuf.writeInt(entityID());
                byteBuf.writeInt(data.getCurrentBarrier());

                data.setBarrier(value());

            }


        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
