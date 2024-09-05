package com.rumaruka.riskofmine.ntw.cmd.reg;


import com.mojang.brigadier.CommandDispatcher;
import com.rumaruka.riskofmine.ntw.cmd.barrier.BarrierAddCommand;
import com.rumaruka.riskofmine.ntw.cmd.barrier.BarrierSetCommand;
import com.rumaruka.riskofmine.ntw.cmd.lunar.LunarAddCommand;
import com.rumaruka.riskofmine.ntw.cmd.lunar.LunarSetCommand;
import com.rumaruka.riskofmine.ntw.cmd.money.MoneyAddCommand;
import com.rumaruka.riskofmine.ntw.cmd.money.MoneySetCommand;
import com.rumaruka.riskofmine.ntw.cmd.shields.ShieldsAddCommand;
import com.rumaruka.riskofmine.ntw.cmd.shields.ShieldsSetCommand;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class ROMCommand {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        BarrierAddCommand.register(dispatcher);
        BarrierSetCommand.register(dispatcher);
        MoneyAddCommand.register(dispatcher);
        MoneySetCommand.register(dispatcher);
        LunarAddCommand.register(dispatcher);
        LunarSetCommand.register(dispatcher);
        ShieldsAddCommand.register(dispatcher);
        ShieldsSetCommand.register(dispatcher);

    }
}
