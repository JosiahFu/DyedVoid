package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import archives.tater.dyedvoid.client.render.VoidBlockItemRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.Item;

public class DyedVoidClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, EndVoidBlockEntityRenderer::new);

        DynamicItemRenderer voidBlockItemRenderer = VoidBlockItemRenderer::renderVoidBlockItem;

        for (var item : new Item[] {
                DyedVoidItems.WHITE_VOID,
                DyedVoidItems.LIGHT_GRAY_VOID,
                DyedVoidItems.GRAY_VOID,
                DyedVoidItems.BLACK_VOID,
                DyedVoidItems.BROWN_VOID,
                DyedVoidItems.RED_VOID,
                DyedVoidItems.ORANGE_VOID,
                DyedVoidItems.YELLOW_VOID,
                DyedVoidItems.LIME_VOID,
                DyedVoidItems.GREEN_VOID,
                DyedVoidItems.CYAN_VOID,
                DyedVoidItems.LIGHT_BLUE_VOID,
                DyedVoidItems.BLUE_VOID,
                DyedVoidItems.PURPLE_VOID,
                DyedVoidItems.MAGENTA_VOID,
                DyedVoidItems.PINK_VOID
        }) {
            BuiltinItemRendererRegistry.INSTANCE.register(item, voidBlockItemRenderer);
        }
    }
}
