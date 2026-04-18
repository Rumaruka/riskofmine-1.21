package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.api.registry.skill.SkillData;
import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;

import java.util.Collection;

public class CommandoSkills extends SkillBase {

    public CommandoSkills(SkillType skillType, int cooldown) {
        super(Survivors.COMMANDO, skillType, cooldown);
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
                    RiskOfMine.logger.info("Skill activated");
                    ROMUtils.sendChat("Skill is enabled");
                } else {
                    // Disable the feature
                    RiskOfMine.logger.info("Skill deactivate");
                    ROMUtils.sendChat("Skill is deactivate");

                }


            }

            if (getCooldownCount() > 0) {
                setCooldown(true);
                int cooldownCount = getCooldownCount();

                if (ROMUtils.getPlayer().level().nextSubTickCount() % 20 == 0) {
                    setCooldownCount(cooldownCount - 1);

                }


            }


        }


    }


}

