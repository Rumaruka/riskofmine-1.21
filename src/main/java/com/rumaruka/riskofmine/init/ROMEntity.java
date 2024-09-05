package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;

import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.common.entity.weapon.GoldenBulletEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.zeith.hammerlib.annotations.RegistryName;
import org.zeith.hammerlib.annotations.SimplyRegister;

@SimplyRegister
public interface ROMEntity {
    @RegistryName("health_orb")
    EntityType<HealthOrbEntity> HEALTH_ORB =EntityType.Builder.<HealthOrbEntity>of(HealthOrbEntity::new, MobCategory.MISC)
                            .setTrackingRange(80)
                        .setShouldReceiveVelocityUpdates(true)
                        .sized(3.5F, 3.5F).updateInterval(20).build("health_orb");
   @RegistryName("sticky_bomb")
    EntityType<StickyBombEntity> STICKY_BOMB = EntityType.Builder.<StickyBombEntity>of(StickyBombEntity::new, MobCategory.MISC)
           .setTrackingRange(80)
           .setShouldReceiveVelocityUpdates(true)
           .sized(3.5F, 3.5F).updateInterval(20).build("sticky_bomb");



//        REGISTER.register("health_orb",
//
//
//        REGISTER.register("sticky_bomb",
//                EntityType.Builder.<StickyBombEntity>of(StickyBombEntity::new, MobCategory.MISC)
//                        .setTrackingRange(80)
//                        .setShouldReceiveVelocityUpdates(true)
//                        .sized(3.5F, 3.5F).updateInterval(20));
//        REGISTER.register("golden_bullet",
//                EntityType.Builder.<GoldenBulletEntity>of(GoldenBulletEntity::new, MobCategory.MISC)
//                        .setTrackingRange(80)
//                        .setShouldReceiveVelocityUpdates(true)
//                        .sized(3.5F, 3.5F).updateInterval(20));
//        REGISTER.register("malachite_urchins",
//                EntityType.Builder.<MalachiteUrchinsEntity>of(MalachiteUrchinsEntity::new, MobCategory.MISC)
//                        .setTrackingRange(80)
//                        .setShouldReceiveVelocityUpdates(true)
//                        .sized(3.5F, 3.5F).updateInterval(20));
private static <T extends Entity> EntityType<T> register(String p_20635_, EntityType.Builder<T> p_20636_) {
    return Registry.register(BuiltInRegistries.ENTITY_TYPE, p_20635_, p_20636_.build(p_20635_));
}
}
