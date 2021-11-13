package net.tutorialsbykaupenjoe.tutorialmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.tutorialsbykaupenjoe.tutorialmod.world.features.ModConfiguredFeatures;

public class ModFlowerGeneration {
    public static void generateFlowers() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatures.BLUEBELLS_KEY);
    }
}
