package com.rumaruka.riskofmine.common.items.uncommon;

import com.rumaruka.riskofmine.api.Category;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.UUID;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class InfusionItem extends BaseCollectablesItem {
    public static final UUID uuid1 = UUID.randomUUID();

    public InfusionItem() {
        super(Types.UNCOMMON, Category.UTILITY);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getMainHandItem();
        if (!pLevel.isClientSide()) {
            pPlayer.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier( rl("healthBoost"), stack.getCount(), AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
            if (!pPlayer.isCreative() || !pPlayer.getAbilities().invulnerable) {
                stack.shrink(stack.getCount());
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
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
            tooltip.add(Component.translatable("ror.infusion.info"));
            tooltip.add(Component.translatable("[Stacks:" + ROMUtils.countAll(ROMUtils.getPlayer(), pStack) + "]"));
        }
    }
}
