package com.rumaruka.riskofmine.client.screen;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class SurvivorsSelectionScreen extends Screen {


    private static final ResourceLocation COMMANDO_SELECT = RiskOfMine.rl("survivors/commando_selected_final");
    private static final ResourceLocation COMMANDO = RiskOfMine.rl("survivors/commando_final");
    private static final ResourceLocation ARTIFICER_SELECT = RiskOfMine.rl("survivors/artificer_selected_final");
    private static final ResourceLocation ARTIFICER = RiskOfMine.rl("survivors/artificer_final");
    private static final ResourceLocation ACRID_SELECT = RiskOfMine.rl("survivors/acrid_selected_final");
    private static final ResourceLocation ACRID = RiskOfMine.rl("survivors/acrid_final");

    private static final Component COPYRIGHT_TEXT = Component.translatable("title.credits");
    public static boolean isCommando;
    public static boolean isAcrid;
    public static boolean isArtificer;

    public SurvivorsSelectionScreen() {
        super(Component.literal("Custom Map Selection"));
    }

    @Override
    protected void init() {
        int i = this.font.width(COPYRIGHT_TEXT);
        int j = this.width - i - 2;
        int k = 24;
        int l = this.height / 4 + 32;
        this.addRenderableWidget(
                getAcridButton(this,l)
        );


        this.addRenderableWidget(
                getCommandoButton(this,l)
        );
        this.addRenderableWidget(
                getArtificerButton(this,l)
        );


        this.addRenderableWidget(
                Button.builder(CommonComponents.GUI_BACK,
                                p_280917_ -> backToMenu())
                        .bounds(this.width / 2 + 82, this.height - 30, 72, 20)
                        .build()
        );



    }

    //        Button.builder(Component.literal("Commando"), button -> {
//        isAcrid = isArtificer = false;
//screen.width/2  + 82 , screen.height - 310 - (count * 3)
//        isCommando = true;
//
//    })
//            .bounds(this.width / 2 - 154, this.height - 5 - (count * 3), 150, 20)
//            .build()
    public ImageButton getCommandoButton(Screen screen, int height) {
        WidgetSprites sprites = new WidgetSprites(COMMANDO, COMMANDO_SELECT);
        return new ImageButton(screen.width/2  -  160 , height, 32, 32, sprites, b -> {
            isAcrid = isArtificer = false;

            isCommando = true;
        });
    }


    public ImageButton getArtificerButton(Screen screen,int height) {
        WidgetSprites sprites = new WidgetSprites(ARTIFICER, ARTIFICER_SELECT);
        return new ImageButton(screen.width / 2 - 128, height +1, 32, 32, sprites, b -> {
            isArtificer = true;

            isAcrid = isCommando = false;
        });
    }

    public ImageButton getAcridButton(Screen screen, int height) {
        WidgetSprites sprites = new WidgetSprites(ACRID, ACRID_SELECT);
        return new ImageButton(screen.width / 2 - 80, height  , 32, 32, sprites, b -> {
            isAcrid = true;

            isArtificer = isCommando = false;
        });
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
            ROMUtils.drawString(p_281549_, font, Component.literal("Health").append(String.valueOf(Survivors.ACRID.getHealth())), this.width / 2 - 144, this.height - 10, Color.RED.getRGB());

        }
        if (isCommando) {
            ROMUtils.drawString(p_281549_, font, Component.literal("Commando Selected"), this.width / 2 - 154, this.height - 20, Color.RED.getRGB());

        }
        if (isArtificer) {
            ROMUtils.drawString(p_281549_, font, Component.literal("Artificer Selected"), this.width / 2 - 154, this.height - 20, Color.RED.getRGB());

        }


    }


}
