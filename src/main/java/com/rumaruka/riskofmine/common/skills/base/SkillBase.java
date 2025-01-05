package com.rumaruka.riskofmine.common.skills.base;

import com.rumaruka.riskofmine.api.Survivors;

public abstract class SkillBase {
    protected Survivors survivors;
    protected SkillType skillType;
    public SkillBase(Survivors survivors,SkillType skillType) {
        this.survivors = survivors;
        this.skillType=skillType;
    }








}
