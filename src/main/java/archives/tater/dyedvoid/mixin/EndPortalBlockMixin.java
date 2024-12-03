package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.EndVoidBlock;
import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndPortalBlock.class)
public class EndPortalBlockMixin {
    @Inject(
            method = "createTeleportTarget",
            at = @At(value = "TAIL")
    )
    private void craftEndVoid(ServerWorld world, Entity entity, BlockPos pos, CallbackInfoReturnable<TeleportTarget> cir) {
        EndVoidBlock.tryCraft(entity);
    }
}
