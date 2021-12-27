package net.tutorialsbykaupenjoe.tutorialmod.world.features;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    public static final ConfiguredFeature<TreeFeatureConfig, ?> REDWOOD_TREE = register("redwood",
            Feature.TREE.configure(new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.REDWOOD_LOG),
                    new StraightTrunkPlacer(8, 4, 3),
                    BlockStateProvider.of(ModBlocks.REDWOOD_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)).build()));

    public static final ConfiguredFeature<RandomFeatureConfig, ?> REDWOOD_TREE_RANDOM = register("redwood_feature",
            Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(new RandomFeatureEntry(
                    REDWOOD_TREE.withWouldSurviveFilter(ModBlocks.REDWOOD_SAPLING), 0.1f)),
                    REDWOOD_TREE.withWouldSurviveFilter(ModBlocks.REDWOOD_SAPLING))));


    public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> BLUEBELLS =
            ModConfiguredFeatures.register("bluebells_feature", Feature.FLOWER.configure(
                    createRandomPatchFeatureConfig(BlockStateProvider.of(ModBlocks.BLUEBELLS), 64)));

    public static final List<OreFeatureConfig.Target> OVERWORLD_RUBY_ORES = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.RUBY_ORE.getDefaultState()));

    public static final ConfiguredFeature<?, ?> RUBY_ORE = register("ruby_ore",
            Feature.ORE.configure(new OreFeatureConfig(OVERWORLD_RUBY_ORES, 9)));



    public static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(TutorialMod.MOD_ID, name),
                configuredFeature);
    }

    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries,
                Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }

    public static void registerConfiguredFeatures() {
        System.out.println("Registering ModConfiguredFeatures for " + TutorialMod.MOD_ID);
    }
}
