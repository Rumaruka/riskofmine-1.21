package com.rumaruka.riskofmine.common.events.skills_event;

import com.mojang.datafixers.kinds.IdF;
import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.skills.base.SkillBase;
import com.rumaruka.riskofmine.common.skills.commando.DoubleTap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class CommandoSkillsEvent {


    protected SkillBase skill;
    @SubscribeEvent
    public void onLeftClick(PlayerInteractEvent.LeftClickEmpty event){
        Player entity = event.getEntity();
        BlockPos pos = event.getPos();
        Direction face = event.getFace();

        // Your commando skills code here
        if (entity instanceof IPlayerSurvivorsBridge survivorsBridge){
            Survivors survivors = survivorsBridge.riskofmine$getSurvivor().survivors();

            if (survivors==Survivors.COMMANDO){
                if (skill instanceof DoubleTap doubleTap){

                }
             
            }
        }

    }
}
