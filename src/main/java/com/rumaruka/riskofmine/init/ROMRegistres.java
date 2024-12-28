package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.skills.SkillBase;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.schedule.Activity;

public class ROMRegistres {
    public static final ResourceLocation ROOT_REGISTRY_NAME = ResourceLocation.withDefaultNamespace("root");
    public static final ResourceKey<Registry<SkillBase>> SKILL = createKey("activity");
    private static <T> ResourceKey<Registry<T>> createKey(String p_259572_) {
        return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(RiskOfMine.MODID,p_259572_));
    }
}
