package com.rumaruka.riskofmine.datagen;

import com.rumaruka.riskofmine.RiskOfMine;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static net.neoforged.fml.common.EventBusSubscriber.Bus.MOD;


@EventBusSubscriber(modid = RiskOfMine.MODID, bus = MOD)
public class DataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = dataGenerator.getPackOutput();
        if (event.includeServer()) {
            //dataGenerator.addProvider(event.includeServer(), new ROMLootTableProvider(packOutput));
        }
    }
}