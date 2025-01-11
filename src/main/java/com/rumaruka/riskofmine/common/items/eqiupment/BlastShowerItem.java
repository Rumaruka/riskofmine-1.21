package com.rumaruka.riskofmine.common.items.eqiupment;

import com.rumaruka.riskofmine.api.enumeration.Category;

import com.rumaruka.riskofmine.init.ROMItems;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import net.neoforged.neoforge.common.EffectCure;

import java.util.List;

import static com.rumaruka.riskofmine.utils.ROMUtils.removeNegativeEffect;

public class BlastShowerItem extends EquipmentBase {
    public BlastShowerItem() {
        super(Category.UTILITY);
        //cooldownMinus= ROMItems.ALIEN_HEAD.cooldownMinus;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide) {
            pPlayer.removeEffectsCuredBy(EffectCure.get("blast_shower"));

            if (!pPlayer.getAbilities().instabuild) {

                for (int i = 0; i < pPlayer.getInventory().getContainerSize(); i++) {
                    ItemStack itemStack = pPlayer.getInventory().getItem(i);
                    if (itemStack.getItem() == ROMItems.ALIEN_HEAD) {
                        pPlayer.removeAllEffects();
                        removeNegativeEffect(pPlayer);
                        pPlayer.getCooldowns().addCooldown(this, /*ROMConfig
                                .GENERAL.cooldownEq.get()*/1000 - cooldownMinus);

                    }

                }
                pPlayer.removeAllEffects();
                removeNegativeEffect(pPlayer);

                pPlayer.getCooldowns().addCooldown(this,/* ROMConfig.GENERAL.cooldownEq.get()*/1000 );

            }
        }


        return new InteractionResultHolder<>(InteractionResult.SUCCESS, pPlayer.getItemInHand(pUsedHand));
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
            tooltip.add(Component.translatable("ror.blast_shower.info"));
        }
    }


}
