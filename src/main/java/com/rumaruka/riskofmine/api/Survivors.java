package com.rumaruka.riskofmine.api;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.StringRepresentable;

public enum Survivors implements StringRepresentable {
    COMMANDO("commando",110f,12f,1.5f,TypeDamage.Ranged,0,7),

    ACRID("acrid", 160f, 15f, 2.5f, TypeDamage.Melee_Ranged, 20, 7),
    ARTIFICER("artificer", 110f, 12f, 1f, TypeDamage.Ranged, 0, 7);

    @Getter
    @Setter
    public float health;
    @Getter
    @Setter
    public float damage;
    @Getter
    @Setter
    public float health_regen;
    @Getter
    @Setter
    public int armor;
    @Getter
    @Setter
    public TypeDamage typeDamage;
    @Getter
    @Setter
    public float speed;
    @Getter
    @Setter
    public String name;

    Survivors(String name, float health, float damage, float health_regen, TypeDamage typeDamage, int armor, float speed) {
        this.name = name;
        this.health = health;
        this.health_regen = health_regen;
        this.typeDamage = typeDamage;
        this.armor = armor;
        this.speed = speed;


    }

    @Override
    public String getSerializedName() {
        return "";
    }
}
