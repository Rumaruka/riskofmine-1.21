package com.rumaruka.riskofmine.client.event;

import com.rumaruka.riskofmine.client.screen.*;
import com.rumaruka.riskofmine.init.ROMContainerTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ROMClientEvent {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event){
         event.register(ROMContainerTypes.SMALL_CHEST, BaseChestScreen::new);
         event.register(ROMContainerTypes.LARGE_CHEST, BaseChestScreen::new);
         event.register(ROMContainerTypes.LEGENDARY_CHEST, BaseChestScreen::new);
         event.register(ROMContainerTypes.LUNAR_CHEST, BaseChestScreen::new);
         event.register(ROMContainerTypes.MULTI_SHOP, BaseShopScreen::new);
         event.register(ROMContainerTypes.EQUIPMENT_TRIPLE_BARREL, BaseShopScreen::new);
    }
}
