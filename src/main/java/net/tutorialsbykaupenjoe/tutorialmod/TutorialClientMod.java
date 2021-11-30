package net.tutorialsbykaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.tutorialsbykaupenjoe.tutorialmod.screen.LightningChannelerScreen;
import net.tutorialsbykaupenjoe.tutorialmod.screen.ModScreenHandlers;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModRenderHelper;

@Environment(EnvType.CLIENT)
public class TutorialClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderHelper.setRenderLayers();
        ScreenRegistry.register(ModScreenHandlers.LIGHTNING_CHANNELER_SCREEN_HANDLER, LightningChannelerScreen::new);
    }
}
