package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.client.screen.SurvivorsSelectionScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;

import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SelectWorldScreen.class)
@OnlyIn(Dist.CLIENT)
public  class SelectWorldScreenMixin extends Screen {
    @Unique
    private Button riskofmine$selectSurvivors;
    protected final Screen lastScreen;
    protected SelectWorldScreenMixin(Screen p_96550_) {
        super(Component.translatable("selectWorld.title"));
        this.lastScreen = p_96550_;
    }
    @Inject(method = "init", at = @At("TAIL"))
    private void init(CallbackInfo ci){
        this.riskofmine$selectSurvivors = this.addRenderableWidget(
                Button.builder(Component.literal("Choose your Survivors"), b -> {
                            this.getMinecraft().setScreen(new SurvivorsSelectionScreen());
                        })

                        .bounds(this.width / 2 + 5, 10, 100, 10)
                        .build()
        );



    }

}
