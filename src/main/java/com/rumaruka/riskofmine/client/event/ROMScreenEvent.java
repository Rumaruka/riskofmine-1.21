package com.rumaruka.riskofmine.client.event;

import com.rumaruka.riskofmine.client.screen.SurvivorsSelectionScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.client.event.ScreenEvent;

public class ROMScreenEvent {


    public static void screenEvent(ScreenEvent.Init.Pre event) {
        Screen screen = event.getScreen();
        if (screen instanceof SelectWorldScreen selectWorldScreen) {
            event.addListener(getSurvivors(selectWorldScreen));

        }


    }

    public static Button getSurvivors(Screen screen) {
        return Button.builder(Component.translatable("riskofmine.choose.survivors"), b -> {
                    screen.getMinecraft().setScreen(new SurvivorsSelectionScreen());
                })

                .bounds(screen.width / 2 + 100, 10, 150, 25)
                .build();
    }
}
