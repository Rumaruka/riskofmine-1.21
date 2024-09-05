package com.rumaruka.riskofmine.init;


import com.rumaruka.riskofmine.common.inventory.ChestInventory;
import com.rumaruka.riskofmine.common.inventory.ChestShopInventory;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ROMContainerTypes {
    @RegistryName("small_chest")
    MenuType<ChestInventory> SMALL_CHEST = IMenuTypeExtension.create((windowId, inv, data) -> ChestInventory.createCommonContainer(windowId, inv));
    @RegistryName("large_chest")
    MenuType<ChestInventory> LARGE_CHEST =   IMenuTypeExtension.create((windowId, inv, data) -> ChestInventory.createLargeContainer(windowId, inv));
    @RegistryName("legend_chest")
    MenuType<ChestInventory> LEGENDARY_CHEST =  IMenuTypeExtension.create((windowId, inv, data) -> ChestInventory.createLegendaryContainer(windowId, inv));
    @RegistryName("lunar_chest")
    MenuType<ChestInventory> LUNAR_CHEST =  IMenuTypeExtension.create((windowId, inv, data) -> ChestInventory.createLunarContainer(windowId, inv));
    @RegistryName("multi_shop")
    MenuType<ChestShopInventory> MULTI_SHOP =  IMenuTypeExtension.create((windowId, inv, data) -> ChestShopInventory.createMultiShopContainer(windowId, inv));
    @RegistryName("equipment_triple_barrel")
    MenuType<ChestShopInventory> EQUIPMENT_TRIPLE_BARREL = IMenuTypeExtension.create((windowId, inv, data) -> ChestShopInventory.createMultiShopContainer(windowId, inv));


}
