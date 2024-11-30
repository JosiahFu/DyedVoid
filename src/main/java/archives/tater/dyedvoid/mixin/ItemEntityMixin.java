package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

	public ItemEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(
			method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;DDD)V",
			at = @At("TAIL")
	)
	private void checkNoGravityTag(World world, double x, double y, double z, ItemStack stack, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
		if (stack.isIn(DyedVoidItems.NO_GRAVITY_TAG))
			setNoGravity(true);
	}
}
