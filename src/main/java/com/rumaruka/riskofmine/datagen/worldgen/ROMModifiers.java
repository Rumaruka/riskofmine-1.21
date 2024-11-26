package com.rumaruka.riskofmine.datagen.worldgen;


import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.datagen.worldgen.chests.ROMPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ROMModifiers {

    protected static ResourceKey<BiomeModifier> ADD_CHEST = createKey("add_chest");
    protected static ResourceKey<BiomeModifier> ADD_NETHER_CHEST = createKey("add_nether_chest");
    protected static ResourceKey<BiomeModifier> ADD_END_CHEST = createKey("add_end_chest");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(
                ADD_CHEST,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                        HolderSet.direct(placedFeatures.getOrThrow(ROMPlacedFeatures.OVER_CHEST_GEN)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
        context.register(
                ADD_NETHER_CHEST,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_NETHER),
                        HolderSet.direct(placedFeatures.getOrThrow(ROMPlacedFeatures.NETHER_CHEST_GEN)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
        context.register(
                ADD_END_CHEST,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        biomes.getOrThrow(BiomeTags.IS_END),
                        HolderSet.direct(placedFeatures.getOrThrow(ROMPlacedFeatures.END_CHEST_GEN)),
                        GenerationStep.Decoration.UNDERGROUND_ORES
                )
        );
    }

    private static ResourceKey<BiomeModifier> createKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(RiskOfMine.MODID, name));
    }
}
