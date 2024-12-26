package com.rumaruka.riskofmine.client.screen;

import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.common.entity.player.PlayerSurvivorsBridge;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.storage.LevelSummary;

public class SurvivorsSelectionScreen extends Screen {

    public  static boolean isCommando;
    public static boolean isAcrid;
    public static boolean isArtificer;



    public SurvivorsSelectionScreen() {
        super(Component.literal("Custom Map Selection"));
    }

    @Override
    protected void init() {
        this.addRenderableWidget(
                Button.builder(Component.literal("Acrid"), button -> {
                            isAcrid = true;

                            isArtificer= isCommando=false;
                        })
                        .bounds(this.width / 2 - 154, this.height - 52, 150, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(Component.literal("Artificer"), button -> {
                            isArtificer = true;

                            isAcrid= isCommando=false;
                        })
                        .bounds(this.width / 2 - 154, this.height - 32, 150, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(Component.literal("Commando"), button -> {
                            isAcrid= isArtificer = false;

                            isCommando=true;
                        })
                        .bounds(this.width / 2 - 154, this.height - 32, 150, 20)
                        .build()
        );
        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_BACK,
                                p_280917_ -> this.minecraft.setScreen( new SelectWorldScreen(new TitleScreen())))
                        .bounds(this.width / 2 + 82, this.height - 28, 72, 20)
                        .build()
        );

    }

}

