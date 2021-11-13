package net.gleason.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.gleason.tutorialmod.item.ModItems;
import net.gleason.tutorialmod.block.ModBlocks;
import net.gleason.tutorialmod.config.ModConfigs;
import net.gleason.tutorialmod.enchantments.ModEnchantments;
import net.gleason.tutorialmod.registries.ModRegistries;
import net.gleason.tutorialmod.sounds.ModSounds;
import net.gleason.tutorialmod.util.ModCommandRegister;
import net.gleason.tutorialmod.util.ModEventsRegister;
import net.gleason.tutorialmod.util.ModLootTableModifiers;
import net.gleason.tutorialmod.util.ModRenderHelper;
import net.gleason.tutorialmod.world.features.ModConfiguredFeatures;
import net.gleason.tutorialmod.world.gen.ModWorldGen;

public class TutorialMod implements ModInitializer {

	public static final String MOD_ID = "tutorialmod";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModConfigs.registerConfigs();

		ModConfiguredFeatures.registerConfiguredFeatures();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModRegistries.registerModFuels();
		ModRegistries.registerStrippables();

		ModRenderHelper.setRenderLayers();

		ModCommandRegister.registerCommands();
		ModEventsRegister.registerEvents();

		ModLootTableModifiers.modifyLootTables();
		ModSounds.registerSounds();

		ModEnchantments.registerModEnchantments();

		ModWorldGen.generateModWorldGen();

		System.out.println("Hello Fabric world!");
	}
}
