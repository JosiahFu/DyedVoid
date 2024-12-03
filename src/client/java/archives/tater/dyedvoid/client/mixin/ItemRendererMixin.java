package archives.tater.dyedvoid.client.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
    @ModifyArg(
            method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/item/ItemRenderer;renderBakedItemModel(Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;)V"),
            index = 2
    )
    private int modifyLight(int light, @Local(argsOnly = true) ItemStack stack) {
        for (var item : DyedVoidItems.VOID_BLOCKS) {
            if (stack.isOf(item)) return LightmapTextureManager.MAX_LIGHT_COORDINATE;
        }
        return light;
    }
}
