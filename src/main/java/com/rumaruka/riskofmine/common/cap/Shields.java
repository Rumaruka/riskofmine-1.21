package com.rumaruka.riskofmine.common.cap;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.ShieldsPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class Shields implements INBTSerializable<CompoundTag> {
    private int shields;
    private LivingEntity target;

    public static final Codec<Shields> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("barrier").forGetter(shields -> shields.shields)
            ).apply(instance, Shields::new)
    );

    public Shields() {
        this(0);
    }

    public Shields(int amount) {
        this.shields = amount;
    }

    public boolean consumeShields(int price) {

        if (hasShields()) {
            setShields(getCurrentShields() - price);

            return true;
        }

        return false;

    }

    public int getCurrentShields() {
        return shields;
    }

    public void setShields(int value) {

        shields = value;
        if (getTarget() instanceof ServerPlayer player) {
            ROMNetwork.sendToClient(new ShieldsPacket(getTarget().getId(), value), player);
        }
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public int getMaxShields() {
        return Integer.MAX_VALUE;
    }

    public boolean hasShields() {
        return getCurrentShields() > 0;
    }

    public void addShields(int value) {

        setShields(Math.min(getCurrentShields() + value, getMaxShields()));
    }

    public void removeShields(int value) {

        setShields(Math.max(getCurrentShields() - value, 0));
    }

    public static @NotNull Shields get(LivingEntity player) {

        Shields data = player.getData(ROMAttachment.SHIELDS.get());
        data.setTarget(player);
        player.setData(ROMAttachment.SHIELDS.get(), data);
        return data;

    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("shields", shields);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
            nbt.getInt("shields");
    }
}
