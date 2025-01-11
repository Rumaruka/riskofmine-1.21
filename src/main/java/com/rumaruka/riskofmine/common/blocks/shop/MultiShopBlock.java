package com.rumaruka.riskofmine.common.blocks.shop;

import com.mojang.serialization.MapCodec;
import com.rumaruka.riskofmine.api.enumeration.Chest;

import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.tiles.shop.GenericShopTE;
import com.rumaruka.riskofmine.common.tiles.shop.MultiShopTE;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MultiShopBlock extends GenericShopBlock {
    public static final MapCodec<MultiShopBlock> CODEC = simpleCodec(p_304364_ -> new MultiShopBlock());

    public MultiShopBlock() {
        super(Properties.of().strength(5.0F, 5.0F), ()->ROMTiles.MULTI_SHOP, Chest.MULTI_SHOP);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult p_60508_) {
        Money money = Money.get(player);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GenericShopTE) {
                if (money.getCurrentMoney() >=10) {
                    money.consumeMoney(10);

                    player.openMenu((GenericShopTE) blockEntity);
                    player.awardStat(Stats.OPEN_CHEST);
                    PiglinAi.angerNearbyPiglins(player, true);
                } else if (money.getCurrentMoney() <10) {
                    level.playSound(null, blockPos, ROMSounds.ROM_CHEST_NOT_MONEY.get(), SoundSource.BLOCKS, 2.0F, 1.0F);
                    player.displayClientMessage(Component.translatable("riskofmine.not_money"), true);

                }


            }
        }
        return InteractionResult.CONSUME;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new MultiShopTE(pPos, pState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
