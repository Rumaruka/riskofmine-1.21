package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.utils.ROMUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record OverloadingPacket(int entityId, boolean overloading) implements CustomPacketPayload {
    public static final Type<OverloadingPacket> TYPE = new Type<>(rl("overloading"));


    public static final StreamCodec<FriendlyByteBuf, OverloadingPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, OverloadingPacket::entityId,
            ByteBufCodecs.BOOL, OverloadingPacket::overloading,

            OverloadingPacket::new);


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityId());
            if (entity != null) {
                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                byteBuf.writeUtf(entity.getStringUUID());
                byteBuf.writeInt(entityId());
                byteBuf.writeBoolean(overloading());
                if (entity instanceof IOverloading over) {
                    over.setOverloading(overloading);
                }
            }


        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
