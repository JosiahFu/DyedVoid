package archives.tater.dyedvoid;

import archives.tater.dyedvoid.client.render.EndVoidBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class DyedVoidClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(DyedVoidBlocks.END_VOID_BLOCK_ENTITY, EndVoidBlockEntityRenderer::new);
    }
}
