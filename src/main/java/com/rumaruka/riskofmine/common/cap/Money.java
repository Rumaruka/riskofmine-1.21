package com.rumaruka.riskofmine.common.cap;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.MoneyPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class Money implements INBTSerializable<CompoundTag> {
    private int money;
    private Player target;

    public static final Codec<Money> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("money").forGetter(ln -> ln.money)
            ).apply(instance, Money::new)
    );

    public Money() {
        this(0);
    }

    public Money(int amount) {
        this.money = amount;
    }

    public boolean consumeMoney(int price) {

        if (hasMoney()) {
            setMoney(getCurrentMoney() - price);

            return true;
        }

        return false;

    }

    public int getCurrentMoney() {
        return money;
    }

    public void setMoney(int value) {

        money = value;
        if (getTarget() instanceof ServerPlayer player) {
            ROMNetwork.sendToClient(new MoneyPacket(getTarget().getId(), value), player);
        }
    }

    public Player getTarget() {
        return target;
    }

    public void setTarget(Player target) {
        this.target = target;
    }

    public int getMaxMoney() {
        return Integer.MAX_VALUE;
    }

    public boolean hasMoney() {
        return getCurrentMoney() > 0;
    }

    public void addMoney(int value) {

        setMoney(Math.min(getCurrentMoney() + value, getMaxMoney()));
    }

    public void removeMoney(int value) {

        setMoney(Math.max(getCurrentMoney() - value, 0));
    }

    public static @NotNull Money get(Player player) {

        Money data = player.getData(ROMAttachment.MONEY.get());
        data.setTarget(player);
        player.setData(ROMAttachment.MONEY.get(), data);
        return data;

    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("money", money);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        nbt.getInt("money");
    }
}
