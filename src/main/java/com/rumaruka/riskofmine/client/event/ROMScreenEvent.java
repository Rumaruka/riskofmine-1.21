package com.rumaruka.riskofmine.client.event;

import com.rumaruka.riskofmine.client.screen.SurvivorsSelectionScreen;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.ExperimentsScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.ScreenEvent;

public class ROMScreenEvent {


    public static void screenEvent(ScreenEvent.Init.Pre event) {

        Screen screen = event.getScreen();
        if (screen instanceof SelectWorldScreen selectWorldScreen) {
            event.addListener(getSurvivors(selectWorldScreen));
        }
        if (screen instanceof ExperimentsScreen experimentsScreen) {
            event.addListener(ROMUtils.getTextWidget(Component.translatable("riskofmine.select_survivors.title"), 162, 200));
            event.addListener(getSurvivors(experimentsScreen, 30, 200));
        }


    }


    private static Button getSurvivors(Screen screen) {
        return Button.builder(Component.translatable("riskofmine.choose.survivors"), b -> {
                    screen.getMinecraft().setScreen(new SurvivorsSelectionScreen(screen));
                })

                .bounds(screen.width / 2 + 110, 10, 125, 20)
                .build();
    }

    private static Button getSurvivors(Screen screen, int x, int y) {
        return Button.builder(Component.translatable("riskofmine.choose.survivors"), b -> {
                    screen.getMinecraft().setScreen(new SurvivorsSelectionScreen(screen));
                })

                .bounds(screen.width / 2 + x, y, 125, 20)
                .build();
    }


}

