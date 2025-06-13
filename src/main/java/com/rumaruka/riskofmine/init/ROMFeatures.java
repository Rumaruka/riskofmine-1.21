package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.datagen.worldgen.chests.features.ROMLargeChestFeature;
import com.rumaruka.riskofmine.datagen.worldgen.chests.features.ROMSmallChestFeature;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ROMFeatures {

    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, RiskOfMine.MODID);
    public static final DeferredHolder<Feature<?>, ROMSmallChestFeature> SMALL_CHEST = FEATURES.register("features_small_chest", () -> new ROMSmallChestFeature(NoneFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, ROMLargeChestFeature> LARGE_CHEST = FEATURES.register("features_large_chest", () -> new ROMLargeChestFeature(NoneFeatureConfiguration.CODEC));

    public static void registerFeatures(IEventBus bus) {
        FEATURES.register(bus);
    }


}
