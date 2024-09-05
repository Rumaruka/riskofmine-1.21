package com.rumaruka.riskofmine.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;


import java.util.HashMap;
import java.util.UUID;

@EventBusSubscriber
public class MovingHandler {
    private static final HashMap<UUID, MoveInfo> moveMap = new HashMap<>();
    private static final HashMap<UUID, MoveEntityInfo> moveEntityInfoMap = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerIn(PlayerEvent.PlayerLoggedInEvent e) {
        moveMap.put(e.getEntity().getUUID(), new MoveInfo(e.getEntity()));

    }


    @SubscribeEvent
    public static void onPlayerOut(PlayerEvent.PlayerLoggedOutEvent e) {
        moveMap.remove(e.getEntity().getUUID());

    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

            MoveInfo moveInfo = moveMap.get(event.getEntity().getUUID());
            if (moveInfo != null) {
                moveInfo.update(event.getEntity());
            }


    }

    @SubscribeEvent
    public static void onLivingTick(EntityTickEvent.Post event) {

        MoveEntityInfo moveInfo = moveEntityInfoMap.get(event.getEntity().getUUID());
        if (moveInfo != null) {
            moveInfo.updateEntity(event.getEntity());

        }

    }

    public static boolean isMoving(Player mp) {
        MoveInfo moveInfo = moveMap.get(mp.getUUID());
        if (moveInfo != null) {
            return moveInfo.isPosChanged();
        }
        return false;
    }


    public static boolean isMovingEntity(Entity entity) {
        MoveEntityInfo moveInfo = moveEntityInfoMap.get(entity.getUUID());
        if (moveInfo != null) {
            return moveInfo.isPosChanged();
        }
        return true;
    }


    public static class MoveInfo {
        private double lastPosX;
        private double lastPosY;
        private double lastPosZ;

        private boolean posChanged;

        public MoveInfo(Player player) {
            updateLastPos(player);
        }

        private void updateLastPos(Player player) {
            lastPosX = player.getX();
            lastPosY = player.getY();
            lastPosZ = player.getZ();
        }

        public void update(Player player) {
            posChanged = lastPosX != player.position().x() || lastPosY != player.position().y() || lastPosZ != player.position().z();
            if (posChanged) updateLastPos(player);

        }

        public boolean isPosChanged() {
            return posChanged;
        }

    }

    public static class MoveEntityInfo {
        private double lastPosX;
        private double lastPosY;
        private double lastPosZ;

        private boolean posChanged;

        public MoveEntityInfo(Player player) {
            updateLastPos(player);
        }


        private void updateLastPos(Player player) {
            lastPosX = player.getX();
            lastPosY = player.getY();
            lastPosZ = player.getZ();
        }

        private void updateEntityLastPos(Entity player) {
            lastPosX = player.getX();
            lastPosY = player.getY();
            lastPosZ = player.getZ();
        }

        public void updateEntity(Entity player) {
            posChanged = lastPosX != player.position().x() || lastPosY != player.position().y() || lastPosZ != player.position().z();
            if (posChanged) updateEntityLastPos(player);

        }

        public boolean isPosChanged() {
            return posChanged;
        }

    }
}
