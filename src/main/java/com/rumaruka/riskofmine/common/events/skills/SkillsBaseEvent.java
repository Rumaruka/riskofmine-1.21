//package com.rumaruka.riskofmine.common.events.skills;
//
//import com.rumaruka.riskofmine.api.Survivors;
//import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
//import com.rumaruka.riskofmine.common.skills.SkillBase;
//import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
//import net.minecraft.world.entity.Entity;
//import net.neoforged.bus.api.Event;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.tick.EntityTickEvent;
//
//@EventBusSubscriber
//public class SkillsBaseEvent   {
//
//    @SubscribeEvent
//    public static void onSkillsUpdate(EntityTickEvent.Post event){
//        Entity entity = event.getEntity();
//        if (entity instanceof IPlayerSurvivorsBridge survivorsBridge){
//            Survivors survivors = survivorsBridge.riskofmine$getSurvivor().survivors();
//            if (survivors ==Survivors.COMMANDO){
//                new DoubleTap().onPressSkills(100);
//            }
//        }
//    }
//}
