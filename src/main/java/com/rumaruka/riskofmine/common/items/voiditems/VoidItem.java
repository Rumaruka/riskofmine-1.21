package com.rumaruka.riskofmine.common.items.voiditems;

import com.rumaruka.riskofmine.api.enumeration.Category;
import com.rumaruka.riskofmine.api.item.IVoidItem;
import com.rumaruka.riskofmine.api.enumeration.ChestTypes;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import net.minecraft.world.item.ItemStack;

public class VoidItem extends BaseCollectablesItem implements IVoidItem {
    public VoidItem(Category category) {
        super(ChestTypes.VOID, category);

    }


    @Override
    public void replaceItem(ItemStack uncorruted, ItemStack corruted) {
        uncorruted.shrink(1);
        corruted.shrink(-1);

    }
}
