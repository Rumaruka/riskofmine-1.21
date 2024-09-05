package com.rumaruka.riskofmine.client.tesr;

import com.mojang.blaze3d.vertex.PoseStack;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.rumaruka.riskofmine.common.config.ROMConfig;
import com.rumaruka.riskofmine.common.tiles.chest.SmallChestTE;

import com.rumaruka.riskofmine.init.ROMModels;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.joml.Quaternionf;
import org.zeith.hammeranims.HammerAnimations;
import org.zeith.hammeranims.api.HammerAnimationsApi;
import org.zeith.hammeranims.api.geometry.event.RefreshStaleModelsEvent;
import org.zeith.hammeranims.api.geometry.model.IGeometricModel;
import org.zeith.hammeranims.api.geometry.model.RenderData;
import org.zeith.hammerlib.client.render.tile.IBESR;


import javax.annotation.Nullable;
import java.util.function.Function;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class SmallChestTESR implements IBESR<SmallChestTE> {


    IGeometricModel model;

    final ResourceLocation texture = rl("textures/tile/small_chest.png");
    final RenderData data;

    public SmallChestTESR() {
        data = new RenderData();
        HammerAnimationsApi.EVENT_BUS.addListener(this::refreshModel);

    }
    public void refreshModel(RefreshStaleModelsEvent e)
    {
        model = ROMModels.SMALL_CHEST.createModel();
    }

    @Override
    public void render(SmallChestTE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {


        pPoseStack.pushPose();
        model.resetPose();
        pPoseStack.translate(0.5F, 0.01f, 0.5F);
        pPoseStack.scale(1.15f, 1f, 1.25f);
        model.renderModel(data.apply(pPoseStack,pBufferSource.getBuffer(RenderType.entitySolid(texture)),pPackedLight,pPackedOverlay));

        pPoseStack.popPose();
    }
















}
