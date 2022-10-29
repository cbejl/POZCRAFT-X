package net.cbejltpycbl.pozcraft.items.kalzak.client;

import net.cbejltpycbl.pozcraft.items.kalzak.client.gui.screen.BackpackScreen;
import net.cbejltpycbl.pozcraft.items.kalzak.common.init.BackpackScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

@Environment(EnvType.CLIENT)
public final class BackpackModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HandledScreens.register(BackpackScreenHandlers.BACKPACK_SCREEN_HANDLER, BackpackScreen::new);
    }
}
