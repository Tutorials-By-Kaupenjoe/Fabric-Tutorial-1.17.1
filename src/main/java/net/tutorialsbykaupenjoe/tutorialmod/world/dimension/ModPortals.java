package net.tutorialsbykaupenjoe.tutorialmod.world.dimension;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;

public class ModPortals {
    public static void registerPortals() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(ModBlocks.RUBY_BLOCK)
                .lightWithItem(ModItems.PEPPER)
                .destDimID(ModDimensions.KJDIM_KEY.getValue())
                .tintColor(150, 200, 60)
                .registerPortal();
    }
}
