package com.rumaruka.riskofmine.ntw.packets;

import com.rumaruka.riskofmine.utils.ROMDoubleEffect;
import com.rumaruka.riskofmine.utils.ROMUtils;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public record DoubleJumpPacket() implements CustomPacketPayload {
    public static final Type<DoubleJumpPacket> TYPE = new Type<>(rl("double_jump"));

    public static final StreamCodec<RegistryFriendlyByteBuf, DoubleJumpPacket> STREAM_CODEC = StreamCodec.unit(new DoubleJumpPacket());


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ROMUtils.getPlayer();
            FriendlyByteBuf byteBuf = new FriendlyByteBuf(Unpooled.buffer());
            byteBuf.writeUtf(player.getStringUUID());


        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
