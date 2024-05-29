package archives.tater.dyedvoid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DyedVoid implements ModInitializer {
	public static final String MOD_ID = "dyedvoid";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("dyedvoid");

	private static Block register(String name, Block block) {
		return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
	}

	private static Item register(String name, Item item) {
		return Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
	}

	private static Item registerBlockItem(Block block, Item.Settings settings) {
		return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, settings));
	}

	private static Item registerBlockItem(Block block) {
		return registerBlockItem(block, new FabricItemSettings());
	}

	private static Block registerVoidBlock(String colorName, boolean luminant) {
		FabricBlockSettings settings = FabricBlockSettings.create()
				.emissiveLighting((state, world, pos) -> true)
				.sounds(VOID_BLOCK_SOUND_GROUP)
				.luminance(luminant ? 15 : 0);

		return register(colorName + "_void", new Block(settings
		));
	}

	private static Block registerVoidBlock(String colorName) {
		return registerVoidBlock(colorName, true);
	}

	public static final Block WHITE_VOID_BLOCK = registerVoidBlock("white");
	public static final Block LIGHT_GRAY_VOID_BLOCK = registerVoidBlock("light_gray");
	public static final Block GRAY_VOID_BLOCK = registerVoidBlock("gray");
	public static final Block BLACK_VOID_BLOCK = registerVoidBlock("black", false);
	public static final Block BROWN_VOID_BLOCK = registerVoidBlock("brown");
	public static final Block RED_VOID_BLOCK = registerVoidBlock("red");
	public static final Block ORANGE_VOID_BLOCK = registerVoidBlock("orange");
	public static final Block YELLOW_VOID_BLOCK = registerVoidBlock("yellow");
	public static final Block LIME_VOID_BLOCK = registerVoidBlock("lime");
	public static final Block GREEN_VOID_BLOCK = registerVoidBlock("green");
	public static final Block CYAN_VOID_BLOCK = registerVoidBlock("cyan");
	public static final Block LIGHT_BLUE_VOID_BLOCK = registerVoidBlock("light_blue");
	public static final Block BLUE_VOID_BLOCK = registerVoidBlock("blue");
	public static final Block PURPLE_VOID_BLOCK = registerVoidBlock("purple");
	public static final Block MAGENTA_VOID_BLOCK = registerVoidBlock("magenta");
	public static final Block PINK_VOID_BLOCK = registerVoidBlock("pink");

	public static final Item WHITE_VOID_ITEM = registerBlockItem(WHITE_VOID_BLOCK);
	public static final Item LIGHT_GRAY_VOID_ITEM = registerBlockItem(LIGHT_GRAY_VOID_BLOCK);
	public static final Item GRAY_VOID_ITEM = registerBlockItem(GRAY_VOID_BLOCK);
	public static final Item BLACK_VOID_ITEM = registerBlockItem(BLACK_VOID_BLOCK);
	public static final Item BROWN_VOID_ITEM = registerBlockItem(BROWN_VOID_BLOCK);
	public static final Item RED_VOID_ITEM = registerBlockItem(RED_VOID_BLOCK);
	public static final Item ORANGE_VOID_ITEM = registerBlockItem(ORANGE_VOID_BLOCK);
	public static final Item YELLOW_VOID_ITEM = registerBlockItem(YELLOW_VOID_BLOCK);
	public static final Item LIME_VOID_ITEM = registerBlockItem(LIME_VOID_BLOCK);
	public static final Item GREEN_VOID_ITEM = registerBlockItem(GREEN_VOID_BLOCK);
	public static final Item CYAN_VOID_ITEM = registerBlockItem(CYAN_VOID_BLOCK);
	public static final Item LIGHT_BLUE_VOID_ITEM = registerBlockItem(LIGHT_BLUE_VOID_BLOCK);
	public static final Item BLUE_VOID_ITEM = registerBlockItem(BLUE_VOID_BLOCK);
	public static final Item PURPLE_VOID_ITEM = registerBlockItem(PURPLE_VOID_BLOCK);
	public static final Item MAGENTA_VOID_ITEM = registerBlockItem(MAGENTA_VOID_BLOCK);
	public static final Item PINK_VOID_ITEM = registerBlockItem(PINK_VOID_BLOCK);

	public static final Item VOID_BOTTLE_ITEM = register("void_bottle", new Item(new FabricItemSettings()
			.maxCount(16)
			.recipeRemainder(Items.GLASS_BOTTLE)
	));

	public static final TagKey<Item> NO_GRAVITY_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, "no_gravity"));

	public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
			.icon(() -> new ItemStack(RED_VOID_ITEM))
			.displayName(Text.translatable("itemGroup.dyedvoid.group"))
			.entries((context, entries) -> {
				entries.add(VOID_BOTTLE_ITEM);
				entries.add(LIGHT_GRAY_VOID_ITEM);
				entries.add(WHITE_VOID_ITEM);
				entries.add(GRAY_VOID_ITEM);
				entries.add(BLACK_VOID_ITEM);
				entries.add(BROWN_VOID_ITEM);
				entries.add(RED_VOID_ITEM);
				entries.add(ORANGE_VOID_ITEM);
				entries.add(YELLOW_VOID_ITEM);
				entries.add(LIME_VOID_ITEM);
				entries.add(GREEN_VOID_ITEM);
				entries.add(CYAN_VOID_ITEM);
				entries.add(LIGHT_BLUE_VOID_ITEM);
				entries.add(BLUE_VOID_ITEM);
				entries.add(PURPLE_VOID_ITEM);
				entries.add(MAGENTA_VOID_ITEM);
				entries.add(PINK_VOID_ITEM);
			})
			.build();


	public static final Identifier FILL_VOID_BOTTLE_ID = new Identifier("dyedvoid:fill_void_bottle");
	public static final SoundEvent FILL_VOID_BOTTLE_SOUND_EVENT = SoundEvent.of(FILL_VOID_BOTTLE_ID);

	public static final Identifier VOID_BLOCK_PLACE_ID = new Identifier("dyedvoid:place_void_block");
	public static final SoundEvent VOID_BLOCK_PLACE_SOUND_EVENT = SoundEvent.of(VOID_BLOCK_PLACE_ID);

	public static final BlockSoundGroup VOID_BLOCK_SOUND_GROUP = new BlockSoundGroup(
			0.1f,
			0.8f,
			SoundEvents.INTENTIONALLY_EMPTY,
			SoundEvents.INTENTIONALLY_EMPTY,
			VOID_BLOCK_PLACE_SOUND_EVENT,
			SoundEvents.INTENTIONALLY_EMPTY,
			SoundEvents.INTENTIONALLY_EMPTY
	);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "item_group"), ITEM_GROUP);
	}
}
