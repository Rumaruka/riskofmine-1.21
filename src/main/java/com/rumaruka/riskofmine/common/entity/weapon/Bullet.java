package com.rumaruka.riskofmine.common.entity.weapon;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

public class Bullet extends Projectile {
    protected Bullet(EntityType<? extends Projectile> p_37248_, Level p_37249_) {
        super(p_37248_, p_37249_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_326003_) {

    }
}
