package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.api.enumeration.Survivors;
import com.rumaruka.riskofmine.client.screen.SurvivorsSelectionScreen;
import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
import com.rumaruka.riskofmine.common.entity.player.PlayerSurvivorsBridge;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements IPlayerSurvivorsBridge {
    @Unique
    private static PlayerSurvivorsBridge riskofmine$survivorsBridge;

    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }


    @NotNull
    @Override
    public ISurvivors riskofmine$getSurvivor() {
        return riskofmine$survivorsBridge;
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void replacePlayerInfo(CallbackInfo ci) {
        riskofmine$survivorsBridge = new PlayerSurvivorsBridge((Player) (Object) this, Survivors.COMMANDO);
        if (SurvivorsSelectionScreen.isCommando) {
            riskofmine$survivorsBridge = new PlayerSurvivorsBridge((Player) (Object) this, Survivors.COMMANDO);

        }
        if (SurvivorsSelectionScreen.isAcrid) {
            riskofmine$survivorsBridge = new PlayerSurvivorsBridge((Player) (Object) this, Survivors.ACRID);

        }
        if (SurvivorsSelectionScreen.isArtificer) {
            riskofmine$survivorsBridge = new PlayerSurvivorsBridge((Player) (Object) this, Survivors.ARTIFICER);

        }

    }


}
