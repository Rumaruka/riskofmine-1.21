package com.rumaruka.riskofmine.api.client;

import com.rumaruka.riskofmine.api.registry.skill.SkillBase;

public interface IClientSkillExtensions {

    IClientSkillExtensions DEFAULT = new IClientSkillExtensions()
    {
    };

    static IClientSkillExtensions of(SkillBase s)
    {
        return s.getRenderPropertiesInternal() instanceof IClientSkillExtensions e ? e : DEFAULT;
    }


}
