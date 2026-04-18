package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import com.rumaruka.riskofmine.init.ROMItems;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class PhaseRound extends CommandoSkills {

    protected boolean isRightFlag;

    protected int cooldownCountMax;

    public PhaseRound(int cooldownCountMax) {
        super(SkillType.SECONDARY, cooldownCountMax);
        addListener(this::onRightClick);
        this.cooldownCountMax = cooldownCountMax;

        addListener(this::onTick);



    }

    private void onRightClick(PlayerInteractEvent.RightClickEmpty event) {
        int charge_value = getChargeValue();

            if (isActive()) {
                if (charge_value >=1){

                if (isSkillActive()) {
                    isRightFlag = true;
                    charge_value--;
                    setChargeValue(charge_value);
                }

            }
        }


    }


    private void onTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) {

            if (isSkillActive()) {
                if (isRightFlag && isCooldown()) {
                    PrimedTnt tnt = new PrimedTnt(EntityType.TNT, level);

                    tnt.setPos(player.getX(), player.getY() + 1, player.getZ());
                    level.addFreshEntity(tnt);

                    isRightFlag = false;
                    setCooldown(false);
                    setCooldownCount(cooldownCountMax);
                }


            }
            if (ROMUtils.checkInventory(player, Items.DIAMOND.getDefaultInstance())){
                setChargeValue(Items.DIAMOND.getDefaultInstance().getCount());
            }else {
                setChargeValue(1);
            }
        }


    }


}







