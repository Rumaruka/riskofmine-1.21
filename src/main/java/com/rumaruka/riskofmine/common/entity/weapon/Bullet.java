package com.rumaruka.riskofmine.common.entity.weapon;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class Bullet extends Projectile {
    private int life;
    @Nullable
    private List<Entity> piercedAndKilledEntities;
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(Bullet.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId(Bullet.class, EntityDataSerializers.BYTE);

    public Bullet(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    public Bullet(EntityType<? extends Projectile> entityType, double x, double y, double z, Level level) {
        super(entityType, level);
        this.setPos(x, y, z);
    }

    public Bullet(EntityType<? extends Projectile> entityType, LivingEntity owner, Level level) {
        this(entityType, owner.getX(), owner.getY(), owner.getZ(), level);
        this.setOwner(owner);
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        super.shoot(x, y, z, velocity, inaccuracy);
        life = 0;

    }

    @Override
    public void lerpTo(double x, double y, double z, float yRot, float xRot, int steps) {
        super.lerpTo(x, y, z, yRot, xRot, steps);
        setRot(yRot, xRot);
    }

    @Override
    public void lerpMotion(double x, double y, double z) {
        super.lerpMotion(x, y, z);
        life = 0;
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 vec3 = getDeltaMovement();
        if (xRotO == 0F && yRotO == 0F) {
            double d0 = vec3.horizontalDistance();
            this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * 180.0F / (float) Math.PI));
            this.setXRot((float) (Mth.atan2(vec3.y, d0) * 180.0F / (float) Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(ID_FLAGS, (byte)0);
        builder.define(PIERCE_LEVEL,  (byte)0);
    }
}
