package com.rumaruka.riskofmine.ntw.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import static com.rumaruka.riskofmine.RiskOfMine.rl;


public record ItemActivationPacket(ItemStack stack) implements CustomPacketPayload {


    public static final Type<ItemActivationPacket> TYPE = new Type<>(rl("item_activation"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ItemActivationPacket> STREAM_CODEC = StreamCodec.composite(
            ItemStack.STREAM_CODEC, ItemActivationPacket::stack,
            ItemActivationPacket::new
    );


    public void handle(IPayloadContext ctx) {
        ctx.enqueueWork(() -> Minecraft.getInstance().gameRenderer.displayItemActivation(stack()));
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
