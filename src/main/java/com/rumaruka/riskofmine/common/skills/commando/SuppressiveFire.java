package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class SuppressiveFire extends CommandoSkills {

    protected boolean isFlag;
    protected int cooldownCountMax;

    public SuppressiveFire(int cooldownCountMax) {
        super(SkillType.SPECIAL, cooldownCountMax);
        addListener(this::onInputKey);
        this.cooldownCountMax = cooldownCountMax;
        addListener(this::onTick);
        addListener(this::onDeath);


    }

    private void onInputKey(InputEvent.Key event) {

        if (isActive()) {
            if (KEY_SPECIAL_SKILL.isDown()){
                isFlag = true;
            }


        }

    }


    private void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();


        if (!level.isClientSide()) {

            if (isSkillActive) {
                if (isFlag && isCooldown()) {
                    shootMultipleArrows(player, level, 5, 2);
                    isFlag = false;
                    setCooldown(false);
                    setCooldownCount(cooldownCountMax);
                }


            }
        }


    }

    private void onDeath(LivingDeathEvent event) {
        if (isSkillActive) {
            isKillInSkills = true;
        }
    }


    public static void shootMultipleArrows(Player player, Level world, int numberOfArrows, int delayTicks) {
        new Thread(() -> {
            for (int i = 0; i < numberOfArrows; i++) {
                try {
                    Thread.sleep(delayTicks * 50L); // 50 ms per tick
                } catch (InterruptedException e) {
                   e.fillInStackTrace();
                }

                MinecraftServer server = world.getServer();
                if (server!=null){
                    server.execute(() -> {
                        if (player != null && !world.isClientSide()) {
                            Arrow arrow = new Arrow(EntityType.ARROW, world);

                            Vec3 direction = player.getLookAngle();
                            arrow.shoot(direction.x, direction.y, direction.z, 3F, 1.0F);
                            arrow.setPos(player.getX(), player.getY() + 1, player.getZ());
                            world.addFreshEntity(arrow);
                        }
                    });
                }

            }
        }).start();

    }
}






