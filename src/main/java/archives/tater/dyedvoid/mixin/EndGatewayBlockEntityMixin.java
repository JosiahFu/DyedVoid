package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.EndVoidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndGatewayBlockEntity.class)
public class EndGatewayBlockEntityMixin {
    @Inject(
            method = "tryTeleportingEntity",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;teleport(DDD)V")
    )
    private static void craftEndVoid(World world, BlockPos pos, BlockState state, Entity entity, EndGatewayBlockEntity blockEntity, CallbackInfo ci) {
        EndVoidBlock.tryCraft(entity);
    }
}
