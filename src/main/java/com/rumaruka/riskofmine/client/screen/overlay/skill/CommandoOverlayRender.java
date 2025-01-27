package com.rumaruka.riskofmine.client.screen.overlay.skill;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import com.rumaruka.riskofmine.common.skills.commando.PhaseRound;
import com.rumaruka.riskofmine.common.skills.commando.SuppressiveFire;
import com.rumaruka.riskofmine.common.skills.commando.TacticalDive;
import com.rumaruka.riskofmine.init.ROMSkills;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;

import java.awt.*;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

@EventBusSubscriber
public class CommandoOverlayRender {


    private static final ResourceLocation DOUBLE_TAP_IMG = rl("textures/gui/skills/commando/double_tap.png");
    private static final ResourceLocation SUPPRESSIVE_FIRE_IMG = rl("textures/gui/skills/commando/suppressive_fire.png");
    private static final ResourceLocation PHASE_ROUND_IMG = rl("textures/gui/skills/commando/phase_round.png");
    private static final ResourceLocation TACTICAL_DIVE_IMG = rl("textures/gui/skills/commando/tactical_dive.png");

    private static final Minecraft mc = ROMUtils.getMc();

    @SubscribeEvent
    public static void renderCommandoOverlay(CustomizeGuiOverlayEvent.Chat event) {
        GuiGraphics guiGraphics = event.getGuiGraphics();
        Player player = mc.player;
        if (player instanceof IPlayerSurvivorsBridge survivorsBridge) {
            ISurvivors iSurvivors = survivorsBridge.riskofmine$getSurvivor();
            Survivors survivors = iSurvivors.survivors();
            if (survivors == Survivors.COMMANDO) {
                if (SkillBase.isSkillActive()) {
                    renderDoubleTapSkill(guiGraphics);
                    renderSuppressiveFireSkill(guiGraphics);
                    renderTacticalDiveSkill(guiGraphics);
                    renderPhaseRoundSkill(guiGraphics);
                }

            }
        }


        //

    }


    private static void renderDoubleTapSkill(GuiGraphics gui) {
        Font font = mc.font;
        Color color = Color.RED;
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int imageWidth = 32; // Ширина вашего изображения
        int imageHeight = 32; // Высота вашего изображения

        int x = mc.getWindow().getX();
        int y = mc.getWindow().getY();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();
        int posX = x - w;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, DOUBLE_TAP_IMG);

        // Включение смешивания и прозрачности
        RenderSystem.enableBlend();


        // Рисование изображения в центре экрана
        DoubleTap doubleTap = ROMSkills.DOUBLE_TAP;
        if (mc.getWindow().isFullscreen()) {
            gui.blit(DOUBLE_TAP_IMG, w - (h / 2 + 340)/*(width - imageWidth-420) / 2*/, (h / 2 + 80), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getDoubleTapDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, w - (h / 2 + 325), (h / 2 + 70), color.getRGB());
            }

        } else {
            gui.blit(DOUBLE_TAP_IMG, 0, (h / 2 + 80 + 20), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getDoubleTapDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, 10, (h / 2 + 70 + 20), color.getRGB());
            }
        }




        RenderSystem.disableBlend();

    }

    private static void renderPhaseRoundSkill(GuiGraphics gui) {
        Font font = mc.font;
        Color color = Color.RED;
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int imageWidth = 32; // Ширина вашего изображения
        int imageHeight = 32; // Высота вашего изображения

        int x = mc.getWindow().getX();
        int y = mc.getWindow().getY();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, PHASE_ROUND_IMG);

        // Включение смешивания и прозрачности
        RenderSystem.enableBlend();


        // Рисование изображения в центре экрана
        PhaseRound doubleTap = ROMSkills.PHASE_ROUND;
        if (mc.getWindow().isFullscreen()) {
            gui.blit(PHASE_ROUND_IMG, w - (h / 2 + 340 - 33)/*(width - imageWidth-420) / 2*/, (h / 2 + 80), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getPhaseRoundDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay,  w - (h / 2 +290), (h / 2 + 70), color.getRGB());
            }


        } else {
            gui.blit(PHASE_ROUND_IMG,34, (h / 2 + 80 + 20), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getPhaseRoundDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, 45, (h / 2 + 70 + 20), color.getRGB());
            }
        }


        RenderSystem.disableBlend();

    }

    private static void renderTacticalDiveSkill(GuiGraphics gui) {
        Font font = mc.font;
        Color color = Color.RED;
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int imageWidth = 32; // Ширина вашего изображения
        int imageHeight = 32; // Высота вашего изображения

        int x = mc.getWindow().getX();
        int y = mc.getWindow().getY();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TACTICAL_DIVE_IMG);

        // Включение смешивания и прозрачности
        RenderSystem.enableBlend();


        // Рисование изображения в центре экрана
        TacticalDive doubleTap = ROMSkills.TACTICAL_DIVE;
        if (mc.getWindow().isFullscreen()) {
            gui.blit(TACTICAL_DIVE_IMG, w - (h / 2 + 340 - 33 - 33)/*(width - imageWidth-420) / 2*/, (h / 2 + 80), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getTacticalDiveDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, w - (h / 2 + 260), (h / 2 + 70), color.getRGB());
            }


        } else {
            gui.blit(TACTICAL_DIVE_IMG, 34*2, (h / 2 + 80 + 20), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getTacticalDiveDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, 34*2+10, (h / 2 + 70 + 20), color.getRGB());
            }

        }


        RenderSystem.disableBlend();

    }

    private static void renderSuppressiveFireSkill(GuiGraphics gui) {
        Font font = mc.font;
        Color color = Color.RED;

        int imageWidth = 32;
        int imageHeight = 32;
        int x = mc.getWindow().getX();
        int y = mc.getWindow().getY();
        int w = mc.getWindow().getGuiScaledWidth();
        int h = mc.getWindow().getGuiScaledHeight();


        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SUPPRESSIVE_FIRE_IMG);


        RenderSystem.enableBlend();


        SuppressiveFire doubleTap = ROMSkills.SUPPRESSIVE_FIRE;

        if (mc.getWindow().isFullscreen()) {

            gui.blit(SUPPRESSIVE_FIRE_IMG, w - (h / 2 + 340 - 33 - 33 - 33), (h / 2 + 80), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);


            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getSuppressiveFireDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, 34*2+34, (h / 2 + 70), color.getRGB());
            }


        } else {
            gui.blit(SUPPRESSIVE_FIRE_IMG, 34*2+34, (h / 2 + 80 + 20), 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);


            if (!doubleTap.isCooldown()) {
                String toDisplay = String.valueOf(getSuppressiveFireDisplay(doubleTap));

                ROMUtils.drawString(gui, font, toDisplay, 34*2+34+10, (h / 2 + 70 + 20), color.getRGB());
            }
        }


//  String toDisplay = String.valueOf(0);
//
//  ROMUtils.drawString(gui, font, toDisplay,  w - (h / 2 + 305),  (h / 2 + 70), color.getRGB());


        RenderSystem.disableBlend();
    }


    private static int getDoubleTapDisplay(DoubleTap tap) {
        return tap.getCooldownCount();

    }

    private static int getSuppressiveFireDisplay(SuppressiveFire tap) {
        return tap.getCooldownCount();

    }

    private static int getPhaseRoundDisplay(PhaseRound tap) {
        return tap.getCooldownCount();

    }

    private static int getTacticalDiveDisplay(TacticalDive tap) {
        return tap.getCooldownCount();

    }
}
