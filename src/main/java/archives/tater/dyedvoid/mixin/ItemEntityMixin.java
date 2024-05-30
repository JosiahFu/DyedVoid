package archives.tater.dyedvoid.mixin;

import archives.tater.dyedvoid.DyedVoidItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

	@Shadow public abstract ItemStack getStack();

	public ItemEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Override
	public boolean hasNoGravity() {
		return super.hasNoGravity() || getStack().isIn(DyedVoidItems.NO_GRAVITY_TAG);
	}
}
