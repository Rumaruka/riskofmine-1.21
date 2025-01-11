package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.utils.ROMUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record SurvivorsPacket(int entityID) implements CustomPacketPayload {
    public static final Type<SurvivorsPacket> TYPE = new Type<>(rl("survivors"));

    public static final StreamCodec<FriendlyByteBuf, SurvivorsPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, SurvivorsPacket::entityID,


            SurvivorsPacket::new);
    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {


            Entity entity = ROMUtils.getLvL().getEntity(entityID());
            if (entity != null) {
                if (entity instanceof Player player) {
                    if (player instanceof IPlayerSurvivorsBridge survivorsBridge){
                        Survivors survivors = survivorsBridge.riskofmine$getSurvivor().survivors();
                        FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
                        byteBuf.writeUtf(entity.getStringUUID());
                        byteBuf.writeInt(entityID());
                        byteBuf.writeFloat(survivors.getHealth());
                        byteBuf.writeFloat(survivors.getSpeed());
                        byteBuf.writeFloat(survivors.getHealth_regen());
                        byteBuf.writeFloat(survivors.getDamage());
                        byteBuf.writeFloat(survivors.getArmor());
                    }
                }



            }


        });
    }
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
