package com.rumaruka.riskofmine.common.items;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.ChestTypes;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public abstract class BaseCollectablesItem extends Item implements ICurioItem {
    @Getter
    private final ChestTypes type;
    private final Category categoryEnum;




    public BaseCollectablesItem(ChestTypes type, Category category) {
        super(new Properties());
        this.type = type;
        this.categoryEnum = category;




    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public boolean canEquip(ItemStack stack, EquipmentSlot armorType, LivingEntity entity) {
        return true;
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
