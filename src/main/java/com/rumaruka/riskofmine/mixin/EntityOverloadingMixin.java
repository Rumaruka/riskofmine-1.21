package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.PacketDistributor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(Entity.class)
public abstract class EntityOverloadingMixin implements IOverloading {
    @Shadow
    public abstract int getId();


    @Shadow
    public abstract Level level();

    private boolean isOverloading;

    @Override
    public boolean isOverloading() {

        return isOverloading;
    }

    @Override
    public void setOverloading(boolean isOver) {
        if (isOver != isOverloading && !this.level().isClientSide()) {
                ROMNetwork.sendToClientsTrackingEntityAndSelf(new OverloadingPacket(this.getId(),isOver),(Entity) (Object) this);
        }
        isOverloading = isOver;
    }

}
