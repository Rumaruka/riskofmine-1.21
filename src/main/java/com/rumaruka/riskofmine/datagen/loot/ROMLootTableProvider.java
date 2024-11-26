package com.rumaruka.riskofmine.datagen.loot;

import com.mojang.blaze3d.MethodsReturnNonnullByDefault;
import com.rumaruka.riskofmine.datagen.loot.chests.ChestLootTableROM;
import com.rumaruka.riskofmine.datagen.loot.chests.ROMChestLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;

import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ROMLootTableProvider extends LootTableProvider {

    private final List<SubProviderEntry> lootTables = List.of(new LootTableProvider.SubProviderEntry(ChestLootTableROM::new, LootContextParamSets.CHEST));

    public ROMLootTableProvider(PackOutput pOutputs, CompletableFuture<HolderLookup.Provider> provider) {
        super(pOutputs, ROMLootTables.all(), ROMChestLootTableProvider.create(pOutputs,provider).getTables(),provider);
    }

    @Override
    public List<SubProviderEntry> getTables() {
        return lootTables;
    }




}
