package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.blocks.ScrapperBlock;
import com.rumaruka.riskofmine.common.blocks.WarbannerBlock;
import com.rumaruka.riskofmine.common.blocks.chest.LargeChestBlock;
import com.rumaruka.riskofmine.common.blocks.chest.LegendaryChestBlock;
import com.rumaruka.riskofmine.common.blocks.chest.LunarChestBlock;
import com.rumaruka.riskofmine.common.blocks.chest.SmallChestBlock;
import com.rumaruka.riskofmine.common.blocks.shop.EquipmentTripleBarrelBlock;
import com.rumaruka.riskofmine.common.blocks.shop.MultiShopBlock;
import org.zeith.hammerlib.annotations.Ref;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;


@SimplyRegister(creativeTabs = @Ref(value = RiskOfMine.class,field = "TAB"))
public interface ROMBlocks {
    @RegistryName("small_chest")
    SmallChestBlock SMALL_CHEST = new SmallChestBlock();
    @RegistryName("large_chest")
    LargeChestBlock LARGE_CHEST = new LargeChestBlock();
    @RegistryName("legendary_chest")
    LegendaryChestBlock LEGENDARY_CHEST = new LegendaryChestBlock();
    @RegistryName("lunar_chest")
    LunarChestBlock LUNAR_CHEST = new LunarChestBlock();
    @RegistryName("multi_shop")
    MultiShopBlock MULTI_SHOP = new MultiShopBlock();
    @RegistryName("warbanner_block")
    WarbannerBlock WARBANNER_BLOCK = new WarbannerBlock();
    @RegistryName("scrapper")
    ScrapperBlock SCRAPPER = new ScrapperBlock();
    @RegistryName("equipment_triple_barrel")
    EquipmentTripleBarrelBlock EQUIPMENT_TRIPLE_BARREL = new EquipmentTripleBarrelBlock();

//public static UtilityChestBlock UTILITY_CHEST = promise();
//public static HealingChestBlock HEALING_CHEST = promise();
//public static DamageChestBlock DAMAGE_CHEST = promise();
//public static RustyChestBlock RUSTY_CHEST = promise();
//public static EquipmentBarrelBlock EQUIPMENT_BARREL = promise();
//REGISTER.register("large_chest", LargeChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
//REGISTER.register("legendary_chest", LegendaryChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
//REGISTER.register("lunar_chest", LunarChestBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
//REGISTER.register("warbanner_block", WarbannerBlock::new).oneVarStateAndCubeAllModel();
//REGISTER.register("scrapper", ScrapperBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
//REGISTER.register("multi_shop", MultiShopBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());
//REGISTER.register("equipment_triple_barrel", EquipmentTripleBarrelBlock::new).oneVarStateAndCubeAllModel().defaultBlockItem(ROMCreativeTabs.tabROM.getKey());


}



