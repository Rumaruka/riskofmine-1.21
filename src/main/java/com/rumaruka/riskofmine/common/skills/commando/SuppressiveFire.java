package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class SuppressiveFire extends CommandoSkills {

    protected boolean isLeftFlag;
    protected int cooldownCountMax;
    public SuppressiveFire(int cooldownCountMax) {
        super(SkillType.PRIMARY, cooldownCountMax);
        addListener(this::onLeftClick);
        this.cooldownCountMax=cooldownCountMax;
        addListener(this::onTick);
        addListener(this::onDeath);


    }

    private void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        if (isActive()) {

            isLeftFlag = true;

        }

    }


    private void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();


        if (!level.isClientSide()) {

            if (isSkillActive) {
                if (isLeftFlag && isCooldown()) {
                    Arrow arrow = new Arrow(EntityType.ARROW, level);

                    Vec3 direction = player.getLookAngle();
                    arrow.shoot(direction.x, direction.y, direction.z, 3F, 1.0F);
                    arrow.setPos(player.getX(), player.getY() + 1, player.getZ());
                    level.addFreshEntity(arrow);
                    isLeftFlag = false;
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

}






