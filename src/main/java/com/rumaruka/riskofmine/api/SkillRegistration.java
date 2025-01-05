package com.rumaruka.riskofmine.api;

import com.google.common.base.Preconditions;
import com.rumaruka.riskofmine.common.skills.base.SkillBase;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class SkillRegistration {
    private final Map<ResourceLocation, SkillBase> skills = new HashMap<>();
    public SkillRegistration() {}
    public void registerPerk(ResourceLocation perkID, SkillBase skillBase) {
        Preconditions.checkNotNull(perkID);
        Preconditions.checkNotNull(skillBase);
        skills.put(perkID, skillBase);
    }

}
