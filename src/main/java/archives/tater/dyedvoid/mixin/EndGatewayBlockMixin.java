package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.EndVoidBlock;
import net.minecraft.block.EndGatewayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndGatewayBlock.class)
public class EndGatewayBlockMixin {
    @Inject(
            method = "createTeleportTarget",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/TeleportTarget;<init>(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;FFLnet/minecraft/world/TeleportTarget$PostDimensionTransition;)V")
    )
    private void craftEndVoid(ServerWorld world, Entity entity, BlockPos pos, CallbackInfoReturnable<TeleportTarget> cir) {
        EndVoidBlock.tryCraft(entity);
    }
}
