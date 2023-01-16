package net.cbejltpycbl.pozcraft;

import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.cbejltpycbl.pozcraft.block.entity.ModBlockEntities;
import net.cbejltpycbl.pozcraft.block.entity.client.DistillingBlockEntityRenderer;
import net.cbejltpycbl.pozcraft.networking.ModMessages;
import net.cbejltpycbl.pozcraft.screen.BackpackScreen;
import net.cbejltpycbl.pozcraft.items.kalzak.common.init.BackpackScreenHandlers;
import net.cbejltpycbl.pozcraft.screen.DistillingBlockScreen;
import net.cbejltpycbl.pozcraft.screen.ModScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class PozcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModMessages.registerS2CPackets();

        HandledScreens.register(ModScreenHandler.DISTILLING_BLOCK_SCREEN_HANDLER, DistillingBlockScreen::new);
        HandledScreens.register(BackpackScreenHandlers.BACKPACK_SCREEN_HANDLER, BackpackScreen::new);

        BlockEntityRendererRegistry.register(ModBlockEntities.DISTILLATION_APPARATUS, DistillingBlockEntityRenderer::new);
    }
}
