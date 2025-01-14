package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class DoubleTap extends CommandoSkills {
    public DoubleTap() {
        super(SkillType.PRIMARY);
        addListener(this::onBulletAttack);
    }


    public void onBulletAttack(PlayerInteractEvent.LeftClickEmpty event) {
        Entity target = event.getEntity();
        Level level = target.level();
        if (isActive()) {
            target.sendSystemMessage(Component.literal("Survivals: "+isActive()+"Skills:"+getRegisterName()+"Skill Type: "+getSkillType()));

        }


    }


}



