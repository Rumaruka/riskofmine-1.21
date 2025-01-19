package com.rumaruka.riskofmine.common.events.cap_event;


import com.rumaruka.riskofmine.api.registry.skill.SkillBase;
import com.rumaruka.riskofmine.common.cap.Money;
import com.rumaruka.riskofmine.common.events.ItemsEvents;
import com.rumaruka.riskofmine.init.ROMAttachment;
import com.rumaruka.riskofmine.utils.ROMRandomChanceUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;


@EventBusSubscriber
public class MoneyEvents {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Player player = event.getEntity() instanceof Player ? (Player) event.getEntity() : null;

        if (player != null) {
            Money barrier = Money.get(player);
            barrier.setMoney(barrier.getCurrentMoney());
        }


    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getOriginal().hasData(ROMAttachment.MONEY.get())) {
            event.getEntity().getData(ROMAttachment.MONEY.get()).setMoney(event.getOriginal().getData(ROMAttachment.MONEY.get()).getCurrentMoney());

        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        Player entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server != null) {
            Money data = Money.get(entity);
            data.setMoney(data.getCurrentMoney());
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player entity = event.getEntity();
        Money money = Money.get(entity);
        if (ItemsEvents.isAlive()) {
            if (ROMRandomChanceUtils.fiftyFifty()) {
                money.addMoney(1);
            }


            ItemsEvents.setAlive(false);
        }

        if (SkillBase.isSkillActive()){
            if (SkillBase.isKillInSkills()){
                if (ROMRandomChanceUtils.fiftyFifty()) {
                    money.addMoney(1);
                }
                SkillBase.setKillInSkills(false);
            }
        }
    }

    @SubscribeEvent
    public static void onEntityDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            LivingEntity livingEntity = event.getEntity();
            Level level = livingEntity.level();

            if (!level.isClientSide()) {
                Money money = Money.get(player);
                money.addMoney(10);


            }
        }
        if (event.getSource().getEntity() instanceof AmbientCreature && event.getEntity() instanceof ServerPlayer player) {
            Level world = player.level();
            Money money = Money.get(player);
            if (!world.isClientSide) {
                if (event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    return;
                } else {
                    money.setMoney(0);

                }


            }
        }
    }

}
