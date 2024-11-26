package com.rumaruka.riskofmine.datagen.worldgen;

import com.rumaruka.riskofmine.RiskOfMine;

import com.rumaruka.riskofmine.datagen.worldgen.chests.ROMConfiguredFeatures;
import com.rumaruka.riskofmine.datagen.worldgen.chests.ROMPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ROMWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public ROMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, new RegistrySetBuilder()
                        .add(Registries.CONFIGURED_FEATURE, ROMConfiguredFeatures::bootstrap)
                        .add(Registries.PLACED_FEATURE, ROMPlacedFeatures::bootstrap)
                        .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ROMModifiers::bootstrap),
                Set.of(RiskOfMine.MODID));
    }
}

