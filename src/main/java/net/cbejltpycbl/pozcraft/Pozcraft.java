package net.cbejltpycbl.pozcraft;

import net.cbejltpycbl.pozcraft.block.ModBlocks;
import net.cbejltpycbl.pozcraft.block.entity.ModBlockEntities;
import net.cbejltpycbl.pozcraft.init.PozcraftItems;
import net.cbejltpycbl.pozcraft.items.kalzak.common.init.BackpackScreenHandlers;
import net.cbejltpycbl.pozcraft.networking.ModMessages;
import net.cbejltpycbl.pozcraft.recipe.ModRecipes;
import net.cbejltpycbl.pozcraft.screen.ModScreenHandler;
import net.cbejltpycbl.pozcraft.util.PozcraftLootTableModifiers;
import net.cbejltpycbl.pozcraft.world.dimension.ModDimensions;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Pozcraft implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(Pozcraft.MOD_ID);
	public static final String MOD_ID = "pozcraft";

	@Override
	public void onInitialize() {
		BackpackScreenHandlers.register();
		PozcraftItems.registerCaseItems();

		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModRecipes.registerRecipes();

		ModDimensions.register();

		ModScreenHandler.registerAllScreenHandlers();

		PozcraftLootTableModifiers.modifyLootTables();

	}
}
