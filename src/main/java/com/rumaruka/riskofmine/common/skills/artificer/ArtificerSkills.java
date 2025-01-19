package com.rumaruka.riskofmine.common.skills.artificer;

import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.api.registry.skill.SkillData;
import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.utils.ROMRandomChanceUtils;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.network.chat.Component;

public class ArtificerSkills extends SkillBase {

    public ArtificerSkills(SkillType skillType, int cooldown) {
        super(Survivors.ARTIFICER, skillType, cooldown);
    }


    @Override
    public void tick(SkillData data, boolean isActive) {
        super.tick(data, isActive);
        if (ROMUtils.getPlayer() instanceof IPlayerSurvivorsBridge survivorsBridge) {
            ISurvivors iSurvivors = survivorsBridge.riskofmine$getSurvivor();
            setActive(iSurvivors.survivors() == getSurvivors());

            if (KEY_ACTIVE_SKILL.consumeClick()) {
                isSkillActive = !isSkillActive;
                if (isSkillActive) {
                    // Enable the feature
                    ROMUtils.getPlayer().sendSystemMessage(Component.literal("Skill is enabled"));
                } else {
                    // Disable the feature
                    ROMUtils.getPlayer().sendSystemMessage(Component.literal("Skill is disabled"));
                }


            }

            if (getCooldownCount()>0){
                setCooldown(true);
                int cooldownCount = getCooldownCount();
                if (ROMRandomChanceUtils.percentChance(0.05)){
                    setCooldownCount(cooldownCount - 1);
                }



            }


        }


    }


}

