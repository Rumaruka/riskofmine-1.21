package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.common.cap.Money;
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

public record MoneyPacket(int entityID, int value) implements CustomPacketPayload {
    public static final Type<MoneyPacket> TYPE = new Type<>(rl("money"));


    public static final StreamCodec<FriendlyByteBuf, MoneyPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, MoneyPacket::entityID,
            ByteBufCodecs.INT, MoneyPacket::value,

            MoneyPacket::new);


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityID());
            if (entity != null) {
                FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                Money data = Money.get((Player) entity);
                byteBuf.writeUtf(entity.getStringUUID());
                byteBuf.writeInt(entityID());
                byteBuf.writeInt(data.getCurrentMoney());

                data.setMoney(value());

            }


        });

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
