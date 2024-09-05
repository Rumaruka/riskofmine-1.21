//package com.rumaruka.riskofmine.mixin.inventory;
//
//import net.minecraft.core.component.DataComponentType;
//import net.minecraft.core.component.DataComponents;
//import net.minecraft.world.item.Item;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//
//@Mixin({Item.Properties.class})
//public abstract class ItemSettingsMixin {
//
//
//
//
//    @Shadow
//    public abstract <T> Item.Properties component(DataComponentType<T> p_330871_, T p_330323_);
//
//    @Inject(at = @At("TAIL"),method = "stacksTo", cancellable = true)
//    public void stacksTo(int p_41488_, CallbackInfoReturnable<Item.Properties> cir) {
//        cir.setReturnValue(component(DataComponents.MAX_STACK_SIZE, 500));
//    }
//
//}
