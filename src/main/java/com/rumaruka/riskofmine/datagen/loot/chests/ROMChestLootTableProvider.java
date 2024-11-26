package com.rumaruka.riskofmine.datagen.loot.chests;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ROMChestLootTableProvider {

    public static LootTableProvider create(PackOutput p_250807_, CompletableFuture<HolderLookup.Provider> p_324422_) {
        return new LootTableProvider(
                p_250807_,
                BuiltInLootTables.all(),
                List.of(
                        new LootTableProvider.SubProviderEntry(ChestLootTableROM::new, LootContextParamSets.CHEST)

                ),
                p_324422_
        );
    }
}
