package net.tutorialsbykaupenjoe.tutorialmod.world.features.tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.tutorialsbykaupenjoe.tutorialmod.world.features.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class RedwoodSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.REDWOOD_TREE;
    }
}
