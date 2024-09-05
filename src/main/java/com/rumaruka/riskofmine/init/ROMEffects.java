package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.effect.BleedEffect;
import com.rumaruka.riskofmine.common.effect.NullifiedEffect;
import com.rumaruka.riskofmine.common.effect.StunEffect;
import com.rumaruka.riskofmine.common.entity.elite_effect.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import static com.rumaruka.riskofmine.RiskOfMine.MODID;

public class ROMEffects {
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MODID);
    /**
     * POTIONS FOR TESTING EFFECTS!
     */
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, MODID);


    public static final DeferredHolder<MobEffect,MobEffect> STUN = EFFECTS.register("stun", () -> new StunEffect(MobEffectCategory.HARMFUL, 0x2A2D2E));
    public static final DeferredHolder<MobEffect,MobEffect> BLEED = EFFECTS.register("bleed", () -> new BleedEffect(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> NULLIFIED = EFFECTS.register("nullified", () -> new NullifiedEffect(MobEffectCategory.BENEFICIAL, 5646433));


    public static final DeferredHolder<MobEffect,MobEffect> BLAZING = EFFECTS.register("blazing", () -> new BlazingMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> CELESTINE = EFFECTS.register("celestine", () -> new CelestineMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> GLACIAL = EFFECTS.register("glacial", () -> new GlacialMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> MALACHITE = EFFECTS.register("malachite", () -> new MalachiteMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> MALACHITE_ELITES = EFFECTS.register("malachite_elites", () -> new MalachiteElitesMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> OVERLOADING = EFFECTS.register("overloading", () -> new OverloadingMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> OVERLOADING_ELITES = EFFECTS.register("overloading_elites", () -> new OverloadingMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> PERFECTED = EFFECTS.register("perfected", () -> new PerfectedMobs(MobEffectCategory.BENEFICIAL, 5646433));
    public static final DeferredHolder<MobEffect,MobEffect> MENDING = EFFECTS.register("mending", () -> new MendingMobs(MobEffectCategory.BENEFICIAL, 5646433));
}
