package net.tutorialsbykaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModRenderHelper;

@Environment(EnvType.CLIENT)
public class TutorialClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModRenderHelper.setRenderLayers();
    }
}
