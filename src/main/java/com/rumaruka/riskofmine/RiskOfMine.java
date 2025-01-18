package com.rumaruka.riskofmine;


import com.rumaruka.riskofmine.api.registry.ROMRegistry;
import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.client.ROMEntityRegister;
import com.rumaruka.riskofmine.client.event.ROMScreenEvent;
import com.rumaruka.riskofmine.client.screen.overlay.ROMOverlayRender;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.datagen.worldgen.ROMFeatures;
import com.rumaruka.riskofmine.init.*;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverlayPacket;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.zeith.api.registry.RegistryMapping;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.proxy.HLConstants;
import top.theillusivec4.curios.api.CuriosApi;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static com.rumaruka.riskofmine.client.screen.overlay.ROMOverlayRender.KEY_SHOW_OVERLAYS;


@Mod(MODID)
public class RiskOfMine {
    public static final String MODID = "riskofmine";
    public static final Logger logger = LogManager.getLogger(MODID);
    public static final Registry<SkillBase> SKILLS = new RegistryBuilder<>(ROMRegistry.SKILL).sync(false).create();


    public RiskOfMine(IEventBus bus) {

        LanguageAdapter.registerMod(MODID);

        ROMAttachment.setup(bus);


        ROMSounds.REGISTER.register(bus);
        ROMParticles.PARTICLES.register(bus);
        ROMEffects.EFFECTS.register(bus);
        ROMEffects.POTIONS.register(bus);

        logger.info("Skills: {}", ROMSkills.DOUBLE_TAP);

        bus.addListener(RiskOfMine::setup);
        bus.addListener(RiskOfMine::clientSetup);

        NeoForge.EVENT_BUS.addListener(ROMScreenEvent::screenEvent);

        ROMFeatures.registerFeatures(bus);
        bus.addListener(this::newRegister);
        bus.addListener(ROMOverlayRender::registerKeys);
        bus.addListener(SkillBase::registerKeys);

    }

    private static void setup(final FMLCommonSetupEvent evt) {

        ROMItems.Items.getAllItem().forEach(item -> {
            if (item instanceof BaseCollectablesItem baseCollectablesItem) {
                CuriosApi.registerCurio(item, baseCollectablesItem);
            }
        });
    }


    private static void clientSetup(final FMLClientSetupEvent event) {


        ROMEntityRegister.renderEntity();


    }

    private void newRegister(NewRegistryEvent event) {
        RegistryMapping.report(SkillBase.class,SKILLS,false);
        event.register(SKILLS)  ;
    }


    @SubscribeEvent
    public static void inputEvent(InputEvent.Key event) {

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
