package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.utils.ROMUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record LunarPacket(int entityID, int value) implements CustomPacketPayload {
    public static final Type<LunarPacket> TYPE = new Type<>(rl("lunar"));


    public static final StreamCodec<FriendlyByteBuf, LunarPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, LunarPacket::entityID,
            ByteBufCodecs.INT, LunarPacket::value,

            LunarPacket::new);


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityID());
            if (entity != null) {
                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                Lunar data = Lunar.get((Player) entity);
                byteBuf.writeUtf(entity.getStringUUID());
                byteBuf.writeInt(entityID());
                byteBuf.writeInt(data.getCurrentLunar());

                data.setLunar(value());

            }


        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
