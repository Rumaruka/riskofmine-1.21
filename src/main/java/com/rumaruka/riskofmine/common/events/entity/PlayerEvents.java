package com.rumaruka.riskofmine.common.events.entity;


import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber
public class PlayerEvents {
    @SubscribeEvent
    public static void onPlayerWithAbilites(PlayerTickEvent.Post evt){
        Player player = evt.getEntity();
        if (player instanceof IPlayerSurvivorsBridge survivors){
            ISurvivors iSurvivors = survivors.riskofmine$getSurvivor();
             player.sendSystemMessage(Component.literal(iSurvivors.getName()));
            player.getAttributes().addTransientAttributeModifiers(makeAttributeMap(iSurvivors));
        }
    }

    private static Multimap<Holder<Attribute>, AttributeModifier> makeAttributeMap( ISurvivors iSurvivors) {
        final Multimap<Holder<Attribute>, AttributeModifier> defaultModifiers;
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();

            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(RiskOfMine.rl("survivoirs_health"), iSurvivors.getHealth(), AttributeModifier.Operation.ADD_VALUE));
           // builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(RiskOfMine.rl("survivoirs_health"), iSurvivors.getSpeed(), AttributeModifier.Operation.ADD_VALUE));


        defaultModifiers = builder.build();
        return defaultModifiers;
    }
}
