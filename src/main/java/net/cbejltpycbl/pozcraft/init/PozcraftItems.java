package net.cbejltpycbl.pozcraft.init;

import net.cbejltpycbl.pozcraft.items.CigaretteCaseItem;
import net.cbejltpycbl.pozcraft.items.DrinkItem;
import net.cbejltpycbl.pozcraft.items.PozcraftFoodComponents;
import net.cbejltpycbl.pozcraft.items.KalzakItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.cbejltpycbl.pozcraft.Pozcraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class PozcraftItems {

    public static final Item CIGARETTE_CASE = registerItem("cigarette_case",
            new CigaretteCaseItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));

    public static final Item KALZAK = registerItem("kalzak",
            new KalzakItem(9, 1, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1)));

    public static final Item BEER = registerItem("beer",
            new DrinkItem(new FabricItemSettings().group(ItemGroup.BREWING)
                    .food(PozcraftFoodComponents.BEER).maxCount(1),
                    "tooltip.pozcraft.beer",
                    new String[]{
                            "tooltip.pozcraft.beer",
                            "tooltip.pozcraft.beer.shift1",
                            "tooltip.pozcraft.beer.shift2",
                            "tooltip.pozcraft.beer.shift3"
            }));

    public static final Item MOONSHINE = registerItem("moonshine",
            new DrinkItem(new FabricItemSettings().group(ItemGroup.BREWING)
                    .food(PozcraftFoodComponents.MOONSHINE).maxCount(1),
                    "tooltip.pozcraft.moonshine",
                    new String[]{
                            "tooltip.pozcraft.moonshine",
                            "tooltip.pozcraft.moonshine.shift1",
                            "tooltip.pozcraft.moonshine.shift2",
                            "tooltip.pozcraft.moonshine.shift3",
                            "tooltip.pozcraft.moonshine.shift4"
                    }));

    //===========================================================//

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Pozcraft.MOD_ID, name), item);
    }

    public static void registerCaseItems() {
        Pozcraft.LOGGER.debug(Pozcraft.MOD_ID + ": Регистрация портсигар");

        for(CigaretteCasesEnum cigaretteCasesEnum : CigaretteCasesEnum.values()) {
            registerItem(cigaretteCasesEnum.name().toLowerCase(),
                    new CigaretteCaseItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));
        }
    }
}
