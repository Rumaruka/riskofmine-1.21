package com.rumaruka.riskofmine.datagen.worldgen.chests.features;

import com.mojang.serialization.Codec;
import com.rumaruka.riskofmine.datagen.loot.ROMLootTables;
import com.rumaruka.riskofmine.init.ROMBlocks;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.stream.IntStream;

public class ROMSmallChestFeature extends Feature<NoneFeatureConfiguration> {


    public ROMSmallChestFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159749_) {
        RandomSource randomsource = p_159749_.random();
        WorldGenLevel worldgenlevel = p_159749_.level();
        ChunkPos chunkpos = new ChunkPos(p_159749_.origin());
        IntArrayList intarraylist = Util.toShuffledList(IntStream.rangeClosed(chunkpos.getMinBlockX(), chunkpos.getMaxBlockX()), randomsource);
        IntArrayList intarraylist1 = Util.toShuffledList(IntStream.rangeClosed(chunkpos.getMinBlockZ(), chunkpos.getMaxBlockZ()), randomsource);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for (Integer integer : intarraylist) {
            for (Integer integer1 : intarraylist1) {
                blockpos$mutableblockpos.set(integer, 0, integer1);
                BlockPos blockpos = worldgenlevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, blockpos$mutableblockpos);
                if (worldgenlevel.isEmptyBlock(blockpos) || worldgenlevel.getBlockState(blockpos).getCollisionShape(worldgenlevel, blockpos).isEmpty()) {
                    worldgenlevel.setBlock(blockpos, ROMBlocks.SMALL_CHEST.defaultBlockState(), 2);
                    RandomizableContainer.setBlockEntityLootTable(worldgenlevel, randomsource, blockpos, ROMLootTables.SMALL_CHEST);
                    return true;
                }
            }
        }

        return false;
    }
}