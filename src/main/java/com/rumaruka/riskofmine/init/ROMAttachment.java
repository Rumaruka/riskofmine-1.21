package com.rumaruka.riskofmine.init;

import com.rumaruka.riskofmine.common.cap.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static com.rumaruka.riskofmine.RiskOfMine.MODID;

public class ROMAttachment {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Barrier>> BARRIER = ATTACHMENT_TYPES.register("barrier", () -> AttachmentType.builder(()->new Barrier()).serialize(Barrier.CODEC).copyOnDeath().build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Lunar>> LUNAR = ATTACHMENT_TYPES.register("lunar", () -> AttachmentType.builder(()->new Lunar()).serialize(Lunar.CODEC).copyOnDeath().build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Money>> MONEY = ATTACHMENT_TYPES.register("money", () -> AttachmentType.builder(()->new Money()).serialize(Money.CODEC).copyOnDeath().build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Shields>> SHIELDS = ATTACHMENT_TYPES.register("shields", () -> AttachmentType.builder(()->new Shields()).serialize(Shields.CODEC).copyOnDeath().build());
    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Timer>> TIMER = ATTACHMENT_TYPES.register("timer", () -> AttachmentType.builder(()->new Timer()).serialize(Timer.CODEC).copyOnDeath().build());

   public static void setup(IEventBus bus) {
       ATTACHMENT_TYPES.register(bus);
   }

}
