package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.skills.SkillBase;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

public class ROMSkills {
    public static final DeferredRegister<SkillBase> SKILL= DeferredRegister.create(ROMRegistres.SKILL, MODID);
    //Commando
    public static final DeferredHolder<SkillBase,SkillBase> doubletap = SKILL.register("doubletap", DoubleTap::new);


}
