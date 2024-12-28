package com.rumaruka.riskofmine.common.skills;

import com.rumaruka.riskofmine.api.Survivors;

public abstract class SkillBase {
    protected Survivors survivors;

    public SkillBase(Survivors survivors) {
        this.survivors = survivors;
    }


   public abstract void onPressPrimarySkills(int cooldown);
}
