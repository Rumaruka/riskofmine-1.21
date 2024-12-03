package com.rumaruka.riskofmine.datagen;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.datagen.curios.ROMCuriosProvider;
import com.rumaruka.riskofmine.datagen.loot.ROMLootTableProvider;
import com.rumaruka.riskofmine.datagen.worldgen.ROMWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static net.neoforged.fml.common.EventBusSubscriber.Bus.MOD;


@EventBusSubscriber(modid = RiskOfMine.MODID, bus = MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        dataGenerator.addProvider(event.includeServer(), new ROMLootTableProvider(packOutput, lookupProvider));
        dataGenerator.addProvider(event.includeServer(), new ROMCuriosProvider(packOutput, existingFileHelper, lookupProvider));
        dataGenerator.addProvider(event.includeServer(), new ROMWorldGenProvider(packOutput, lookupProvider));

    }
}