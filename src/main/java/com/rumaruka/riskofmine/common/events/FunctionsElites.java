package com.rumaruka.riskofmine.common.events;

import com.rumaruka.riskofmine.init.ROMEffects;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class FunctionsElites {


    @SubscribeEvent
    public static void onBlazingTick(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        if (entity instanceof LivingEntity living) {
            if (living.hasEffect(ROMEffects.BLAZING)) {
                living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE));

                //  ROMUtils.getPlayer().sendSystemMessage(Component.literal("Я ГОРЮ"));
            }
        }

    }


    @SubscribeEvent
    public static void fireAttack(EntityInvulnerabilityCheckEvent event) {
        Entity target = event.getEntity();
        Entity attacked = event.getSource().getEntity();
        Level level = target.level();
        if (attacked instanceof Mob) {
            if (target instanceof ServerPlayer player) {
                if (!level.isClientSide()) {
                    player.setRemainingFireTicks(100);
                }
            }
        }

    }
}
