package com.rumaruka.riskofmine.common.events.entity;

import com.rumaruka.riskofmine.api.entity.IBlazing;
import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.init.ROMEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@EventBusSubscriber
public class ElitesEvents {


    @SubscribeEvent
    public static void addOveloading(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();


        Level level = entity.level();
        if (!level.isClientSide()) {

            if (entity instanceof LivingEntity living) {
                ((IOverloading) living).setOverloading(living.hasEffect(ROMEffects.OVERLOADING));
            }



        }
    }
    @SubscribeEvent
    public static void addBlazing(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();


        Level level = entity.level();
        if (!level.isClientSide()) {

            if (entity instanceof LivingEntity living) {
                ((IBlazing) living).setBlazing(living.hasEffect(ROMEffects.BLAZING));
            }



        }
    }

}



