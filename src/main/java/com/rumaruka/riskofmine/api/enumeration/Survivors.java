package com.rumaruka.riskofmine.api.enumeration;


import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum Survivors implements StringRepresentable {
    COMMANDO("commando", 110f, 12f, 1.5f, TypeDamage.Ranged, 0, 0.77f),
    ACRID("acrid", 160f, 15f, 2.5f, TypeDamage.Melee_Ranged, 20, 7),
    BANDIT("bandit", 160f, 15f, 2.5f, TypeDamage.Melee_Ranged, 20, 7),
    ARTIFICER("artificer", 110f, 12f, 1f, TypeDamage.Ranged, 0, 7);

    @Getter
    @Setter
    private  float health;
    @Setter
    @Getter
    private  float damage;
    @Getter
    @Setter
    private  float health_regen;
    @Getter
    @Setter
    private  int armor;
    @Getter
    @Setter
    private  TypeDamage typeDamage;
    @Getter
    @Setter
    private  float speed;
    @Getter
    @Setter
    private  String name;

    Survivors(String name, float health, float damage, float health_regen, TypeDamage typeDamage, int armor, float speed) {
        this.name = name;
        this.health = health;
        this.health_regen = health_regen;
        this.typeDamage = typeDamage;
        this.armor = armor;
        this.speed = speed;


    }


    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

//    public float getHealth() {
//        return health;
//    }
//
//    public float getDamage() {
//        return damage;
//    }
//
//    public float getHealth_regen() {
//        return health_regen;
//    }
//
//    public float getSpeed() {
//        return speed;
//    }
//
//    public int getArmor() {
//        return armor;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public TypeDamage getTypeDamage() {
//        return typeDamage;
//    }
//
//    public void setHealth(float health) {
//        this.health = health;
//    }
//
//    public void setArmor(int armor) {
//        this.armor = armor;
//    }
//
//    public void setDamage(float damage) {
//        this.damage = damage;
//    }
//
//    public void setHealth_regen(float health_regen) {
//        this.health_regen = health_regen;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setSpeed(float speed) {
//        this.speed = speed;
//    }
//
//    public void setTypeDamage(TypeDamage typeDamage) {
//        this.typeDamage = typeDamage;
//    }
}
