package net.cbejltpycbl.pozcraft.recipe;

import net.cbejltpycbl.pozcraft.Pozcraft;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Pozcraft.MOD_ID, DistillingRecipe.Serializer.ID),
                DistillingRecipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Pozcraft.MOD_ID, DistillingRecipe.Type.ID),
                DistillingRecipe.Type.INSTANCE);
    }
}
