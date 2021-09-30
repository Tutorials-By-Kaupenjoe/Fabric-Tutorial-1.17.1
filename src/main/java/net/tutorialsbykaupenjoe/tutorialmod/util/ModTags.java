package net.tutorialsbykaupenjoe.tutorialmod.util;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.tutorialsbykaupenjoe.tutorialmod.TutorialMod;

public class ModTags {
    public static class Blocks {
        public static final Tag<Block> VALUABLE_BLOCKS = createTag("valuable_blocks");

        private static Tag<Block> createTag(String name) {
            return TagFactory.BLOCK.create(new Identifier(TutorialMod.MOD_ID, name));
        }

        private static Tag<Block> createCommonTag(String name) {
            return TagFactory.BLOCK.create(new Identifier("c", name));
        }
    }

    public static class Items {

        public static final Tag<Item> GEMS = createCommonTag("gems");
        public static final Tag<Item> RUBIES = createCommonTag("rubies");

        private static Tag<Item> createTag(String name) {
            return TagFactory.ITEM.create(new Identifier(TutorialMod.MOD_ID, name));
        }

        private static Tag<Item> createCommonTag(String name) {
            return TagFactory.ITEM.create(new Identifier("c", name));
        }
    }
}
