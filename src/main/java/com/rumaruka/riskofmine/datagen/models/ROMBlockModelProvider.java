package com.rumaruka.riskofmine.datagen.models;

import net.minecraft.data.PackOutput;
import net.minecraft.data.models.ModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ROMBlockModelProvider extends BlockModelProvider {


    public ROMBlockModelProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
