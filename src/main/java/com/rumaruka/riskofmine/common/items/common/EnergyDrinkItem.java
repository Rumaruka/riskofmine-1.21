package com.rumaruka.riskofmine.common.items.common;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.utils.ROMMathFormula;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;

import java.util.List;

import static com.rumaruka.riskofmine.RiskOfMine.rl;


public class EnergyDrinkItem extends BaseCollectablesItem {
    private static final ResourceLocation SPEED_MODIFIER_SPRINTING_UUID = rl("speed");

    public EnergyDrinkItem() {
        super(Types.COMMON, Category.UTILITY);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide()) {
            Player playerEntity = (Player) pEntity;
            if (playerEntity.getOffhandItem().getItem() == this) {
                playerEntity.getAttributes().addTransientAttributeModifiers(this.makeAttributeMap(EquipmentSlot.OFFHAND, pStack));

            }

        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity livingEntity = slotContext.entity();
        if (!livingEntity.level().isClientSide()) {
            Player playerEntity = (Player) livingEntity;
            playerEntity.getAttributes().addTransientAttributeModifiers(this.getAttributeModifiers(slotContext, SPEED_MODIFIER_SPRINTING_UUID, stack));
        }
        super.curioTick(slotContext, stack);
    }


    private Multimap<Holder<Attribute>, AttributeModifier> makeAttributeMap(EquipmentSlot slot, ItemStack stack) {
        final Multimap<Holder<Attribute>, AttributeModifier> defaultModifiers;
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        if (slot == EquipmentSlot.OFFHAND) {
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPEED_MODIFIER_SPRINTING_UUID, ROMMathFormula.speedIncreasing(stack.getCount()), AttributeModifier.Operation.ADD_VALUE));
        }

        defaultModifiers = builder.build();
        return defaultModifiers;
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext slotContext, ResourceLocation id, ItemStack stack) {
        EquipmentSlot slot = stack.getEquipmentSlot();
        final Multimap<Holder<Attribute>, AttributeModifier> defaultModifiers;
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();

        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPEED_MODIFIER_SPRINTING_UUID, ROMMathFormula.speedIncreasing(stack.getCount()), AttributeModifier.Operation.ADD_VALUE));


        defaultModifiers = builder.build();
        return defaultModifiers;
    }




    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.translatable((getColor() + getTypeName())));
        tooltip.add(Component.translatable("ror.shiftpress.info"));
        if (Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("ror.energydrink.info"));
            tooltip.add(Component.translatable("[Stacks:" + ROMUtils.counting(ROMUtils.getPlayer(), pStack) + "]"));
        }
    }
}
