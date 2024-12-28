package com.rumaruka.riskofmine.client.screen;

import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

import java.awt.*;

public class SurvivorsSelectionScreen extends Screen {


    public static boolean isCommando;
    public static boolean isAcrid;
    public static boolean isArtificer;

    public SurvivorsSelectionScreen() {
        super(Component.literal("Custom Map Selection"));
    }

    @Override
    protected void init() {
        int count = 20;

        this.addRenderableWidget(


                Button.builder(Component.literal("Acrid"), button -> {
                            isAcrid = true;

                            isArtificer = isCommando = false;

                        })
                        .bounds(this.width / 2 - 154, this.height - 5 - count, 150, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(Component.literal("Artificer"), button -> {
                            isArtificer = true;

                            isAcrid = isCommando = false;

                        })
                        .bounds(this.width / 2 - 154, this.height -5 - (count * 2), 150, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(Component.literal("Commando"), button -> {
                            isAcrid = isArtificer = false;

                            isCommando = true;

                        })
                        .bounds(this.width / 2 - 154, this.height - 5 - (count * 3), 150, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_BACK,
                                p_280917_ -> backToMenu())
                        .bounds(this.width / 2 + 82, this.height - 20, 72, 20)
                        .build()
        );


    }

    private void backToMenu() {
        this.minecraft.setScreen(new SelectWorldScreen(new TitleScreen()));
    }

    @Override
    public void render(GuiGraphics p_281549_, int p_281550_, int p_282878_, float p_282465_) {
        super.render(p_281549_, p_281550_, p_282878_, p_282465_);
        renderSurvivorsStatus(p_281549_);
    }

    private void renderSurvivorsStatus(GuiGraphics p_281549_) {

        if (isAcrid) {
            ROMUtils.drawString(p_281549_, font, Component.literal("Acrid Selected"), this.width / 2 - 154, this.height - 20, Color.RED.getRGB());
            ROMUtils.drawString(p_281549_, font,Component.literal("Health").append(String.valueOf(Survivors.ACRID.getHealth())), this.width / 2 - 144, this.height - 10, Color.RED.getRGB());

        }
        if (isCommando) {
            ROMUtils.drawString(p_281549_, font, Component.literal("Commando Selected"), this.width / 2 - 154, this.height - 20, Color.RED.getRGB());

        }
        if (isArtificer) {
            ROMUtils.drawString(p_281549_, font, Component.literal("Artificer Selected"), this.width / 2 - 154, this.height - 20, Color.RED.getRGB());

        }


    }


}
