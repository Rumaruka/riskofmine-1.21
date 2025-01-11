package com.rumaruka.riskofmine.api.registry;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ROMRegistry {

    public static final ResourceKey<? extends Registry<SkillBase>> SKILL = ResourceKey.createRegistryKey(RiskOfMine.rl("skill"));

}
