//package com.rumaruka.riskofmine.mixin;
//
//import com.rumaruka.riskofmine.client.screen.SurvivorsSelectionScreen;
//import net.minecraft.client.gui.components.Button;
//import net.minecraft.client.gui.screens.Screen;
//import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
//
//import net.minecraft.network.chat.Component;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.api.distmarker.OnlyIn;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(SelectWorldScreen.class)
//@OnlyIn(Dist.CLIENT)
//public  class SelectWorldScreenMixin extends Screen {
//
//    protected SelectWorldScreenMixin(Screen p_96550_) {
//        super(Component.translatable("selectWorld.title"));
//
//    }
//    @Inject(method = "init", at = @At("TAIL"))
//    private void init(CallbackInfo ci){
//       this.addRenderableWidget(
//                Button.builder(Component.translatable("riskofmine.choose.survivors"), b -> {
//                            this.getMinecraft().setScreen(new SurvivorsSelectionScreen());
//                        })
//
//                        .bounds(this.width / 2 +100, 10, 150, 25)
//                        .build()
//        );
//
//
//
//    }
//
//}
