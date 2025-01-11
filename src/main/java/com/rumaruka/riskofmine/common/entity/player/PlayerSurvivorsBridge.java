package com.rumaruka.riskofmine.common.entity.player;

import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.api.enumeration.TypeDamage;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.SurvivorsPacket;
import net.minecraft.world.entity.player.Player;

public class PlayerSurvivorsBridge implements ISurvivors {
    protected final Player player;

    private Survivors survivors;

    public PlayerSurvivorsBridge(Player player, Survivors survivors) {
        this.player = player;
        this.survivors = survivors;


    }

    @Override
    public Survivors survivors() {
        return survivors;
    }

    public void setSurvivors(Survivors survivors) {
        this.survivors = survivors;
        ROMNetwork.sendToServer(new SurvivorsPacket(player.getId()));


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
