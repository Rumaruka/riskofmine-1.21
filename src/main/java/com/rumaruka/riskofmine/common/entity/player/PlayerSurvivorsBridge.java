package com.rumaruka.riskofmine.common.entity.player;

import com.rumaruka.riskofmine.api.Survivors;
import com.rumaruka.riskofmine.api.TypeDamage;
import lombok.Setter;
import net.minecraft.world.entity.player.Player;

public class PlayerSurvivorsBridge implements ISurvivors {
    protected final Player player;

    private Survivors survivors;

    public PlayerSurvivorsBridge(Player player, Survivors survivors) {
        this.player = player;
        this.survivors = survivors;

        setSurvivors(survivors);
    }

    @Override
    public Survivors survivors() {
        return survivors;
    }

    public void setSurvivors(Survivors survivors) {
        this.survivors = survivors();

    }

    @Override
    public float getHealth() {
        return survivors().getHealth();

    }


    @Override
    public float getDamage() {
        return survivors().getDamage();

    }


    @Override
    public float getHealthRegen() {
        return survivors().getHealth_regen();
    }


    @Override
    public TypeDamage getTypeDamage() {
        return survivors().getTypeDamage();
    }


    @Override
    public int getArmor() {
        return survivors().getArmor();

    }


    @Override
    public float getSpeed() {
        return survivors().getSpeed();
    }


    @Override
    public String getName() {
        return survivors().getName();
    }

    @Override
    public void copyOnDeath(ISurvivors info) {
        this.survivors = info.survivors();
        setSurvivors(info.survivors());
    }
}
