package com.rumaruka.riskofmine.utils;

import com.rumaruka.riskofmine.client.fx.FocusCrystalFX;
import com.rumaruka.riskofmine.init.ROMParticles;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;


import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static net.neoforged.fml.common.EventBusSubscriber.Bus.MOD;

@EventBusSubscriber(modid = MODID, bus = MOD)
public class ParticleUtils {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerParticle(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ROMParticles.FOCUS_CRYSTAL.get(), FocusCrystalFX.Factory::new);

    }
}
