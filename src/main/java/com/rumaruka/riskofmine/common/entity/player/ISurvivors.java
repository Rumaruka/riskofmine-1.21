package com.rumaruka.riskofmine.common.entity.player;

import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.enumeration.TypeDamage;
import net.minecraft.world.entity.player.Player;

public interface ISurvivors {

    Survivors survivors();

    static ISurvivors get(Player player) {
        return ((IPlayerSurvivorsBridge) player).riskofmine$getSurvivor();
    }

    static void set(Player player) {
        ((IPlayerSurvivorsBridge) player).riskofmine$setSurvivor(get(player));
    }

    static void copyOnDeath(Player oldPlayer, Player newPlayer) {
        final ISurvivors oldInfo = get(oldPlayer);
        final ISurvivors newInfo = get(newPlayer);
        set(newPlayer);
        newInfo.copyOnDeath(oldInfo);
    }

    default float getHealth() {
        return survivors().getHealth();
    }


    default float getDamage() {
        return survivors().getDamage();
    }

    default void setDamage(float damage) {
        survivors().setDamage(damage);
    }

    default float getHealthRegen() {
        return survivors().getHealth_regen();
    }


    default TypeDamage getTypeDamage() {
        return survivors().getTypeDamage();
    }



    default int getArmor() {
        return survivors().getArmor();
    }

    default float getSpeed() {
        return survivors().getSpeed();
    }



    default void setName(String name) {
        survivors().setName(name);
    }

    default String getName() {
        return survivors().getName();
    }
  //  void onClientUpdate(PlayerInfoPacket packet);

    void copyOnDeath(ISurvivors info);
}
