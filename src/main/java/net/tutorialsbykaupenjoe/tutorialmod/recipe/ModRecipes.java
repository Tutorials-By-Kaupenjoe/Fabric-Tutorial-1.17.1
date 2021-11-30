package net.tutorialsbykaupenjoe.tutorialmod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;

public class ModRecipes {
    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(TutorialMod.MOD_ID, LightningChannelerRecipe.Serializer.ID),
                LightningChannelerRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(TutorialMod.MOD_ID, LightningChannelerRecipe.Type.ID),
                LightningChannelerRecipe.Type.INSTANCE);
    }
}
