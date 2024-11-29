package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.api.entity.IBlazing;
import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.BlazingPacket;
import com.rumaruka.riskofmine.ntw.packets.OverloadingPacket;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(Entity.class)
public abstract class EntityBlazingMixin implements IBlazing {
    @Shadow
    public abstract int getId();


    @Shadow
    public abstract Level level();

    private boolean isBlazing;

    @Override
    public boolean isBlazing() {

        return isBlazing;
    }

    @Override
    public void setBlazing(boolean isOver) {
        if (isOver != isBlazing && !this.level().isClientSide()) {
                ROMNetwork.sendToClientsTrackingEntityAndSelf(new BlazingPacket(this.getId(),isOver),(Entity) (Object) this);
        }
        isBlazing = isOver;
    }

}
