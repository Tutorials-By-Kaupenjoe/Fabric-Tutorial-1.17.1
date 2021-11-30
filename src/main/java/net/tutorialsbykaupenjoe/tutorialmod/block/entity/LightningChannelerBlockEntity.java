package net.tutorialsbykaupenjoe.tutorialmod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tutorialsbykaupenjoe.tutorialmod.item.ModItems;
import net.tutorialsbykaupenjoe.tutorialmod.item.inventory.ImplementedInventory;
import net.tutorialsbykaupenjoe.tutorialmod.recipe.LightningChannelerRecipe;
import net.tutorialsbykaupenjoe.tutorialmod.screen.LightningChannelerScreenHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class LightningChannelerBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(3, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    // How many ticks it will take to craft the item (divide by twenty to get seconds count)
    // In our case this should be even divisible by 21 as that's our pixel count for our progress arrow
    private int maxProgress = 63;

    public LightningChannelerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIGHTNING_CHANNELER_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                switch (index) {
                    case 0: return LightningChannelerBlockEntity.this.progress;
                    case 1: return LightningChannelerBlockEntity.this.maxProgress;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: LightningChannelerBlockEntity.this.progress = value; break;
                    case 1: LightningChannelerBlockEntity.this.maxProgress = value; break;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    @Override
    public Text getDisplayName() {
        return new LiteralText("Lightning Channeler");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new LightningChannelerScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, inventory);
        return super.writeNbt(nbt);
    }

    public static void tick(World world, BlockPos pos, BlockState state, LightningChannelerBlockEntity entity) {
        if(hasRecipe(entity)) {
            entity.progress++;
            if(entity.progress > entity.maxProgress) {
                craftItem(entity);
            }
        } else {
            entity.resetProgress();
        }
    }

    private static boolean hasRecipe(LightningChannelerBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<LightningChannelerRecipe> match = world.getRecipeManager()
                .getFirstMatch(LightningChannelerRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && evaluateWeather(match.get().getWeather(), world)
                && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(LightningChannelerBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        Optional<LightningChannelerRecipe> match = world.getRecipeManager()
                .getFirstMatch(LightningChannelerRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            entity.removeStack(0,1);
            entity.removeStack(1,1);
            entity.setStack(2, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(2).getCount() + 1));

            if(!world.isClient() && match.get().getWeather() == LightningChannelerRecipe.Weather.THUNDERING) {
                EntityType.LIGHTNING_BOLT.spawn((ServerWorld) world, null, null, null, entity.pos,
                        SpawnReason.TRIGGERED, true, true);
            }

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(2).getItem() == output.getItem() || inventory.getStack(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(2).getMaxCount() > inventory.getStack(2).getCount();
    }

    private static boolean evaluateWeather(LightningChannelerRecipe.Weather weather, World world) {
        boolean matches = false;

        if(weather == LightningChannelerRecipe.Weather.CLEAR && !world.isRaining()) {
            matches = true;
        }

        if(weather == LightningChannelerRecipe.Weather.RAIN && world.isRaining()) {
            matches = true;
        }

        if(weather == LightningChannelerRecipe.Weather.THUNDERING && world.isThundering()) {
            matches = true;
        }

        return matches;
    }
}
