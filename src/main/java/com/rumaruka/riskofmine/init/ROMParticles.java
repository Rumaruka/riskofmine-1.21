package com.rumaruka.riskofmine.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import static com.rumaruka.riskofmine.RiskOfMine.MODID;

public class ROMParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MODID);
    public static final DeferredHolder<ParticleType<?>,SimpleParticleType> FOCUS_CRYSTAL = PARTICLES.register("focus_crystal", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>,SimpleParticleType> STUN_PARTICLES = PARTICLES.register("stun", () -> new SimpleParticleType(true));
}
