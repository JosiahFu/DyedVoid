package archives.tater.dyedvoid;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class VoidBottleItem extends Item {
    private static final int MAX_USE_TIME = 32;

    public VoidBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MAX_USE_TIME;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.INTENTIONALLY_EMPTY;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if ((user instanceof PlayerEntity player)) {
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.CONSUME_ITEM.trigger(serverPlayer, stack);
            }

            if (!world.isClient) {
                player.getHungerManager().addExhaustion(12);
                player.setAir(0);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200));
            }

            player.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }

            if (!stack.isEmpty()) {
                player.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE));
            }
        }

        if (stack.isEmpty()) {
            return new ItemStack(Items.GLASS_BOTTLE);
        }

        user.emitGameEvent(GameEvent.DRINK);
        return stack;
    }
}
