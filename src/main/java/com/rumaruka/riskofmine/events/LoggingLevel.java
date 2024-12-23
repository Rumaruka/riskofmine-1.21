package com.rumaruka.riskofmine.events;

import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@EventBusSubscriber
public class LoggingLevel {
    public static Screen WarningScreen = new ConfirmScreen(b -> {
        if (b) {
            Player player = ROMUtils.getPlayer();
        } else {
            Minecraft.getInstance().stop();
        }
    }, Component.translatable("gribtweaks.rumaruka.failure.title"), Component.translatable("gribtweaks.rumaruka.failure.notfound"), Component.translatable("gribtweaks.rumaruka.openlink"), Component.translatable("menu.quit"));
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player instanceof IPlayerSurvivorsBridge s){
            ISurvivors iSurvivors = s.riskofmine$getSurvivor();
            Survivors survivors = iSurvivors.survivors();
            if (survivors ==null){
                Minecraft.getInstance().setScreen(WarningScreen);
            }else {
                player.sendSystemMessage(Component.literal(survivors.getName()));
            }
        }


    }


}
