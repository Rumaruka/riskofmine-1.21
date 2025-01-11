package com.rumaruka.riskofmine.common.blocks.chest;

import com.mojang.serialization.MapCodec;
import com.rumaruka.riskofmine.api.enumeration.Chest;

import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.tiles.chest.GenericChestTE;
import com.rumaruka.riskofmine.common.tiles.chest.LargeChestTE;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.init.ROMTiles;
import net.minecraft.core.BlockPos;
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

public class LargeChestBlock extends GenericChestBlock {
    public static final MapCodec<LargeChestBlock> CODEC = simpleCodec(p_304364_ -> new LargeChestBlock());

    public LargeChestBlock() {
        super(Properties.of().strength(5, 5),()-> ROMTiles.LARGE_CHEST, Chest.LARGE);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult p_60508_) {
        Money money = Money.get(player);
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = level.getBlockEntity(blockPos);
            if (blockEntity instanceof GenericChestTE) {
                if (money.getCurrentMoney() >= 30) {
                    money.consumeMoney( 30);
                    
                    player.openMenu((GenericChestTE) blockEntity);
                    player.awardStat(Stats.OPEN_CHEST);
                    PiglinAi.angerNearbyPiglins(player, true);
                } else if (money.getCurrentMoney() < 30) {
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
        return new LargeChestTE(pPos, pState);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
