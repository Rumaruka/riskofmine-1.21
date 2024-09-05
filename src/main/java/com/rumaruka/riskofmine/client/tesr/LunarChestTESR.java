package com.rumaruka.riskofmine.client.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.common.tiles.chest.LunarChestTE;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.zeith.hammerlib.client.render.tile.IBESR;


public class LunarChestTESR implements IBESR<LunarChestTE> {
    public LunarChestTESR( BlockEntityRendererProvider.Context rendererDispatcherIn) {
        super();
    }



    @Override
    public void render(LunarChestTE entity, float partial, PoseStack matrix, MultiBufferSource buf, int lighting, int overlay) {

    }
}
