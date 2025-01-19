package com.rumaruka.riskofmine.common.events;


import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.init.ROMParticles;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.utils.ROMDoubleEffect;
import com.rumaruka.riskofmine.utils.ROMMathFormula;
import com.rumaruka.riskofmine.utils.ROMUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;


@EventBusSubscriber
public class ItemsEvents {

    @Getter
    @Setter
    public static boolean isAlive;

    @SubscribeEvent
    public static void onEntityAttack(EntityInvulnerabilityCheckEvent event) {
        Entity target = event.getEntity();
        Entity attacked = event.getSource().getEntity();
        Level level = target.level();
        if (attacked instanceof ServerPlayer player) {
            if (!level.isClientSide()) {
                if (ROMUtils.checkInventory(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance()) || ROMUtils.checkCurios(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance())) {
                    if (target.getType().is(Tags.EntityTypes.BOSSES)) {
                        int counting = ROMUtils.countAll(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance());
                        target.hurt(level.damageSources().magic(), counting);
                    }
                }

                if (ROMUtils.checkInventory(player, ROMItems.ARMOR_PIERCING_ROUNDS.getDefaultInstance()) || ROMUtils.checkCurios(player, ROMItems.CROWBAR.getDefaultInstance())) {
                    if (target instanceof LivingEntity livingTarget) {
                        if (livingTarget.getHealth() > (livingTarget.getMaxHealth() * 90 / 100)) {
                            livingTarget.hurt(level.damageSources().magic(), (float) (ROMUtils.countAll(player, ROMItems.CROWBAR.getDefaultInstance()) * 1.00115d));
                        }
                    }

                }

                if (ROMUtils.checkInventory(player, ROMItems.GASOLINE.getDefaultInstance()) || ROMUtils.checkCurios(player, ROMItems.GASOLINE.getDefaultInstance())) {
                    if (target instanceof LivingEntity livingTarget) {
                        livingTarget.setRemainingFireTicks(ROMUtils.countAll(player, ROMItems.GASOLINE.getDefaultInstance()) * 20);

                    }
                }


                if (ROMUtils.checkInventory(player, ROMItems.STICKY_BOMB.getDefaultInstance()) || ROMUtils.checkCurios(player, ROMItems.STICKY_BOMB.getDefaultInstance())) {
                    if (target instanceof LivingEntity livingTarget) {
                        StickyBombEntity entityStickyBomb = new StickyBombEntity(level, target.getX() + 0.5d, target.getY() + 0.5d, target.getZ() + 0.5d, player, livingTarget);
                        level.addFreshEntity(entityStickyBomb);
                    }

                }
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerAttack(EntityInvulnerabilityCheckEvent event) {
        Entity target = event.getEntity();
        Entity attacked = event.getSource().getEntity();
        Level level = target.level();
        if (attacked instanceof Mob) {
            if (target instanceof ServerPlayer player) {
                if (!level.isClientSide()) {
                    if (ROMUtils.checkInventory(player, ROMItems.OLD_WAR_STEALTHKIT.getDefaultInstance()) || ROMUtils.checkInventory(player, ROMItems.OLD_WAR_STEALTHKIT.getDefaultInstance())) {
                        if (player.getHealth() < 2.5f) {
                            ROMUtils.addEffects(player,MobEffects.INVISIBILITY);
                            ROMUtils.addEffects(player,MobEffects.MOVEMENT_SPEED);

                        }
                    }
                    if (ROMUtils.checkInventory(player, ROMItems.DIO_BEST_FRIEND.getDefaultInstance()) || ROMUtils.checkCurios(player, ROMItems.DIO_BEST_FRIEND.getDefaultInstance())) {
                        if (player.getHealth() == 1f) {
                            ROMUtils.addEffects(player,MobEffects.DIG_SPEED);
                            ROMUtils.addEffects(player,MobEffects.MOVEMENT_SPEED);

                        }
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        LivingEntity target = event.getEntity();
        Entity attacked = event.getSource().getEntity();
        Level level = target.level();
        if (!level.isClientSide()) {
        if (attacked instanceof ServerPlayer player) {

                if (ROMUtils.checkInventory(player,  ROMItems.MONSTER_TOOTH.getDefaultInstance()) || ROMUtils.checkCurios(player,  ROMItems.MONSTER_TOOTH.getDefaultInstance())) {
                    level.addFreshEntity(new HealthOrbEntity(level, target.getX() + 0.5d, target.getY() + 0.5d, target.getZ() + 0.5d, ROMUtils.countAll(player,  ROMItems.MONSTER_TOOTH.getDefaultInstance())));
                    level.playSound(null, new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ()), ROMSounds.PROC_MT_SPAWN.get(), SoundSource.MASTER, 2, 2);
                }
            }

        }
    }

    @SubscribeEvent
    public static void onEntityUpdate(EntityTickEvent.Post event) {
        Player player = ROMUtils.getPlayer();
        Entity entity = event.getEntity();
        Level level = entity.level();
        if (!level.isClientSide()) {
            if (entity instanceof LivingEntity livingEntity) {

                if (livingEntity instanceof Mob mob) {
                    if (player != null) {
                        if (ROMUtils.checkInventory(player, ROMItems.FOCUS_CRYSTAL.getDefaultInstance()) || (ROMUtils.checkCurios(player, ROMItems.FOCUS_CRYSTAL.getDefaultInstance()))) {
                            float distance = mob.distanceTo(player);
                            if (distance <= 3.5f) {
                                mob.hurt(level.damageSources().magic(), ROMMathFormula.powerIncreasing(ROMUtils.counting(player, new ItemStack(ROMItems.FOCUS_CRYSTAL)), 5.0f, 5));
                                ROMUtils.getMc().particleEngine.createTrackingEmitter(mob, ROMParticles.FOCUS_CRYSTAL.get());
                                if (mob.getHealth() == 0) {
                                    setAlive(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerUpdate(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (ROMUtils.checkInventory(player, ROMItems.POWER_ELIXIR.getDefaultInstance()) || ROMUtils.checkCurios(player, ROMItems.POWER_ELIXIR.getDefaultInstance())) {
            if (player.getHealth() < 2) {
                player.heal(player.getHealth());
                ROMUtils.replaceItem(ROMItems.POWER_ELIXIR.getDefaultInstance(), ROMItems.EMPTY_ELIXIR.getDefaultInstance());
            }
        }

    }


    @SubscribeEvent
    public static void onEntityJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide()) {
            if (entity instanceof Player player) {
                if (ROMUtils.checkInventory(player, new ItemStack(ROMItems.HOPOO_FEATHER)) || ROMUtils.checkCurios(player, new ItemStack(ROMItems.HOPOO_FEATHER))) {
                    ROMDoubleEffect.play(player);
                }
            }
        }
    }
}
