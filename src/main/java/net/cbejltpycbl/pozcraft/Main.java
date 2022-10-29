package net.cbejltpycbl.pozcraft;

import de.siphalor.nbtcrafting.NbtCrafting;
import net.cbejltpycbl.pozcraft.init.CaseItems;
import net.cbejltpycbl.pozcraft.init.KalItems;
import net.cbejltpycbl.pozcraft.items.kalzak.common.BackpackMod;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");
	public static final String MOD_ID = "pozcraft";

	@Override
	public void onInitialize() {
		CaseItems.registerCaseItems();
		new BackpackMod().onInitialize();
		KalItems.registerKalItems();
		new NbtCrafting().onInitialize();
	}
}
