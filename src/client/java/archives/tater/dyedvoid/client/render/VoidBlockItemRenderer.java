package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.DyedVoidBlocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class VoidBlockItemRenderer {
    public static void renderVoidBlockItem(ItemStack itemStack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (!(itemStack.getItem() instanceof BlockItem blockItem)) {
            // Use missing model somehow
            return;
        }
        var world = MinecraftClient.getInstance().world;
        if (world == null) {
            // Use missing model somehow
            return;
        }
        MinecraftClient.getInstance().getBlockRenderManager()
                .renderBlock(blockItem.getBlock().getDefaultState(), BlockPos.ORIGIN, world, matrices, vertexConsumers.getBuffer(RenderLayer.getSolid()), false, world.random);
    }

    public static void renderEndVoidBlockItem(ItemStack itemStack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        var world = MinecraftClient.getInstance().world;
        if (world == null) {
            // Use missing model somehow
            return;
        }
        MinecraftClient.getInstance().getBlockRenderManager()
                .renderBlock(DyedVoidBlocks.BLACK_VOID.getDefaultState(), BlockPos.ORIGIN, world, matrices, vertexConsumers.getBuffer(RenderLayer.getEndGateway()), false, world.random);
    }
}
