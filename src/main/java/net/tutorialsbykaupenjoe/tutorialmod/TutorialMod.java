package net.tutorialsbykaupenjoe.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

public class TutorialMod implements ModInitializer {

	public static final String MOD_ID = "tutorialmod";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		System.out.println("Hello Fabric world!");
	}
}
