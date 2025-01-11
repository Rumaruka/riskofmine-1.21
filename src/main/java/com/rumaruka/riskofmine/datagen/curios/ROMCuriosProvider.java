package com.rumaruka.riskofmine.datagen.curios;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class ROMCuriosProvider extends CuriosDataProvider {
    public ROMCuriosProvider( PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(RiskOfMine.MODID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider registries, ExistingFileHelper fileHelper) {
        this.createEntities("rom_curios_entities")
                .addPlayer()
                .addSlots("curio")


        ;
        createSlot("curio").size(6);


    }
}
