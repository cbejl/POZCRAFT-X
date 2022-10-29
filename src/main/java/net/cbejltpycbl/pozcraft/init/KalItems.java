package net.cbejltpycbl.pozcraft.init;

import net.cbejltpycbl.pozcraft.Main;
import net.cbejltpycbl.pozcraft.items.kalzak.common.item.BackpackItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class KalItems {

    public static final ItemGroup KALGROUP = FabricItemGroupBuilder.build(new Identifier(Main.MOD_ID, "kal"),
            () -> new ItemStack(KalItems.KALZAK));

    //===========================================================//
    public static final Item KALZAK = registerItem("kalzak",
            new BackpackItem(9, 1, new FabricItemSettings().group(KALGROUP).maxCount(1)));

    //===========================================================//

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), item);
    }

    public static void registerKalItems() {}
}
