package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.api.registry.skill.SkillData;
import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.utils.ROMUtils;

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
            {



                    if (!isCooldown()) {
                        int cooldownCount = getCooldownCount();
                        setCooldown(true);
                        setCooldownCount(cooldownCount);
                        cooldownCount--;
                        if (cooldownCount == 0) {
                            setCooldown(false);
                        }

                    }
                }

            }


        }
    }

