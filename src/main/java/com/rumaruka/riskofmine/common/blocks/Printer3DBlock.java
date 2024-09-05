package com.rumaruka.riskofmine.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Collections;

public class Printer3DBlock extends Block {
    public Item priorityItem;

    public Printer3DBlock(Properties properties_, Item priority) {
        super(properties_);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult p_60508_){
        if (!level.isClientSide()) {
            if (player.getInventory().hasAnyOf(Collections.singleton(priorityItem))) {
                // Если у игрока есть предмет с приоритетом, используйте его
                ItemStack priorityStack = player.getInventory().getItem(player.getInventory().getSlotWithRemainingSpace(new ItemStack(priorityItem)));
                player.getInventory().setItem(player.getInventory().selected, priorityStack);
            } else {
                // Иначе используйте любой другой предмет
                for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                    ItemStack stack = player.getInventory().getItem(i);
                    if (!stack.isEmpty() && stack.getItem() != priorityItem) {
                        player.getInventory().setItem(player.getInventory().selected, stack);
                        break;
                    }
                }
            }
        }

        return super.useWithoutItem(blockState, level, blockPos, player, p_60508_);
    }
}
