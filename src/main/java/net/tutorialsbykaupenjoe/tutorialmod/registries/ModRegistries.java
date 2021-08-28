package net.tutorialsbykaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

public class ModRegistries {

    public static void registerModFuels() {
        System.out.println("Now registering Fuels for " + TutorialMod.MOD_ID);
        FuelRegistry registry = FuelRegistry.INSTANCE;

        registry.add(ModItems.IRON_WOOL, 300); // x / 20
        registry.add(ModItems.PEPPER, 60);
    }
}
