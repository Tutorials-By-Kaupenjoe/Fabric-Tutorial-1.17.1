package net.tutorialsbykaupenjoe.tutorialmod.world.features;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> REDWOOD_TREE_KEY = registerKey("redwood_spawn");
    public static final RegistryKey<PlacedFeature> BLUEBELLS_KEY = registerKey("bluebells");
    public static final RegistryKey<PlacedFeature> RUBY_ORE_KEY = registerKey("ruby_ore");

    public static final PlacedFeature REDWOOD_PLACED = registerPlacedFeature("redwood_spawn",
            ModConfiguredFeatures.REDWOOD_TREE_RANDOM.withPlacement(VegetationPlacedFeatures.modifiers(
                    PlacedFeatures.createCountExtraModifier(1, 0.1f, 2))));

    public static final PlacedFeature BLUEBELLS_PLACED = registerPlacedFeature("bluebells",
            ModConfiguredFeatures.BLUEBELLS.withPlacement(RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                    PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));


    public static final PlacedFeature RUBY_ORE_PLACED = registerPlacedFeature("ruby_ore",
            ModConfiguredFeatures.RUBY_ORE.withPlacement(modifiersWithCount(7,
                    HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-80), YOffset.aboveBottom(80)))));



    public static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static PlacedFeature registerPlacedFeature(String name, PlacedFeature placedFeature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(TutorialMod.MOD_ID, name), placedFeature);
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(TutorialMod.MOD_ID, name));
    }
}
