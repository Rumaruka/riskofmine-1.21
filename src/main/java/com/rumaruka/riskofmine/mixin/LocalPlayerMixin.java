package com.rumaruka.riskofmine.mixin;

import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.DoubleJumpPacket;
import com.rumaruka.riskofmine.utils.ROMDoubleEffect;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

    private int riskofmine$jumpCount = 0;
    private boolean riskofmine$jumpedLastTick = false;

    @Inject(method = "tick", at = @At("HEAD"))
    private void tickMovement(CallbackInfo info) {
        LocalPlayer player = (LocalPlayer) (Object) this;
        if (player.onGround() || player.onClimbable()) {
            riskofmine$jumpCount = ROMUtils.countAll(player, ROMItems.HOPOO_FEATHER.getDefaultInstance());
        } else if (!riskofmine$jumpedLastTick && riskofmine$jumpCount > 0 && player.getDeltaMovement().y < 0) {
            if (player.input.jumping && !player.getAbilities().flying) {
                if (riskofmine$canJump(player)) {
                    --riskofmine$jumpCount;
                    player.jumpFromGround();
                    ROMDoubleEffect.play(player);
                    ROMNetwork.sendToServer(new DoubleJumpPacket());
                }
            }
        }
        riskofmine$jumpedLastTick = player.input.jumping;
    }


    @Unique
    private boolean riskofmine$wearingUsableElytra(LocalPlayer player) {
        ItemStack chestItemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        return chestItemStack.getItem() == Items.ELYTRA && ElytraItem.isFlyEnabled(chestItemStack);
    }

    @Unique
    private boolean riskofmine$canJump(LocalPlayer player) {
        return !riskofmine$wearingUsableElytra(player) && !player.isFallFlying() && !player.isVehicle() && !player.isInWater() && !player.hasEffect(MobEffects.LEVITATION);
    }


}




