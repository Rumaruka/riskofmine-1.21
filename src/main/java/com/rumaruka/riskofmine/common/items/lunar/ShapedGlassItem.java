package com.rumaruka.riskofmine.common.items.lunar;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.ChestTypes;

import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;
import java.util.UUID;

public class ShapedGlassItem extends BaseCollectablesItem {

    public static final UUID healthModifierID = UUID.fromString("208b4d4c-50ef-4b45-a097-4bed633cdbff");

    public ShapedGlassItem() {
        super(ChestTypes.LUNAR, Category.DAMAGE);
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
            tooltip.add(Component.translatable("ror.shaped_glass.info"));
            tooltip.add(Component.translatable("[Stacks:" + ROMUtils.countAll(ROMUtils.getPlayer(), pStack) + "]"));
        }
    }

//    @Override
//    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
//        if (!pLevel.isClientSide()) {
//            Player playerEntity = (Player) pEntity;
//            if (playerEntity.getOffhandItem().getItem() == this) {
//
//                playerEntity.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(EquipmentSlot.OFFHAND, pStack));
//
//            }
//
//        }
//
//        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
//    }
//
//    @Override
//    public void curioTick(SlotContext slotContext, ItemStack stack) {
//        LivingEntity livingEntity = slotContext.entity();
//
//        if (!livingEntity.level().isClientSide()) {
//            Player playerEntity = (Player) livingEntity;
//
//            playerEntity.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(slotContext, playerEntity.getUUID(), stack));
//            curioShields(playerEntity);
//        }
//        super.curioTick(slotContext, stack);
//    }
//
//    private void curioShields(Player player) {
//        Shields shields = Shields.of(player);
//        if (shields != null) {
//            if (!shields.isHurt() && player.hurtTime == 0) {
//
//                shields.setShields(16);
//            }
//            shields.detectAndSendChanges();
//        }
//    }
//
//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid, ItemStack stack) {
//        final Multimap<Attribute, AttributeModifier> defaultModifiers;
//
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//
//        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modificator", 64, AttributeModifier.Operation.ADDITION));
//        builder.put(Attributes.MAX_HEALTH, new AttributeModifier(healthModifierID, "Health Minus", -16, AttributeModifier.Operation.ADDITION));
//
//        defaultModifiers = builder.build();
//        return defaultModifiers;
//    }
//
//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        final Multimap<Attribute, AttributeModifier> defaultModifiers;
//
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        if (slot == EquipmentSlot.OFFHAND) {
//            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modificator", 64, AttributeModifier.Operation.ADDITION));
//            builder.put(Attributes.MAX_HEALTH, new AttributeModifier(healthModifierID, "Health Minus", -16, AttributeModifier.Operation.ADDITION));
//        }
//        defaultModifiers = builder.build();
//        return defaultModifiers;
//    }
//
//    @Override
//    public boolean showAttributesTooltip(String identifier, ItemStack stack) {
//        return false;
//    }
}
