package com.rumaruka.riskofmine.datagen.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.datagen.worldgen.chests.features.ROMSmallChestFeature;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.BonusChestFeature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.List;

public class ROMFeatures{

    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, RiskOfMine.MODID);
    public static final DeferredHolder<Feature<?>, ROMSmallChestFeature> SMALL_CHEST = FEATURES.register("features_small_chest", ()->new ROMSmallChestFeature(NoneFeatureConfiguration.CODEC));

    public static void registerFeatures(IEventBus bus) {
        FEATURES.register(bus);
    }
//    public static final ROMSmallChestFeature SMALL_CHEST = register("features_small_chest", new ROMSmallChestFeature(NoneFeatureConfiguration.CODEC));
//
//
//
//
//
//
//
//    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String p_65808_, F p_65809_) {
//        return Registry.register(BuiltInRegistries.FEATURE, ResourceLocation.fromNamespaceAndPath(RiskOfMine.MODID, p_65808_), p_65809_);
//    }

}
