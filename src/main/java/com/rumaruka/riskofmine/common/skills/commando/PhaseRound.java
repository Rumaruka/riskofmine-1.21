package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class PhaseRound extends CommandoSkills {

    protected boolean isRightFlag;

    protected int cooldownCountMax;

    public PhaseRound(int cooldownCountMax){
        super(SkillType.SECONDARY, cooldownCountMax);
        addListener(this::onRightClick);
        this.cooldownCountMax=cooldownCountMax;

        addListener(this::onTick);
        addListener(this::onDeath);


    }

    private void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
        if (isActive()) {

            isRightFlag = true;

        }

    }



    private void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) {

            if (isSkillActive) {
                if (isRightFlag &&isCooldown()) {
                    PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level);

                    tnt.setPos(player.getX(), player.getY() + 1, player.getZ());
                    level.addFreshEntity(tnt);
                    isRightFlag = false;
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







