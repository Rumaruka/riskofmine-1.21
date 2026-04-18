package com.rumaruka.riskofmine.common.skills.artificer;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class FlameBolt extends ArtificerSkills {

    protected boolean isLeftFlag;
    protected int cooldownCountMax;

    public FlameBolt(int cooldownCountMax) {
        super(SkillType.PRIMARY, cooldownCountMax);
        addListener(this::onLeftClick);
        addListener(this::onTick);
        this.cooldownCountMax = cooldownCountMax;


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

                    SmallFireball arrow = new SmallFireball(EntityType.SMALL_FIREBALL, level);

                    Vec3 direction = player.getLookAngle();
                    arrow.shoot(direction.x, direction.y, direction.z, 3F, 1.0F);
                    arrow.setPos(player.getX(), player.getY() + 1, player.getZ());
                    level.addFreshEntity(arrow);
                    arrow.setOwner(player);

                    isLeftFlag = false;
                    setCooldown(false);
                    setCooldownCount(cooldownCountMax);
                }


            }
        }


    }

}







