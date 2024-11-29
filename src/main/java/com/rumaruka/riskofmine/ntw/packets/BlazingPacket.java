package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.api.entity.IBlazing;
import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.utils.ROMUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record BlazingPacket(int entityId, boolean blazing) implements CustomPacketPayload {
    public static final Type<BlazingPacket> TYPE = new Type<>(rl("blazing"));


    public static final StreamCodec<FriendlyByteBuf, BlazingPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, BlazingPacket::entityId,
            ByteBufCodecs.BOOL, BlazingPacket::blazing,

            BlazingPacket::new);


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityId());
            if (entity != null) {
                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                byteBuf.writeUtf(entity.getStringUUID());
                byteBuf.writeInt(entityId());
                byteBuf.writeBoolean(blazing());
                if (entity instanceof IBlazing over) {
                    over.setBlazing(blazing);
                }
            }


        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
