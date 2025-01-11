package com.rumaruka.riskofmine.common.items.voiditems;

import com.rumaruka.riskofmine.api.enumeration.Category;
import com.rumaruka.riskofmine.common.items.common.BustlingFungusItem;
import com.rumaruka.riskofmine.events.MovingHandler;
import com.rumaruka.riskofmine.init.ROMSounds;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

public class WeepingFungusItem extends VoidItem {
    public WeepingFungusItem() {
        super(Category.HEALING);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pEntity instanceof Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.getItem() instanceof BustlingFungusItem) {
                    pLevel.playSound(null, pEntity.getX(), pEntity.getY(), pEntity.getZ(), ROMSounds.UI_VOID_REPLACE_ITEM.get(), SoundSource.MASTER, 2.0F, 1.0F);
                    replaceItem(itemStack, pStack);
                }
            }


            if (!pLevel.isClientSide()) {
                if (MovingHandler.isMoving(player)) {
                    player.heal((pStack.getCount() + 0.045f) / 20f);
                }
            }
        }

    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (slotContext.entity() instanceof Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (itemStack.getItem() instanceof BustlingFungusItem) {
                    slotContext.entity().level().playSound(null, slotContext.entity().getX(), slotContext.entity().getY(), slotContext.entity().getZ(), ROMSounds.UI_VOID_REPLACE_ITEM.get(), SoundSource.MASTER, 2.0F, 1.0F);

                    replaceItem(itemStack, stack);
                }
            }


            if (!slotContext.entity().level().isClientSide()) {
                if (MovingHandler.isMoving(player)) {
                    player.heal((stack.getCount() + 0.045f) / 20f);
                }
            }
        }
    }
    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.translatable("ror.alt.info"));
        if (Screen.hasAltDown()) {


            tooltip.add(Component.translatable("riskofmine.rarity").append(": ").append(Component.translatable((getColor() + getTypeName()))));
            tooltip.add(Component.translatable("riskofmine.category").append(": ").append(Component.translatable((getColors() + getCategoryName()))));

        }
        tooltip.add(Component.translatable("ror.shiftpress.info"));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("ror.weepimg.info"));
            tooltip.add(Component.translatable("[Stacks:" + ROMUtils.countAll(ROMUtils.getPlayer(), pStack) + "]"));
        }
    }
}
