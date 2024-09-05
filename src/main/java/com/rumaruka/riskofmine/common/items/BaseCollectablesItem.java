package com.rumaruka.riskofmine.common.items;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class BaseCollectablesItem extends Item implements ICurioItem {
    @Getter
    private final Types type;
    private final Category categoryEnum;




    public BaseCollectablesItem(Types type, Category category) {
        super(new Properties());
        this.type = type;
        this.categoryEnum = category;




    }






    public String getTypeName() {
        return type.getName();
    }

    public ChatFormatting getColor() {
        return type.getChatColor();
    }

    public ChatFormatting getColors() {
        return categoryEnum.getChatColor();
    }

    public String getCategoryName() {
        return categoryEnum.getName();
    }


}
