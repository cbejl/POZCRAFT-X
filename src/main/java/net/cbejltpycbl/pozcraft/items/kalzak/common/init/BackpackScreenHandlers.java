package net.cbejltpycbl.pozcraft.items.kalzak.common.init;

import net.cbejltpycbl.pozcraft.items.kalzak.common.inventory.BackpackInventory;
import net.cbejltpycbl.pozcraft.items.kalzak.common.inventory.BackpackScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public final class BackpackScreenHandlers {

    public final static ScreenHandlerType<BackpackScreenHandler> BACKPACK_SCREEN_HANDLER = new ExtendedScreenHandlerType<>((i, pinv, buf) -> {
        return new BackpackScreenHandler(pinv, i, new BackpackInventory(buf.readInt(), buf.readInt(), null));
    });

    public static final void register() {
        Registry.register(Registry.SCREEN_HANDLER, BackpackScreenHandler.IDENTIFIER, BACKPACK_SCREEN_HANDLER);
    }
}
