package com.rumaruka.riskofmine.datagen.loot;

import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.Set;

import static com.rumaruka.riskofmine.RiskOfMine.rl;

public class ROMLootTables {

    private static final Set<ResourceKey<LootTable>> LOCATIONS = Sets.newHashSet();
    private static final Set<ResourceKey<LootTable>> IMMUTABLE_LOCATIONS = Collections.unmodifiableSet(LOCATIONS);

    public static final ResourceKey<LootTable> SMALL_CHEST = register("chests/small_chest");
   // public static final ResourceKey<LootTable> LARGE_CHEST = register("chests/large_chest");
   // public static final ResourceKey<LootTable> LEGENDARY_CHEST = register("chests/legendary_chest");
   // public static final ResourceKey<LootTable> LUNAR_CHEST = register("chests/lunar_chest");
//
   // public static final ResourceKey<LootTable> EQUIPMENT_BARREL = register("chests/equipment_barrel");
   // //Category Chest
   // public static final ResourceKey<LootTable> DAMAGE_CHEST = register("chests/damage_chest");
   // public static final ResourceKey<LootTable> HEALING_CHEST = register("chests/healing_chest");
   // public static final ResourceKey<LootTable> UTILITY_CHEST = register("chests/utility_chest");


    private static ResourceKey<LootTable> register(String p_78768_) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, rl(p_78768_)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> p_335977_) {
        if (LOCATIONS.add(p_335977_)) {
            return p_335977_;
        } else {
            throw new IllegalArgumentException(p_335977_.location() + " is already a registered built-in loot table");
        }
    }

    public static Set<ResourceKey<LootTable>> all() {
        return IMMUTABLE_LOCATIONS;
    }

}
