package net.cbejltpycbl.pozcraft.block;

import net.cbejltpycbl.pozcraft.Pozcraft;
import net.cbejltpycbl.pozcraft.block.custom.DistillingBlock;
import net.cbejltpycbl.pozcraft.init.PozcraftItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block DISTILLATION_APPARATUS = registerBlock("distillation_apparatus",
            new DistillingBlock(FabricBlockSettings.of(Material.METAL).strength(3.5f).nonOpaque()
                    .luminance(state -> state.get(DistillingBlock.LIT) ? 4 : 0)),
            ItemGroup.BREWING);



    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(Pozcraft.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Pozcraft.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(Pozcraft.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Pozcraft.LOGGER.debug(Pozcraft.MOD_ID + ": Регистрация блоков");
    }
}
