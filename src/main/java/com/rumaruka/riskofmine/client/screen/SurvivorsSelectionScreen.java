package com.rumaruka.riskofmine.client.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import net.minecraft.network.chat.Component;

public class SurvivorsSelectionScreen extends Screen {


    public SurvivorsSelectionScreen() {
        super(Component.literal("Custom Map Selection"));
    }

    @Override
    protected void init() {
        this.addRenderableWidget(
                Button.builder(
                                Component.translatable("choose.survivors"),
                                p_101373_ -> {

                                }
                        )
                        .bounds(this.width / 2 + 4, this.height - 28, 72, 20)
                        .build()
        );

    }
}
