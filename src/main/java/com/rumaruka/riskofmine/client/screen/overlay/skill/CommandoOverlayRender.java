package com.rumaruka.riskofmine.client.screen.overlay.skill;

import com.mojang.blaze3d.systems.RenderSystem;
import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import com.rumaruka.riskofmine.common.skills.commando.SuppressiveFire;
import com.rumaruka.riskofmine.init.ROMSkills;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;

import java.awt.*;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

@EventBusSubscriber
public class CommandoOverlayRender {


    private static final ResourceLocation DOUBLE_TAP_IMG = rl("textures/gui/double_tap.png");
    private static final ResourceLocation SUPPRESSIVE_FIRE_IMG = rl("textures/gui/suppressive_fire.png");

    private static final Minecraft mc = ROMUtils.getMc();

    @SubscribeEvent
    public static void renderCommandoOverlay(CustomizeGuiOverlayEvent.Chat event) {
        GuiGraphics guiGraphics = event.getGuiGraphics();
        Player player = mc.player;
        if (player instanceof IPlayerSurvivorsBridge survivorsBridge){
            ISurvivors iSurvivors = survivorsBridge.riskofmine$getSurvivor();
            Survivors survivors = iSurvivors.survivors();
            if (survivors == Survivors.COMMANDO) {
                if (SkillBase.isSkillActive()){
                    renderDoubleTapSkill(guiGraphics);
                    renderSuppressiveFireSkill(guiGraphics);
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



        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, DOUBLE_TAP_IMG);

        // Включение смешивания и прозрачности
        RenderSystem.enableBlend();


        // Рисование изображения в центре экрана
        DoubleTap doubleTap = ROMSkills.DOUBLE_TAP;
        gui.blit(DOUBLE_TAP_IMG ,(width - imageWidth-420) / 2, (height - imageHeight +350) / 2, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        if (!doubleTap.isCooldown()){
            String toDisplay = String.valueOf(getDoubleTapDisplay(doubleTap));

            ROMUtils.drawString(gui, font, toDisplay, 27.5f, 385, color.getRGB());
        }
      // String toSkillD = String.valueOf(0);

      // ROMUtils.drawString(gui, font, toSkillD, 25, 385, color.getRGB());
        // Отключение смешивания

        RenderSystem.disableBlend();

    }

    private static void renderSuppressiveFireSkill(GuiGraphics gui) {
        Font font = mc.font;
        Color color = Color.RED;
        int width = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        int imageWidth = 32; // Ширина вашего изображения
        int imageHeight = 32; // Высота вашего изображения



        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, SUPPRESSIVE_FIRE_IMG);

        // Включение смешивания и прозрачности
        RenderSystem.enableBlend();


        // Рисование изображения в центре экрана
        SuppressiveFire doubleTap = ROMSkills.SUPPRESSIVE_FIRE;
        gui.blit(SUPPRESSIVE_FIRE_IMG ,(width - imageWidth-250) / 2, (height - imageHeight +350) / 2, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);

        if (!doubleTap.isCooldown()){
            String toDisplay = String.valueOf(getSuppressiveFireDisplay(doubleTap));

            ROMUtils.drawString(gui, font, toDisplay, 110, 385, color.getRGB());
        }
        // String toDisplay = String.valueOf(0);

       //ROMUtils.drawString(gui, font, toDisplay, 110, 405, color.getRGB());


        RenderSystem.disableBlend();
    }


    private static int getDoubleTapDisplay(DoubleTap tap) {
        return tap.getCooldownCount();

    }

    private static int getSuppressiveFireDisplay(SuppressiveFire tap) {
        return tap.getCooldownCount();

    }
}
