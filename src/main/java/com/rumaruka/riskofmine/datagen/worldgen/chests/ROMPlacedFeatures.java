package com.rumaruka.riskofmine.datagen.worldgen.chests;


import com.rumaruka.riskofmine.RiskOfMine;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ROMPlacedFeatures {

    public static ResourceKey<PlacedFeature> OVER_CHEST_GEN = createKey("over_chest_gen");
 //   public static ResourceKey<PlacedFeature> NETHER_CHEST_GEN = createKey("nether_chest_gen");
 //   public static ResourceKey<PlacedFeature> END_CHEST_GEN = createKey("end_chest_gen");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder<ConfiguredFeature<?, ?>> overholder =
                configuredFeatures.getOrThrow(ROMConfiguredFeatures.OVERWORLD_CHEST);
//        Holder<ConfiguredFeature<?, ?>> netherholder =
//                configuredFeatures.getOrThrow(ROMConfiguredFeatures.NETHER_CHEST);
//        Holder<ConfiguredFeature<?, ?>> endholder =
//                configuredFeatures.getOrThrow(ROMConfiguredFeatures.END_CHEST);


//        register(context, OVER_CHEST_GEN, overholder, ROMChestGen.commonOrePlacements(
//                10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(240)
//        )));
       // register(context, NETHER_CHEST_GEN, netherholder, ROMChestGen.netherOrePlacements(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(200))));
       // register(context, END_CHEST_GEN, endholder, ROMChestGen.endOrePlacements(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64   ), VerticalAnchor.absolute(200))));
        PlacementUtils.register(
                context,
                OVER_CHEST_GEN,
                overholder,
                RarityFilter.onAverageOnceEvery(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(240)),
                BiomeFilter.biome()
        );

    }


    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(RiskOfMine.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> feature, List<PlacementModifier> placementModifiers) {
        context.register(key, new PlacedFeature(feature, placementModifiers));
    }
}
