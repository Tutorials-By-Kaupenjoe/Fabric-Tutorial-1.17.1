package net.tutorialsbykaupenjoe.tutorialmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.ModPressurePlateBlock;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.ModStairsBlock;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.ModStoneButtonBlock;
import net.tutorialsbykaupenjoe.tutorialmod.block.custom.StatusBlock;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItemGroup;

public class ModBlocks {

    public static final Block RUBY_ORE = registerBlock("ruby_ore",
            new Block(FabricBlockSettings.of(Material.STONE).strength(4.0f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block STATUS_BLOCK = registerBlock("status_block",
            new StatusBlock(FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block RUBY_STAIRS = registerBlock("ruby_stairs",
            new ModStairsBlock(ModBlocks.RUBY_BLOCK.getDefaultState(),
                    FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block RUBY_SLAB = registerBlock("ruby_slab",
            new SlabBlock(FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block RUBY_FENCE = registerBlock("ruby_fence",
            new FenceBlock(FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block RUBY_FENCE_GATE = registerBlock("ruby_fence_gate",
            new FenceGateBlock(FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));


    public static final Block RUBY_BUTTON = registerBlock("ruby_button",
            new ModStoneButtonBlock(FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));

    public static final Block RUBY_PRESSURE_PLATE = registerBlock("ruby_pressure_plate",
            new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                    FabricBlockSettings.of(Material.STONE).strength(6f)
                    .breakByTool(FabricToolTags.PICKAXES, 2).requiresTool()));


    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registry.BLOCK, new Identifier(TutorialMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(ModItemGroup.RUBY)));
    }

    public static void registerModBlocks() {
        System.out.println("Registering ModBlocks for " + TutorialMod.MOD_ID);
    }
}
