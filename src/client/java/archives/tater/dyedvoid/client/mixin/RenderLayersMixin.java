package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderLayers.class)
public class RenderLayersMixin {
    @Inject(
            method = "getItemLayer",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void modifyVoidItemLayers(ItemStack stack, boolean direct, CallbackInfoReturnable<RenderLayer> cir) {
        if (stack.isOf(DyedVoidItems.END_VOID) || stack.isOf(DyedVoidItems.DUMMY_END_PORTAL) || stack.isOf(DyedVoidItems.DUMMY_END_GATEWAY)) {
            cir.setReturnValue(RenderLayer.getEndPortal());
            return;
        }
        for (var item : DyedVoidItems.VOID_BLOCKS) {
            if (stack.isOf(item)) {
                cir.setReturnValue(RenderLayer.getSolid());
                return;
            }
        }
    }
}
