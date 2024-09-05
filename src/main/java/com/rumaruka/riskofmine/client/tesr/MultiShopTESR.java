package com.rumaruka.riskofmine.client.tesr;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.common.tiles.shop.MultiShopTE;
import com.rumaruka.riskofmine.init.ROMModels;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.zeith.hammeranims.api.HammerAnimationsApi;
import org.zeith.hammeranims.api.geometry.event.RefreshStaleModelsEvent;
import org.zeith.hammeranims.api.geometry.model.IGeometricModel;
import org.zeith.hammeranims.api.geometry.model.RenderData;
import org.zeith.hammerlib.client.render.tile.IBESR;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class MultiShopTESR implements IBESR<MultiShopTE> {
    IGeometricModel model;
    final ResourceLocation textureOPEN = rl("textures/tile/multi_shop_open.png");
    final RenderData data;

    public MultiShopTESR(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        data = new RenderData();

        HammerAnimationsApi.EVENT_BUS.addListener(this::refreshModel);

    }

    public void refreshModel(RefreshStaleModelsEvent e) {
        model = ROMModels.MULTI_SHOP_OPEN.createModel();
    }

    @Override
    public void render(MultiShopTE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {


        pPoseStack.pushPose();
        model.resetPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5);
        pPoseStack.scale(1.25f, 2f, 1.25f);
        model.renderModel(data.apply(pPoseStack,pBufferSource.getBuffer(RenderType.entitySolid(textureOPEN)),pPackedLight,pPackedOverlay));
        pPoseStack.popPose();
    }



}
