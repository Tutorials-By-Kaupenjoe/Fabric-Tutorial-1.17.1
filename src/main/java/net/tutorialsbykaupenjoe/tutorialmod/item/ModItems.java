package net.tutorialsbykaupenjoe.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;
import net.tutorialsbykaupenjoe.tutorialmod.item.custom.DowsingRodItem;
import net.tutorialsbykaupenjoe.tutorialmod.item.custom.ModAxeItem;
import net.tutorialsbykaupenjoe.tutorialmod.item.custom.ModHoeItem;
import net.tutorialsbykaupenjoe.tutorialmod.item.custom.ModPickaxeItem;

public class ModItems {

    public static final Item RUBY = registerItem("ruby",
            new Item(new FabricItemSettings().group(ModItemGroup.RUBY)));

    public static final Item PEPPER = registerItem("pepper", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).build())
            .group(ModItemGroup.RUBY)));

    public static final Item DOWSING_ROD = registerItem("dowsing_rod",
            new DowsingRodItem(new FabricItemSettings().group(ModItemGroup.RUBY).maxDamage(10)));

    public static final Item IRON_WOOL = registerItem("iron_wool",
            new Item(new FabricItemSettings().group(ModItemGroup.RUBY)));

    public static final Item RUBY_SWORD = registerItem("ruby_sword",
            new SwordItem(ModToolMaterial.RUBY, 2, 1f,
                    new FabricItemSettings().group(ModItemGroup.RUBY)));
    public static final Item RUBY_SHOVEL = registerItem("ruby_shovel",
            new ShovelItem(ModToolMaterial.RUBY, 0, 1f,
                    new FabricItemSettings().group(ModItemGroup.RUBY)));

    public static final Item RUBY_PICKAXE = registerItem("ruby_pickaxe",
            new ModPickaxeItem(ModToolMaterial.RUBY, 0, 1f,
                    new FabricItemSettings().group(ModItemGroup.RUBY)));
    public static final Item RUBY_AXE = registerItem("ruby_axe",
            new ModAxeItem(ModToolMaterial.RUBY, 4, -1f,
                    new FabricItemSettings().group(ModItemGroup.RUBY)));
    public static final Item RUBY_HOE = registerItem("ruby_hoe",
            new ModHoeItem(ModToolMaterial.RUBY, 0, 0f,
                    new FabricItemSettings().group(ModItemGroup.RUBY)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        System.out.println("Registering Mod Items for " + TutorialMod.MOD_ID);
    }
}
