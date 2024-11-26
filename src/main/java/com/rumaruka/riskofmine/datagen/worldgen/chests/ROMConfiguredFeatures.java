package com.rumaruka.riskofmine.datagen.worldgen.chests;

import com.rumaruka.riskofmine.RiskOfMine;
import com.rumaruka.riskofmine.init.ROMBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.neoforged.neoforge.common.Tags;

import java.util.List;

public class ROMConfiguredFeatures {
    protected static ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_CHEST = createKey("overworld_chest");
    protected static ResourceKey<ConfiguredFeature<?, ?>> NETHER_CHEST = createKey("nether_chest");
    protected static ResourceKey<ConfiguredFeature<?, ?>> END_CHEST = createKey("end_chest");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        RuleTest stoneReplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest flowerReplace = new TagMatchTest(BlockTags.FLOWERS);


        RuleTest netherReplacable = new TagMatchTest(Tags.Blocks.NETHERRACKS);
        RuleTest endReplacable = new TagMatchTest(Tags.Blocks.END_STONES);
        BlockState smallChest
                = ROMBlocks.SMALL_CHEST.defaultBlockState();
        List<OreConfiguration.TargetBlockState> netherOre =
                List.of(
                        OreConfiguration.target(netherReplacable, smallChest)

                );
        List<OreConfiguration.TargetBlockState> endOre =
                List.of(

                        OreConfiguration.target(endReplacable, smallChest)
                );


        List<OreConfiguration.TargetBlockState> overOre =
                List.of(
                        OreConfiguration.target(stoneReplacable, smallChest),
                        OreConfiguration.target(flowerReplace, smallChest)

                );
        register(context, OVERWORLD_CHEST, Feature.ORE, new OreConfiguration(overOre, 2));
        register(context, NETHER_CHEST, Feature.ORE, new OreConfiguration(netherOre, 4));
        register(context, END_CHEST, Feature.ORE, new OreConfiguration(endOre, 4));

    }

    private static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(RiskOfMine.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int p_195204_) {
        return FeatureUtils.simpleRandomPatchConfiguration(
                p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(p_195203_))
        );
    }
}


