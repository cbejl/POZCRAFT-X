package net.cbejltpycbl.pozcraft.init;

import net.cbejltpycbl.pozcraft.items.CigaretteCase;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.cbejltpycbl.pozcraft.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CaseItems {

    public static final ItemGroup CIGARETTE_CASE_GROUP = FabricItemGroupBuilder.build(new Identifier(Main.MOD_ID, "cigarette_cases"),
            () -> new ItemStack(CaseItems.CIGARETTE_CASE));


    //===========================================================//

    public static final Item CIGARETTE_CASE = registerItem("cigarette_case",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_RED = registerItem("cigarette_case_red",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_PINK = registerItem("cigarette_case_pink",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_BROWN = registerItem("cigarette_case_brown",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_BLACK = registerItem("cigarette_case_black",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_GRAY = registerItem("cigarette_case_gray",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_BLUE = registerItem("cigarette_case_blue",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_LIME = registerItem("cigarette_case_lime",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_MAGENTA = registerItem("cigarette_case_magenta",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_PURPLE = registerItem("cigarette_case_purple",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_YELLOW = registerItem("cigarette_case_yellow",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_CYAN = registerItem("cigarette_case_cyan",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_LIGHT_GRAY = registerItem("cigarette_case_light_gray",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_WHITE = registerItem("cigarette_case_white",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_ORANGE = registerItem("cigarette_case_orange",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_GREEN = registerItem("cigarette_case_green",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));

    public static final Item CIGARETTE_CASE_LIGHT_BLUE = registerItem("cigarette_case_light_blue",
            new CigaretteCase(new FabricItemSettings().group(CIGARETTE_CASE_GROUP).maxCount(1)));
    //===========================================================//

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Main.MOD_ID, name), item);
    }

    public static void registerCaseItems() {


    }
}
