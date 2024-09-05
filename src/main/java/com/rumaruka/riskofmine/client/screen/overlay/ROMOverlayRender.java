package com.rumaruka.riskofmine.client.screen.overlay;

import com.rumaruka.riskofmine.common.cap.Barrier;
import com.rumaruka.riskofmine.common.cap.Lunar;
import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.cap.Shields;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

@EventBusSubscriber
public class ROMOverlayRender {

    public static KeyMapping KEY_SHOW_OVERLAYS = new KeyMapping("key.mapping.show_overlay.name", GLFW.GLFW_KEY_M, "key.mapping.category.name");

    private static final Minecraft mc = ROMUtils.getMc();


    public static void registerKeys(RegisterKeyMappingsEvent e) {
        e.register(KEY_SHOW_OVERLAYS);
    }


    @SubscribeEvent
    public static void renderOverlay(CustomizeGuiOverlayEvent.Chat event) {
        if (!KEY_SHOW_OVERLAYS.isDown()) {
            renderNearbyMoneyDisplay(event.getGuiGraphics());
            renderNearbyLunarDisplay(event.getGuiGraphics());
        } else {
            renderNearbyShieldsDisplay(event.getGuiGraphics());
            renderNearbyBarrierDisplay(event.getGuiGraphics());

        }

    }

    private static void renderNearbyMoneyDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Money money = Money.get(player);
            String toDisplay = getMoneyDisplay(money);
            Color color = Color.YELLOW;
            ROMUtils.drawString(stack, font, toDisplay, 27.5f, 20, color.getRGB());
        }
    }


    private static void renderNearbyBarrierDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Barrier barrier = Barrier.get(player);
            String toDisplay = getBarrierDisplay(barrier);
            Color color = Color.ORANGE;
            ROMUtils.drawString(stack, font, toDisplay, 27.5f, 20, color.getRGB());
        }
        pose.popPose();
    }


    private static void renderNearbyLunarDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Lunar lunar = Lunar.get(player);
            String toDisplay = getLunarDisplay(lunar);
            Color color = Color.BLUE;
            ROMUtils.drawString(stack, font, toDisplay, 27.5f, 30, color.getRGB());
        }
        pose.popPose();
    }

        private static void renderNearbyShieldsDisplay(GuiGraphics stack) {
        var pose = stack.pose();
        pose.pushPose();
        Player player = mc.player;
        Font font = mc.font;
        if (player != null && !player.isDeadOrDying()) {
            Shields shields = Shields.get(player);
            String toDisplay = getShieldsDisplay(shields);
            Color color = Color.BLUE;
            ROMUtils.drawString(stack, font, toDisplay, 27.5f, 30, color.getRGB());
        }
    }

    private static String getMoneyDisplay(Money money) {
        int currentMoney = money.getCurrentMoney();
        return I18n.get("riskofmine.currentmoney.name") + currentMoney;

    }

    private static String getLunarDisplay(Lunar lunar) {
        int currentLunar = lunar.getCurrentLunar();
        return I18n.get("riskofmine.currentlunar.name") + currentLunar;

    }


    private static String getShieldsDisplay(Shields shields) {
        int shieldsCurrent = shields.getCurrentShields();
        return I18n.get("riskofmine.currentshields.name") + shieldsCurrent;

    }
//
    private static String getBarrierDisplay(Barrier barrier) {
        int barrierCurrent = barrier.getCurrentBarrier();
        return I18n.get("riskofmine.currentbarrier.name") + barrierCurrent;

    }
}
