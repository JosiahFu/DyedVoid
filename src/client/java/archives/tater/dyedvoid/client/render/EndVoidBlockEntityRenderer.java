package archives.tater.dyedvoid.client.render;

import archives.tater.dyedvoid.EndVoidBlock.EndVoidBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;

public class EndVoidBlockEntityRenderer extends EndPortalBlockEntityRenderer<EndVoidBlockEntity> {
    public EndVoidBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    protected float getTopYOffset() {
        return 1.0F;
    }

    @Override
    protected float getBottomYOffset() {
        return 0.0F;
    }

    @Override
    public int getRenderDistance() {
        return 256;
    }
}
