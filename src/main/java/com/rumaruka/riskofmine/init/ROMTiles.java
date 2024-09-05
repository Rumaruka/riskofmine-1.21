package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.client.tesr.SmallChestTESR;
import com.rumaruka.riskofmine.common.tiles.WarbannerTE;
import com.rumaruka.riskofmine.common.tiles.chest.LargeChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.LegendaryChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.LunarChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.SmallChestTE;
import com.rumaruka.riskofmine.common.tiles.shop.EquipmentTripleBarrelTE;
import com.rumaruka.riskofmine.common.tiles.shop.MultiShopTE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;
import org.zeith.hammerlib.annotations.client.TileRenderer;
import org.zeith.hammerlib.api.forge.BlockAPI;

@SimplyRegister
public interface ROMTiles {

    @RegistryName("small_chest")
    @TileRenderer(SmallChestTESR.class)
    BlockEntityType<SmallChestTE> SMALL_CHEST = BlockAPI.createBlockEntityType(SmallChestTE::new, ROMBlocks.SMALL_CHEST);
    @RegistryName("large_chest")
    BlockEntityType<LargeChestTE> LARGE_CHEST = BlockAPI.createBlockEntityType(LargeChestTE::new, ROMBlocks.LARGE_CHEST);
    @RegistryName("legendary_chest")
    BlockEntityType<LegendaryChestTE> LEGENDARY_CHEST = BlockAPI.createBlockEntityType(LegendaryChestTE::new, ROMBlocks.LEGENDARY_CHEST);
    @RegistryName("lunar_chest")
    BlockEntityType<LunarChestTE> LUNAR_CHEST = BlockAPI.createBlockEntityType(LunarChestTE::new, ROMBlocks.LUNAR_CHEST);
    @RegistryName("multi_shop")
    BlockEntityType<MultiShopTE> MULTI_SHOP = BlockAPI.createBlockEntityType(MultiShopTE::new, ROMBlocks.MULTI_SHOP);
    @RegistryName("equipment_triple_barrel")
    BlockEntityType<EquipmentTripleBarrelTE> EQUIPMENT_TRIPLE_BARREL = BlockAPI.createBlockEntityType(EquipmentTripleBarrelTE::new, ROMBlocks.EQUIPMENT_TRIPLE_BARREL);
    @RegistryName("warbanner_block")
    BlockEntityType<WarbannerTE> WARBANNER_BLOCK = BlockAPI.createBlockEntityType(WarbannerTE::new, ROMBlocks.WARBANNER_BLOCK);


}
