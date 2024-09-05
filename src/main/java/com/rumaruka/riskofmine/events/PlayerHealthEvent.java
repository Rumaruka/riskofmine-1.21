package com.rumaruka.riskofmine.events;

import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


public class PlayerHealthEvent extends PlayerEvent {
    public PlayerHealthEvent(Player player) {
        super(player);
    }


    public static class PickupHealth extends PlayerHealthEvent implements ICancellableEvent {
        private final HealthOrbEntity orb;

        public PickupHealth(Player player, HealthOrbEntity orb) {
            super(player);
            this.orb = orb;
        }

        public HealthOrbEntity getOrb() {
            return orb;
        }
    }
}
