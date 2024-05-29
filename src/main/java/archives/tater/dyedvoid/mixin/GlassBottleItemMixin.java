package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoid;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlassBottleItem.class)
public class GlassBottleItemMixin extends Item {
    public GlassBottleItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(
            method = "use",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/util/TypedActionResult;pass(Ljava/lang/Object;)Lnet/minecraft/util/TypedActionResult;", ordinal = 0),
            cancellable = true)
    private void getVoidBottle(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir, @Local BlockHitResult blockHitResult) {
        if (blockHitResult.getType() == HitResult.Type.MISS && blockHitResult.getBlockPos().getY() < world.getBottomY()) {
            var stack = user.getStackInHand(hand);
            if (stack.getCount() > 1 || user.isCreative()) {
                if (!user.isCreative()) {
                    stack.decrement(1);
                }
                user.giveItemStack(new ItemStack(DyedVoid.VOID_BOTTLE_ITEM));
            } else {
                user.setStackInHand(hand, new ItemStack(DyedVoid.VOID_BOTTLE_ITEM));
            }
            world.playSound(null, user.getX(), user.getY(), user.getZ(), DyedVoid.FILL_VOID_BOTTLE_SOUND_EVENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            cir.setReturnValue(TypedActionResult.success(stack));
        }
    }
}
