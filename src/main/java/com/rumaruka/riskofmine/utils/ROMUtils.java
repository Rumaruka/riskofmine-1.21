package com.rumaruka.riskofmine.utils;

import com.mojang.blaze3d.vertex.PoseStack;
import com.rumaruka.riskofmine.api.Types;
import com.rumaruka.riskofmine.api.entity.IOverloading;
import com.rumaruka.riskofmine.common.items.BaseCollectablesItem;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EntityBasedExplosionDamageCalculator;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.List;


public class ROMUtils {


    private static MobEffectCategory category;


    private static final Minecraft mc = Minecraft.getInstance();
    private static int durOld;
    public static int valueShields = 5;

    public static Minecraft getMc() {
        return mc;
    }

    public static Level getLvL() {
        return getMc().level;
    }

    public static LevelRenderer getRender() {
        return mc.levelRenderer;
    }

    public static Player getPlayer() {

        return mc.player;
    }

    public static int getValueShields() {
        return valueShields;
    }

    public static int getShieldShapedGlass() {
        return 16;
    }


    public static int getDurOld() {
        return durOld;
    }

    /**
     * set the movespeed used for the new AI system
     */
    public static int setDurOld(int durNew) {
        return durOld = durNew;
    }


    public static void sendMessage(String msg) {
        Player player = Minecraft.getInstance().player;

        if (player != null) {
            player.sendSystemMessage(Component.translatable(msg));

        }

    }

    public static boolean isGiveDamage(LivingEntity entity) {
        float health = Math.min(entity.getHealth(), entity.getMaxHealth());
        return health < entity.getMaxHealth();
    }


    public static boolean hasOverloadingOnClient(Entity entity) {

        return ((IOverloading) entity).isOverloading();

    }
public static int countAll(Player player, ItemStack item){
    int itemCount = 0;
        if (checkCurios(player,item)||checkCurios(player,item)){
            itemCount+=item.getCount();
        }

        return itemCount;
}
    public static int counting(Player player, ItemStack itemToCount) {
        int itemCount = 0;
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty() && ItemStack.isSameItem(stack, itemToCount)) {
                itemCount += stack.getCount();
            }
        }
        return itemCount;
    }

    public static int countingCurio(Player player, Item item) {
        int itemCount = 0;

        ItemStack itemStack = curiosItemStack(player, item);
        if (itemStack.getItem() == item) {

            itemCount += itemStack.getCount();


        }
        return itemCount;
    }

    public static void removeNegativeEffect(LivingEntity entity) {
        ArrayList<Holder<MobEffect>> potions = new ArrayList<>(entity.getActiveEffectsMap().keySet());
        potions.stream().filter(potion -> isBadEffect()).forEach(entity::removeEffect);
    }

    public static boolean isBadEffect() {
        return category == MobEffectCategory.HARMFUL;
    }


    public static boolean checkInventory(Player player, ItemStack item) {

        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (ItemStack.isSameItem(itemStack, item)) {
                return true;
            }

        }
        return false;
    }
    public static boolean checkBaseItems(Player player) {

        Inventory inventory = player.getInventory();

        for (int i = 0; i < inventory.getContainerSize(); i++) {
            ItemStack itemStack = inventory.getItem(i);
            if (itemStack.getItem() instanceof BaseCollectablesItem){
                return true;
            }

        }
        return false;
    }
    public static boolean isCommon(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            if (base.getType() == Types.COMMON) {
                itemStack.shrink(1);
                return true;
            }

        }
        return false;
    }

    public static boolean isUnCommon(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            if (base.getType() == Types.UNCOMMON) {
                itemStack.shrink(1);
                return true;
            }

        }
        return false;
    }

    public static boolean isBossItem(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            if (base.getType() == Types.BOSS) {
                itemStack.shrink(1);
                return true;
            }

        }
        return false;
    }

    public static boolean isVoid(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            return base.getType() == Types.VOID;

        }
        return false;
    }

    public static boolean isScrap(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            return base.getType() == Types.SCRAP;

        }
        return false;
    }

    public static boolean isLunar(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            return base.getType() == Types.LUNAR;

        }
        return false;
    }

    public static boolean isLegendary(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            if (base.getType() == Types.LEGENDARY) {
                itemStack.shrink(1);
                return true;
            }

        }
        return false;
    }

    public static boolean isEq(Player player) {

        ItemStack itemStack = player.getMainHandItem();
        if (itemStack.getItem() instanceof BaseCollectablesItem base) {
            if (base.getType() == Types.EQUIPMENT) {
                itemStack.shrink(1);
                return true;
            }

        }
        return false;
    }

    public static ItemStack curiosItemStack(Player player, Item item) {
        if (CuriosApi.getCuriosHelper().findFirstCurio(player, item).isPresent()) {
            return CuriosApi.getCuriosHelper().findFirstCurio(player, item).get().stack();
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static boolean checkCurios(Player player, ItemStack item) {
        if (CuriosApi.getCuriosHelper().findFirstCurio(player, item.getItem()).isPresent()) {
            ItemStack curioStack = curiosItemStack(player, item.getItem());
            return ItemStack.isSameItem(curioStack, item);

        }
        return false;
    }


    public static void replaceItem(ItemStack used, ItemStack scrap) {
        used.shrink(1);
        scrap.shrink(-1);
    }

    /**
     * Draws string.
     *
     * @param text  text to be displayed.
     * @param x     start x-coordinate (left)
     * @param y     start y-coordinate (top)
     * @param color HTML color. Example: 0xFF0000 -> red.
     */
    public static void drawString(GuiGraphics graphics, Font fontRendererIn, String text, float x, float y, int color) {
        graphics.drawString(fontRendererIn, text, x, y, color, false);
    }

    /**
     * Draws string with shadow.
     *
     * @param text  text to be displayed.
     * @param x     start x-coordinate (left)
     * @param y     start y-coordinate (top)
     * @param color HTML color. Example: 0xFF0000 -> red.
     */
    public static void drawStringWithShadow(GuiGraphics graphics, Font fontRendererIn, String text, float x, float y, int color) {
        graphics.drawString(fontRendererIn, text, x, y, color, true);
    }

    public static  void renderText( PoseStack pose , String text,float x, float y, MultiBufferSource pBufferSource){
        Font font = ROMUtils.getMc().font;

        pose.pushPose();
       // drawString(graphics,font,text,x,y,0xFFFFFF);
        font.drawInBatch(text,x,y,0xFFFFFF,true,pose.last().pose(), pBufferSource,Font.DisplayMode.NORMAL,
                0,
                15728880);
        pose.popPose();
    }

}
