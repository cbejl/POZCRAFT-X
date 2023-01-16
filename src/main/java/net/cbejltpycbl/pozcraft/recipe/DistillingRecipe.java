package net.cbejltpycbl.pozcraft.recipe;

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

public class DistillingRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;
    private final int time;
    public DistillingRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this(id, output, recipeItems, 600);
    }

    public DistillingRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems, int time) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.time = time;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient()) {
            return false;
        }

        return recipeItems.get(0).test(inventory.getStack(0));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    public int getTime() {return time;}
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<DistillingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "distilling";
    }

    public static class Serializer implements RecipeSerializer<DistillingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "distilling";

        @Override
        public DistillingRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            int time = JsonHelper.getInt(json, "time");

            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new DistillingRecipe(id, output, inputs, time);
        }

        @Override
        public DistillingRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            ItemStack output = buf.readItemStack();
            int time = buf.readInt();

            return new DistillingRecipe(id, output, inputs, time);
        }

        @Override
        public void write(PacketByteBuf buf, DistillingRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for(Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
            buf.writeInt(recipe.getTime());
        }
    }
}
