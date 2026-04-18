package com.rumaruka.riskofmine.common.items.common;

import com.rumaruka.riskofmine.api.enumeration.Category;
import com.rumaruka.riskofmine.api.enumeration.ItemTypes;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;

public class EmptyElixirItem extends BaseCollectablesItem {
    public EmptyElixirItem() {
        super(ItemTypes.COMMON, Category.SCRAP);
    }
}
