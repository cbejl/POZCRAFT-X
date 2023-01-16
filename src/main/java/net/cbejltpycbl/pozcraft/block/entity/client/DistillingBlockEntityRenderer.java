package net.cbejltpycbl.pozcraft.block.entity.client;

import net.cbejltpycbl.pozcraft.block.custom.DistillingBlock;
import net.cbejltpycbl.pozcraft.block.entity.DistillingBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

public class DistillingBlockEntityRenderer implements BlockEntityRenderer<DistillingBlockEntity> {

    public DistillingBlockEntityRenderer (BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(DistillingBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        double pixelSize = (1 / 0.375) / 16;
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack itemStack = entity.getRenderStack();
        matrices.push();
        matrices.scale(0.375f, 0.375f, 0.375f);

        switch (entity.getCachedState().get(DistillingBlock.FACING)) {
            case NORTH -> {
                matrices.translate(pixelSize * 3.667, pixelSize * 3.667, pixelSize * 4.5);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));
            }
            case WEST ->  {
                matrices.translate(pixelSize * 4.5, pixelSize * 3.667, pixelSize * 12.334);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(270));
            }
            case SOUTH ->  {
                matrices.translate(pixelSize * 12.334, pixelSize * 3.667, pixelSize * 11.5);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0));
            }
            case EAST ->  {
                matrices.translate(pixelSize * 11.5, pixelSize * 3.667, pixelSize * 3.667);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
            }
        }
        itemRenderer.renderItem(itemStack, ModelTransformation.Mode.GUI, getLightLevel(entity.getWorld(), entity.getPos()),
                OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, 1);
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
