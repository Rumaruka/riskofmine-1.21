package com.rumaruka.riskofmine.common.cap;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.ntw.ROMNetwork;

import com.rumaruka.riskofmine.ntw.packets.LunarPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class Lunar implements INBTSerializable<CompoundTag> {
    private int lunar;
    private Player target;

    public static final Codec<Lunar> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("lunar").forGetter(ln -> ln.lunar)
            ).apply(instance, Lunar::new)
    );

    public Lunar() {
        this(0);
    }

    public Lunar(int amount) {
        this.lunar = amount;
    }

    public boolean consumeLunar(int price) {

        if (hasLunar()) {
            setLunar(getCurrentLunar() - price);

            return true;
        }

        return false;

    }

    public int getCurrentLunar() {
        return lunar;
    }

    public void setLunar(int value) {

        lunar = value;
        if (getTarget() instanceof ServerPlayer player) {
            ROMNetwork.sendToClient(new LunarPacket(getTarget().getId(), value), player);
        }
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public int getMaxLunar() {
        return Integer.MAX_VALUE;
    }

    public boolean hasLunar() {
        return getCurrentLunar() > 0;
    }

    public void addLunar(int value) {

        setLunar(Math.min(getCurrentLunar() + value, getMaxLunar()));
    }

    public void removeLunar(int value) {

        setLunar(Math.max(getCurrentLunar() - value, 0));
    }

    public static @NotNull Lunar get(Player player) {
        Lunar data = player.getData(ROMAttachment.LUNAR.get());
        data.setTarget(player);
        player.setData(ROMAttachment.LUNAR.get(), data);
        return data;

    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("lunar", lunar);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        nbt.getInt("lunar");
    }
}
