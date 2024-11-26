package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.entity.misc.HealthOrbEntity;

import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
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
           .sized(1.5F, 1.5F).updateInterval(20).build("sticky_bomb");


}
