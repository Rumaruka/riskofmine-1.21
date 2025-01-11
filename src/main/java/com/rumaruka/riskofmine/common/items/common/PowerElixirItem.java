package com.rumaruka.riskofmine.common.items.common;

import com.rumaruka.riskofmine.api.enumeration.Category;
import com.rumaruka.riskofmine.api.enumeration.ChestTypes;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class PowerElixirItem extends BaseCollectablesItem {
    public PowerElixirItem() {
        super(ChestTypes.COMMON, Category.HEALING);
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
            tooltip.add(Component.translatable("ror.power_elixir.info"));
            tooltip.add(Component.translatable("[Stacks:" + ROMUtils.counting(ROMUtils.getPlayer(), pStack) + "]"));
        }
    }
}
