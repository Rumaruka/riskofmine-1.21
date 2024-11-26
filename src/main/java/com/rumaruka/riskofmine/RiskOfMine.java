package com.rumaruka.riskofmine;


import com.rumaruka.riskofmine.client.ROMEntityRegister;
import com.rumaruka.riskofmine.client.screen.overlay.ROMOverlayRender;
import com.rumaruka.riskofmine.init.*;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverlayPacket;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.proxy.HLConstants;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static com.rumaruka.riskofmine.client.screen.overlay.ROMOverlayRender.KEY_SHOW_OVERLAYS;


@Mod(MODID)
public class RiskOfMine {
    public static final String MODID = "riskofmine";
    public static final Logger logger = LogManager.getLogger(MODID);

    private static final ModList MOD_LIST = ModList.get();

    public RiskOfMine(IEventBus bus) {

        LanguageAdapter.registerMod(MODID);

        ROMAttachment.setup(bus);


        ROMSounds.REGISTER.register(bus);
        ROMParticles.PARTICLES.register(bus);
        ROMEffects.EFFECTS.register(bus);
        ROMEffects.POTIONS.register(bus);
        bus.addListener(RiskOfMine::clientSetup);


        bus.addListener(ROMOverlayRender::registerKeys);

    }


    private static void clientSetup(final FMLClientSetupEvent event) {



        ROMEntityRegister.renderEntity();


    }


    @SubscribeEvent
    public static void inputEvent(InputEvent.Key event) {

        if (ROMUtils.getMc().screen != null) return;
        if (KEY_SHOW_OVERLAYS.isDown() && event.getAction() == GLFW.GLFW_PRESS) {
            ROMNetwork.sendToServer(new OverlayPacket());
        }
    }


    @CreativeTab.RegisterTab
    public static final CreativeTab TAB = new CreativeTab(rl("rom"), b ->
            b.icon(ROMItems.ARMOR_PIERCING_ROUNDS::getDefaultInstance)
                    .title(Component.translatable("itemGroup." + RiskOfMine.MODID))
    ).putAfter(HLConstants.HL_TAB);



    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }


}
