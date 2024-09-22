package com.rumaruka.riskofmine.common.cap;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.TimerPacket;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnknownNullability;

public class Timer implements INBTSerializable<CompoundTag> {
    private int timer;
    @Getter
    @Setter
    private Player target;

    public static final Codec<Timer> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("timer").forGetter(timer -> timer.timer)
            ).apply(instance, Timer::new)
    );

    public Timer() {
        this(0);
    }

    public Timer(int amount) {
        this.timer = amount;
    }

    public boolean consumeTimer(int price) {

        if (hasTimer()) {
            setTimer(getCurrentTimer() - price);

            return true;
        }

        return false;

    }

    public int getCurrentTimer() {
        return timer;
    }

    public void setTimer(int value) {

        timer = value;
        if (getTarget() instanceof ServerPlayer player) {
            ROMNetwork.sendToClient(new TimerPacket(getTarget().getId(), value), player);
        }
    }

    public int getMaxTimer() {
        return Integer.MAX_VALUE;
    }

    public boolean hasTimer() {
        return getCurrentTimer() > 0;
    }

    public void addTimer(int value) {

        setTimer(Math.min(getCurrentTimer() + value, getMaxTimer()));
    }

    public void removetimer(int value) {

        setTimer(Math.max(getCurrentTimer() - value, 0));
    }

    public static @NotNull Timer get(Player player) {

        Timer data = player.getData(ROMAttachment.TIMER.get());
        data.setTarget(player);
        player.setData(ROMAttachment.TIMER.get(), data);
        return data;

    }


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("timer", timer);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
            nbt.getInt("timer");
    }
}
