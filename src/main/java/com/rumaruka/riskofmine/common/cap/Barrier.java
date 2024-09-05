package com.rumaruka.riskofmine.common.cap;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.BarrierPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class Barrier implements INBTSerializable<CompoundTag> {
    private int barrier;
    private LivingEntity target;

    public static final Codec<Barrier> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("barrier").forGetter(barrier1 -> barrier1.barrier)
            ).apply(instance, Barrier::new)
    );

    public Barrier() {
        this(0);
    }

    public Barrier(int amount) {
        this.barrier = amount;
    }

    public boolean consumeBarrier(int price) {

        if (hasBarrier()) {
            setBarrier(getCurrentBarrier() - price);

            return true;
        }

        return false;

    }

    public int getCurrentBarrier() {
        return barrier;
    }

    public void setBarrier(int value) {

        barrier = value;
        if (getTarget() instanceof ServerPlayer player) {
            ROMNetwork.sendToClient(new BarrierPacket(getTarget().getId(), value), player);
        }
    }

    public LivingEntity getTarget() {
        return target;
    }

    public void setTarget(LivingEntity target) {
        this.target = target;
    }

    public int getMaxBarrier() {
        return Integer.MAX_VALUE;
    }

    public boolean hasBarrier() {
        return getCurrentBarrier() > 0;
    }

    public void addBarrier(int value) {

        setBarrier(Math.min(getCurrentBarrier() + value, getMaxBarrier()));
    }

    public void removeBarrier(int value) {

        setBarrier(Math.max(getCurrentBarrier() - value, 0));
    }

    public static @NotNull Barrier get(LivingEntity player) {

        Barrier data = player.getData(ROMAttachment.BARRIER.get());
        data.setTarget(player);
        player.setData(ROMAttachment.BARRIER.get(), data);
        return data;

    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("barrier", barrier);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
            nbt.getInt("barrier");
    }
}
