package com.rumaruka.riskofmine.common.skills.commando;

import com.rumaruka.riskofmine.api.registry.skill.SkillType;
import com.rumaruka.riskofmine.ntw.ROMNetwork;
import com.rumaruka.riskofmine.ntw.packets.DashPacket;
import com.rumaruka.riskofmine.utils.ROMUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class TacticalDive extends CommandoSkills {

    protected boolean isFlag;
    protected int cooldownCountMax;
    public TacticalDive(int cooldownCountMax) {
        super(SkillType.UTILITY, cooldownCountMax);
        addListener(this::onKeyClick);
        addListener(this::onDamageCheck);

        this.cooldownCountMax=cooldownCountMax;


    }

    private void onKeyClick(InputEvent.Key event) {
        if (isActive()) {
            if (isSkillActive()) {
                if (isCooldown()) {
                    if (KEY_UTILITY_SKILL.isDown()){
                        if (ROMUtils.getPlayer().onGround()){
                            Vec3 playerLook = ROMUtils.getPlayer().getLookAngle();
                            ROMUtils.getPlayer().setDeltaMovement(playerLook.x * 1.5, playerLook.y * 1.5, playerLook.z * 1.5 );
                            ROMNetwork.sendToServer(new DashPacket());
                            isFlag = true;
                        }

                    }
                }
            }



        }

    }





    private void onDamageCheck(EntityInvulnerabilityCheckEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player player){
            if (isFlag){
                isFlag=false;
                event.setInvulnerable(true);
            }else {
                event.setInvulnerable(false);
            }
        }

    }



}







