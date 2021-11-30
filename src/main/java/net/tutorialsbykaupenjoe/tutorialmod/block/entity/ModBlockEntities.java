package net.tutorialsbykaupenjoe.tutorialmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.block.ModBlocks;

public class ModBlockEntities {
    public static BlockEntityType<LightningChannelerBlockEntity> LIGHTNING_CHANNELER_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(TutorialMod.MOD_ID, "lightning_channeler"),
                    FabricBlockEntityTypeBuilder.create(LightningChannelerBlockEntity::new,
                            ModBlocks.LIGHTNING_CHANNELER_BLOCK).build(null));
}
