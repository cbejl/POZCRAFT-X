package net.cbejltpycbl.pozcraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cbejltpycbl.pozcraft.Pozcraft;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DistillingBlockScreen extends HandledScreen<DistillingBlockScreenHandler> {

    private static final Identifier TEXTURE =
            new Identifier(Pozcraft.MOD_ID, "textures/gui/distilling_block_gui.png");

    public DistillingBlockScreen(DistillingBlockScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
        titleY = 4;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        renderProgressArrow(matrices, x, y);
    }

    private void renderProgressArrow(MatrixStack matrices, int x, int y) {
        if(handler.isCrafting()) {
            drawTexture(matrices, x + 31, y + 13 + handler.getInWorkRepeatInt(), 0, 199, 112, 31);
            drawTexture(matrices, x + 31, y + 13 + handler.getInWorkRepeatInt(), 0, 168, handler.getScaledProgress(), 31);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
