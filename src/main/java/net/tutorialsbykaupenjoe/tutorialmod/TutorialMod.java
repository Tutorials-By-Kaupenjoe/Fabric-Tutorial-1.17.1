package net.tutorialsbykaupenjoe.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.biome.Biome;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;
import net.tutorialsbykaupenjoe.tutorialmod.config.ModConfigs;
import net.tutorialsbykaupenjoe.tutorialmod.enchantments.ModEnchantments;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;
import net.tutorialsbykaupenjoe.tutorialmod.recipe.ModRecipes;
import net.tutorialsbykaupenjoe.tutorialmod.registries.ModRegistries;
import net.tutorialsbykaupenjoe.tutorialmod.sounds.ModSounds;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModCommandRegister;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModEventsRegister;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModLootTableModifiers;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModRenderHelper;
import net.tutorialsbykaupenjoe.tutorialmod.world.biome.ModBiomes;
import net.tutorialsbykaupenjoe.tutorialmod.world.dimension.ModDimensions;
import net.tutorialsbykaupenjoe.tutorialmod.world.dimension.ModPortals;
import net.tutorialsbykaupenjoe.tutorialmod.world.features.ModConfiguredFeatures;
import net.tutorialsbykaupenjoe.tutorialmod.world.gen.ModWorldGen;

public class TutorialMod implements ModInitializer {

	public static final String MOD_ID = "tutorialmod";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModConfigs.registerConfigs();

		ModConfiguredFeatures.registerConfiguredFeatures();
		ModBiomes.initBiomes();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModRegistries.registerModFuels();
		ModRegistries.registerStrippables();

		ModCommandRegister.registerCommands();
		ModEventsRegister.registerEvents();

		ModRecipes.register();

		ModLootTableModifiers.modifyLootTables();
		ModSounds.registerSounds();

		ModEnchantments.registerModEnchantments();

		ModBiomes.registerBiomes();
		ModWorldGen.generateModWorldGen();
		ModDimensions.register();

		ModPortals.registerPortals();

		System.out.println("Hello Fabric world!");
	}
}
