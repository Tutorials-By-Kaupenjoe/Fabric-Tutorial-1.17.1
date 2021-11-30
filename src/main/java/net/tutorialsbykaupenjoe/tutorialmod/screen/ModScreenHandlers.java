package net.tutorialsbykaupenjoe.tutorialmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;

public class ModScreenHandlers {
    public static ScreenHandlerType<LightningChannelerScreenHandler> LIGHTNING_CHANNELER_SCREEN_HANDLER =
            ScreenHandlerRegistry.registerSimple(new Identifier(TutorialMod.MOD_ID, "lightning_channeler"),
                    LightningChannelerScreenHandler::new);
}
