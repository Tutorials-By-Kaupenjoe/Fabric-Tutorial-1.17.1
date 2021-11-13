package net.gleason.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.gleason.tutorialmod.TutorialMod;
import net.gleason.tutorialmod.item.ModItems;
import net.gleason.tutorialmod.block.ModBlocks;

public class ModRegistries {

    public static void registerModFuels() {
        System.out.println("Now registering Fuels for " + TutorialMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.IRON_WOOL, 300); // x / 20
        registry.add(ModItems.PEPPER, 60);
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.REDWOOD_LOG, ModBlocks.STRIPPED_REDWOOD_LOG);
        StrippableBlockRegistry.register(ModBlocks.REDWOOD_WOOD, ModBlocks.STRIPPED_REDWOOD_WOOD);
    }
}
