package com.rumaruka.riskofmine.common.events.cap_event;

import com.rumaruka.riskofmine.common.cap.Timer;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.init.ROMEffects;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EventBusSubscriber
public class TimerEvents {


    private static final List<Holder<MobEffect>> ELITES_MOB_EFFECTS = new ArrayList<>();

    static {
        ELITES_MOB_EFFECTS.add(ROMEffects.BLAZING);
        ELITES_MOB_EFFECTS.add(ROMEffects.OVERLOADING);
        ELITES_MOB_EFFECTS.add(ROMEffects.CELESTINE);
    }

    @Getter
    @Setter
    protected static boolean isHasValueTimer;
    @Getter
    @Setter
    protected static int valueTimer;

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Timer barrier = Timer.get(player);
            barrier.setTimer(barrier.getCurrentTimer());
        }


    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.TIMER.get())) {
            event.getEntity().getData(ROMAttachment.TIMER.get()).setTimer(event.getOriginal().getData(ROMAttachment.TIMER.get()).getCurrentTimer());

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Timer data = Timer.get(entity);
            data.setTimer(data.getCurrentTimer());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        Timer timer = Timer.get(player);
        if (!level.isClientSide()) {
            if (level.nextSubTickCount() % 20 == 0) {
                timer.addTimer(1);
                setHasValueTimer(true);
                setValueTimer(timer.getCurrentTimer());

            }

        }

    }

    @SubscribeEvent
    public static void onTick(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        Holder<MobEffect> randomEffect = getRandomEffect();
        if (!level.isClientSide()) {
            if (isHasValueTimer()) {
                if (entity instanceof LivingEntity living) {
                    if (!(living instanceof Player)) {
                        if (getValueTimer() > 10) {
                            living.addEffect(new MobEffectInstance(randomEffect,1,1));
                        }
                    }

                }
            }
        }


    }
    public static Holder<MobEffect> getRandomEffect() {
        Random rand = new Random();
        int index = rand.nextInt(ELITES_MOB_EFFECTS.size());
        return ELITES_MOB_EFFECTS.get(index);
}




}

