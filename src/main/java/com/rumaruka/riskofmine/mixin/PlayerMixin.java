//package com.rumaruka.riskofmine.mixin;
//
//import com.rumaruka.riskofmine.api.Survivors;
//import com.rumaruka.riskofmine.common.entity.player.IPlayerSurvivorsBridge;
//import com.rumaruka.riskofmine.common.entity.player.ISurvivors;
//import com.rumaruka.riskofmine.common.entity.player.PlayerSurvivorsBridge;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.Level;
//import org.jetbrains.annotations.NotNull;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin(Player.class)
//public abstract class PlayerMixin extends Entity implements IPlayerSurvivorsBridge {
//    @Unique
//    private static PlayerSurvivorsBridge riskofmine$survivorsBridge;
//
//    private PlayerMixin(EntityType<?> p_19870_, Level p_19871_) {
//        super(p_19870_, p_19871_);
//    }
//
//    @NotNull
//    @Override
//    public ISurvivors riskofmine$getSurvivor() {
//        return riskofmine$survivorsBridge;
//    }
//
//    @Inject(method = "<init>", at = @At("TAIL"))
//    private void replacePlayerInfo(CallbackInfo ci) {
//        if (riskofmine$survivorsBridge.survivors()==null) {
//
//        }
//        riskofmine$survivorsBridge = new PlayerSurvivorsBridge((Player) (Object) this, riskofmine$survivorsBridge.survivors());
//
//
//    }
//
//
//
//
////    public static AttributeSupplier.Builder createAttributes() {
////        return LivingEntity.createLivingAttributes()
////                .add(Attributes.ATTACK_DAMAGE, 1.0)
////                .add(Attributes.MOVEMENT_SPEED, 0.1F)
////                .add(Attributes.ATTACK_SPEED)
////                .add(Attributes.LUCK)
////                .add(Attributes.BLOCK_INTERACTION_RANGE, 4.5)
////                .add(Attributes.ENTITY_INTERACTION_RANGE, 3.0)
////                .add(Attributes.BLOCK_BREAK_SPEED)
////                .add(Attributes.SUBMERGED_MINING_SPEED)
////                .add(Attributes.SNEAKING_SPEED)
////                .add(Attributes.MINING_EFFICIENCY)
////                .add(Attributes.SWEEPING_DAMAGE_RATIO)
////                .add(net.neoforged.neoforge.common.NeoForgeMod.CREATIVE_FLIGHT);
////    }
//
//}
