package com.rumaruka.riskofmine.init;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


import static com.rumaruka.riskofmine.RiskOfMine.MODID;
import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class ROMSounds {

    public static final DeferredRegister<SoundEvent> REGISTER = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);

    public static final DeferredHolder<SoundEvent,SoundEvent> ROM_CHEST_OPEN = REGISTER.register("riskofmine.block.open_chest",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.block.open_chest")));
    public static final DeferredHolder<SoundEvent,SoundEvent> ROM_CHEST_NOT_MONEY = REGISTER.register("riskofmine.block.not_money",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.block.not_money")));
    public static final DeferredHolder<SoundEvent,SoundEvent> UI_VOID_REPLACE_ITEM = REGISTER.register("riskofmine.ui.void_replace",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.ui.void_replace")));

    public static final DeferredHolder<SoundEvent,SoundEvent> PROC_MT_SPAWN = REGISTER.register("riskofmine.proc.mt_spawn",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.proc.mt_spawn")));
    public static final DeferredHolder<SoundEvent,SoundEvent> PROC_MT_IMPACT = REGISTER.register("riskofmine.proc.mt_impact",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.proc.mt_impact")));
    public static final DeferredHolder<SoundEvent,SoundEvent> ROM_PLAYER_FEATHER = REGISTER.register("riskofmine.player.feather_1",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.player.feather_1")));
    public static final DeferredHolder<SoundEvent,SoundEvent> ROM_PLAYER_LEVEL_UP = REGISTER.register("riskofmine.player.level_up",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.player.level_up")));

    public static final DeferredHolder<SoundEvent,SoundEvent> ROM_SCRAPPER_WORK = REGISTER.register("riskofmine.proc.scrapper",
            () -> SoundEvent.createVariableRangeEvent(rl("riskofmine.proc.scrapper")));
}
