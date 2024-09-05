package com.rumaruka.riskofmine.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.common.entity.misc.StickyBombEntity;
import com.rumaruka.riskofmine.init.ROMModels;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.zeith.hammeranims.api.HammerAnimationsApi;
import org.zeith.hammeranims.api.geometry.event.RefreshStaleModelsEvent;
import org.zeith.hammeranims.api.geometry.model.IGeometricModel;
import org.zeith.hammeranims.api.geometry.model.RenderData;
import org.zeith.hammeranims.core.client.render.entity.BedrockEntityRenderer;


import javax.annotation.ParametersAreNonnullByDefault;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class StickyBombRenderer extends EntityRenderer<StickyBombEntity> {
    IGeometricModel stickyBombModel;

    final ResourceLocation texture = rl("textures/entity/sticky_bomb_model.png");
    final RenderData data;

    public StickyBombRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        data = new RenderData();
        HammerAnimationsApi.EVENT_BUS.addListener(this::refreshModel);
    }
    public void refreshModel(RefreshStaleModelsEvent e)
    {
        stickyBombModel = ROMModels.STICKY_BOMB.createModel();
    }
    @Override
    public void render(StickyBombEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {

        stickyBombModel.resetPose();
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5F);
        pPoseStack.scale(1.15f, 1f, 1.25f);
        stickyBombModel.renderModel(data.apply(pPoseStack,pBuffer.getBuffer(RenderType.entitySolid(texture)),pPackedLight, OverlayTexture.NO_OVERLAY));

        pPoseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(StickyBombEntity p_114482_) {
        return texture;
    }


}
