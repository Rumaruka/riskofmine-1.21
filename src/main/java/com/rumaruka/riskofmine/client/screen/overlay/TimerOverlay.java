package com.rumaruka.riskofmine.client.screen.overlay;

import com.mojang.blaze3d.platform.Window;
import com.rumaruka.riskofmine.common.cap.Timer;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import org.jetbrains.annotations.NotNull;

import static com.rumaruka.riskofmine.RiskOfMine.rl;


public class TimerOverlay {
    public static final ResourceLocation diffManager = rl( "textures/diffMeter.png");
    double zLevel = 0D;


    public static void renderTick(CustomizeGuiOverlayEvent.Chat event){
        GuiGraphics gui = event.getGuiGraphics();
        Window window = event.getWindow();
        int i = gui.guiWidth();
        int j = gui.guiHeight();
        gui.pose().pushPose();
        gui.blit(diffManager, 0, 0, 0, 0, i, j, i, j);



        Timer timer = Timer.get(ROMUtils.getPlayer());
        int currentTimer = timer.getCurrentTimer();
        String str = getString(currentTimer);

        ROMUtils.drawString(gui, ROMUtils.getMc().font, str, 10, 10, 0xffffff);
        gui.pose().popPose();
    }

    private static @NotNull String getString(int currentTimer) {
        String str = "dl.hahaha+";
        if (currentTimer < 1000000000)
            str = "dl.hahaha";
        if (currentTimer < 100000000)
            str = "dl.imcomingforyou";
        if (currentTimer <100000000)
            str = "dl.iseeyou";
        if (currentTimer < 10000000)
            str = "dl.impossible";
        if (currentTimer < 1000000)
            str = "dl.insane";
        if (currentTimer < 10000)
            str = "dl.veryHard";
        if (currentTimer <1000)
            str = "dl.hard";
        if (currentTimer < 1000)
            str = "dl.medium";
        if (currentTimer < 100)
            str = "dl.easy";
        if (currentTimer < 10)
            str = "dl.veryEasy";
        return str;
    }
}
