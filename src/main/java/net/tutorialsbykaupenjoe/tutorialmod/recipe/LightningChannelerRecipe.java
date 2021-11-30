package net.tutorialsbykaupenjoe.tutorialmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Objects;

public class LightningChannelerRecipe implements Recipe<SimpleInventory> {
    public enum Weather {
        CLEAR,
        RAIN,
        THUNDERING;

        public static Weather getWeatherByString(String s) {
            return Objects.equals(s, "thundering") ? THUNDERING : Objects.equals(s, "rain") ? RAIN : CLEAR;
        }
    }

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private final Weather weather;

    public LightningChannelerRecipe(Identifier id, ItemStack output,
                                    DefaultedList<Ingredient> recipeItems, Weather weather) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(recipeItems.get(0).test(inventory.getStack(0))) {
            return recipeItems.get(1).test(inventory.getStack(1));
        }

        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<LightningChannelerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "lightning";
    }

    public static class Serializer implements RecipeSerializer<LightningChannelerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "lightning";
        // this is the name given in the json file

        @Override
        public LightningChannelerRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            String weather = JsonHelper.getString(json, "weather");

            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(2, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new LightningChannelerRecipe(id, output,
                    inputs, Weather.getWeatherByString(weather));
        }

        @Override
        public LightningChannelerRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            return new LightningChannelerRecipe(id, output,
                    inputs, buf.readEnumConstant(Weather.class));
        }

        @Override
        public void write(PacketByteBuf buf, LightningChannelerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
            buf.writeEnumConstant(recipe.weather);
        }
    }
}
