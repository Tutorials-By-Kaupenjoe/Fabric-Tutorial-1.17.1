package net.tutorialsbykaupenjoe.tutorialmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;
import net.tutorialsbykaupenjoe.tutorialmod.sounds.ModSounds;
import net.tutorialsbykaupenjoe.tutorialmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = Objects.requireNonNull(context.getPlayer());
            boolean foundBlock = false;

            for(int i = 0; i <= positionClicked.getY(); i++) {
                Block blockBelow = context.getWorld().getBlockState(positionClicked.down(i)).getBlock();

                if(isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(blockBelow, positionClicked.add(0, -i, 0), player);
                    foundBlock = true;

                    if(hasPlayerDataTablet(player)) {
                        addNbtToDataTablet(player, positionClicked.add(0, -i, 0), blockBelow);
                    }

                    context.getWorld().playSound(player, positionClicked,
                            ModSounds.SMALL_EXPLOSION, SoundCategory.AMBIENT, 1f, 1f);

                    break;
                }
            }
            if(!foundBlock) {
                player.sendMessage(new LiteralText("Didn't find any valuable below!"), false);
            }
        }

        context.getStack().damage(1, context.getPlayer(),
                (player) -> player.sendToolBreakStatus(player.getActiveHand()));

        return super.useOnBlock(context);
    }

    private void addNbtToDataTablet(PlayerEntity player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getStack(getFirstInventoryIndex(player, ModItems.DATA_TABLET));
        NbtCompound nbtData = new NbtCompound();
        nbtData.putString("Last Found Ore", "Found " + blockBelow.asItem().getName().getString() + " at (" +
                pos.getX() + ", "+ pos.getY() + ", "+ pos.getZ() + ")");

        dataTablet.setNbt(nbtData);
    }

    private boolean hasPlayerDataTablet(PlayerEntity player) {
        return hasPlayerStackInInventory(player, ModItems.DATA_TABLET);
    }

    private boolean hasPlayerStackInInventory(PlayerEntity player, Item item) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isItemEqual(new ItemStack(item))) {
                return true;
            }
        }

        return false;
    }

    private int getFirstInventoryIndex(PlayerEntity player, Item item) {
        for(int i = 0; i < player.getInventory().size(); i++) {
            ItemStack currentStack = player.getInventory().getStack(i);
            if (!currentStack.isEmpty() && currentStack.isItemEqual(new ItemStack(item))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {

        if(Screen.hasShiftDown()){
            tooltip.add(new TranslatableText("tooltip.tutorialmod.dowsing_rod_shift"));
        } else {
            tooltip.add(new TranslatableText("tooltip.tutorialmod.dowsing_rod"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    private boolean isValuableBlock(Block block) {
        return block.getDefaultState().isIn(ModTags.Blocks.VALUABLE_BLOCKS);
    }

    private void outputValuableCoordinates(Block blockFound, BlockPos pos, PlayerEntity player) {
        player.sendMessage(new LiteralText("Found " + blockFound.asItem().getName().getString() + " at (" +
                pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")"), false);
    }
}
