package com.rumaruka.riskofmine.common.items.eqiupment;

import com.rumaruka.riskofmine.api.enumeration.Category;
import com.rumaruka.riskofmine.api.enumeration.ItemTypes;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;

public class EquipmentBase extends BaseCollectablesItem {
    private final Category categoryEnum;

    public int cooldownMinus;

    public EquipmentBase(Category categoryEnum) {
        super(ItemTypes.EQUIPMENT, categoryEnum);
        this.categoryEnum = categoryEnum;

    }
}
