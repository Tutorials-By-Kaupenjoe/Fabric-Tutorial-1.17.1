package net.gleason.tutorialmod.block.custom;

import net.gleason.tutorialmod.item.ModItems;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;

public class PepperPlantBlock extends CropBlock {
    public PepperPlantBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.PEPPER_SEEDS;
    }
}
