package net.cbejltpycbl.pozcraft.screen;

import net.minecraft.screen.ScreenHandlerType;

public class ModScreenHandler {
    public static ScreenHandlerType<DistillingBlockScreenHandler> DISTILLING_BLOCK_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        DISTILLING_BLOCK_SCREEN_HANDLER = new ScreenHandlerType<>(DistillingBlockScreenHandler::new);
    }
}
