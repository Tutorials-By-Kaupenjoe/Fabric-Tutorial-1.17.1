package net.tutorialsbykaupenjoe.tutorialmod.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;

public class ModSurfaceConfigs {
    public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> LAVA_SURFACE_BUILDER = register("lava_surface",
            SurfaceBuilder.DEFAULT.withConfig(new TernarySurfaceConfig(
                    Blocks.MAGMA_BLOCK.getDefaultState(),
                    ModBlocks.DICE_BLOCK.getDefaultState(),
                    Blocks.SANDSTONE.getDefaultState())));

    private static <T extends SurfaceConfig> ConfiguredSurfaceBuilder<T> register(String name,
                                                                                  ConfiguredSurfaceBuilder<T> surfaceBuilder) {
        return Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER,
                new Identifier(TutorialMod.MOD_ID, name), surfaceBuilder);
    }
}
