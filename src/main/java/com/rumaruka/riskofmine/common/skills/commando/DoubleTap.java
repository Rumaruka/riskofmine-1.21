package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class DoubleTap extends CommandoSkills {

    protected boolean isFlag;

    public DoubleTap() {
        super(SkillType.PRIMARY, 5);
        addListener(this::onLeftClick);
        addListener(this::onTick);


    }

    private void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {

        if (isActive()) {

            isFlag = true;


        }

    }


    private void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();

        if (!level.isClientSide()) {
            if (isFlag) {
                // player.addItem(Items.DIAMOND.getDefaultInstance());
                Arrow arrow = new Arrow(EntityType.ARROW, level);

                Vec3 direction = player.getLookAngle();
                arrow.shoot(direction.x, direction.y, direction.z, 1.5F, 1.0F);
                arrow.setPos(player.getX(), player.getY() + 1, player.getZ());
                level.addFreshEntity(arrow);
                isFlag = false;
            }



        }




    }

}







