package archives.tater.dyedvoid;

import archives.tater.dyedvoid.block.DyedVoidBlocks;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.block.Block;
import net.minecraft.block.MultifaceGrowthBlock;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static java.util.Map.entry;

public class DyedVoidDataGenerator implements DataGeneratorEntrypoint {
	private static final List<Block> voidBlocks = List.of(
			DyedVoidBlocks.BLACK_VOID,
			DyedVoidBlocks.WHITE_VOID,
			DyedVoidBlocks.LIGHT_GRAY_VOID,
			DyedVoidBlocks.GRAY_VOID,
			DyedVoidBlocks.BROWN_VOID,
			DyedVoidBlocks.RED_VOID,
			DyedVoidBlocks.ORANGE_VOID,
			DyedVoidBlocks.YELLOW_VOID,
			DyedVoidBlocks.LIME_VOID,
			DyedVoidBlocks.GREEN_VOID,
			DyedVoidBlocks.CYAN_VOID,
			DyedVoidBlocks.LIGHT_BLUE_VOID,
			DyedVoidBlocks.BLUE_VOID,
			DyedVoidBlocks.PURPLE_VOID,
			DyedVoidBlocks.MAGENTA_VOID,
			DyedVoidBlocks.PINK_VOID
	);

	private static final List<Block> voidFaces = List.of(
			DyedVoidBlocks.BLACK_VOID_FACE,
			DyedVoidBlocks.WHITE_VOID_FACE,
			DyedVoidBlocks.LIGHT_GRAY_VOID_FACE,
			DyedVoidBlocks.GRAY_VOID_FACE,
			DyedVoidBlocks.BROWN_VOID_FACE,
			DyedVoidBlocks.RED_VOID_FACE,
			DyedVoidBlocks.ORANGE_VOID_FACE,
			DyedVoidBlocks.YELLOW_VOID_FACE,
			DyedVoidBlocks.LIME_VOID_FACE,
			DyedVoidBlocks.GREEN_VOID_FACE,
			DyedVoidBlocks.CYAN_VOID_FACE,
			DyedVoidBlocks.LIGHT_BLUE_VOID_FACE,
			DyedVoidBlocks.BLUE_VOID_FACE,
			DyedVoidBlocks.PURPLE_VOID_FACE,
			DyedVoidBlocks.MAGENTA_VOID_FACE,
			DyedVoidBlocks.PINK_VOID_FACE
	);

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(LangGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);
	}

	public static class ModelGenerator extends FabricModelProvider {

		public ModelGenerator(FabricDataOutput output) {
			super(output);
		}

		private static final Model VOID_BLOCK_MODEL = new Model(Optional.of(new Identifier(DyedVoid.MOD_ID, "block/void_block")), Optional.empty(), TextureKey.ALL);
		private static final TexturedModel.Factory VOID_BLOCK_FACTORY = TexturedModel.makeFactory(TextureMap::all, VOID_BLOCK_MODEL);

		private static final Model VOID_FACE_MODEL = new Model(Optional.of(new Identifier(DyedVoid.MOD_ID, "block/void_face")), Optional.empty(), TextureKey.ALL);
		private static final TexturedModel.Factory VOID_FACE_FACTORY = TexturedModel.makeFactory(TextureMap::all, VOID_FACE_MODEL);

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			for (Block block : voidBlocks) {
				blockStateModelGenerator.registerSingleton(block, VOID_BLOCK_FACTORY);
			}
			for (Block block : voidFaces) {
				blockStateModelGenerator.registerWallPlant(block);
				VOID_FACE_FACTORY.upload(block, blockStateModelGenerator.modelCollector);
				blockStateModelGenerator.excludeFromSimpleItemModelGeneration(block);
			}
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(DyedVoidItems.VOID_BOTTLE_ITEM, Models.GENERATED);
//			for (Block block : voidFaces) {
//				itemModelGenerator.register(block.asItem(), Models.GENERATED);
//			}
		}
	}

	public static class RecipeGenerator extends FabricRecipeProvider {
		Map<Item, Item> dyes = Map.ofEntries(
				entry(DyedVoidItems.ORANGE_VOID, Items.ORANGE_DYE),
				entry(DyedVoidItems.MAGENTA_VOID, Items.MAGENTA_DYE),
				entry(DyedVoidItems.LIGHT_BLUE_VOID, Items.LIGHT_BLUE_DYE),
				entry(DyedVoidItems.YELLOW_VOID, Items.YELLOW_DYE),
				entry(DyedVoidItems.LIME_VOID, Items.LIME_DYE),
				entry(DyedVoidItems.PINK_VOID, Items.PINK_DYE),
				entry(DyedVoidItems.GRAY_VOID, Items.GRAY_DYE),
				entry(DyedVoidItems.LIGHT_GRAY_VOID, Items.LIGHT_GRAY_DYE),
				entry(DyedVoidItems.CYAN_VOID, Items.CYAN_DYE),
				entry(DyedVoidItems.PURPLE_VOID, Items.PURPLE_DYE),
				entry(DyedVoidItems.BLUE_VOID, Items.BLUE_DYE),
				entry(DyedVoidItems.BROWN_VOID, Items.BROWN_DYE),
				entry(DyedVoidItems.GREEN_VOID, Items.GREEN_DYE),
				entry(DyedVoidItems.RED_VOID, Items.RED_DYE)
		);

		public RecipeGenerator(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generate(Consumer<RecipeJsonProvider> exporter) {
			dyes.forEach((voidItem, dyeItem) -> ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, voidItem, 8)
                    .pattern("###")
                    .pattern("#%#")
                    .pattern("###")
                    .input('#', DyedVoidItems.WHITE_VOID)
                    .input('%', dyeItem)
                    .criterion(hasItem(DyedVoidItems.WHITE_VOID), conditionsFromItem(DyedVoidItems.WHITE_VOID))
                    .group("dye_void_block")
                    .offerTo(exporter)
			);

			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, DyedVoidBlocks.BLACK_VOID, 4)
					.pattern("##")
					.pattern("##")
					.input('#', DyedVoidItems.VOID_BOTTLE_ITEM)
					.criterion(hasItem(DyedVoidItems.VOID_BOTTLE_ITEM), conditionsFromItem(DyedVoidItems.VOID_BOTTLE_ITEM))
					.offerTo(exporter);

			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, DyedVoidItems.WHITE_VOID, 8)
					.pattern("###")
					.pattern("#%#")
					.pattern("###")
					.input('#', DyedVoidItems.BLACK_VOID)
					.input('%', Items.GLOW_INK_SAC)
					.criterion(hasItem(DyedVoidItems.BLACK_VOID), conditionsFromItem(DyedVoidItems.BLACK_VOID))
					.offerTo(exporter);
		}
	}

	public static class LangGenerator extends FabricLanguageProvider {

		protected LangGenerator(FabricDataOutput dataOutput) {
			super(dataOutput);
		}

		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(DyedVoidItems.VOID_BOTTLE_ITEM, "Bottle of Void");

			translationBuilder.add(DyedVoidBlocks.BLACK_VOID, "Void Block");
			translationBuilder.add(DyedVoidBlocks.WHITE_VOID, "Luminous Void Block");
			translationBuilder.add(DyedVoidBlocks.LIGHT_GRAY_VOID, "Light Gray Void Block");
			translationBuilder.add(DyedVoidBlocks.GRAY_VOID, "Gray Void Block");
			translationBuilder.add(DyedVoidBlocks.BROWN_VOID, "Brown Void Block");
			translationBuilder.add(DyedVoidBlocks.RED_VOID, "Red Void Block");
			translationBuilder.add(DyedVoidBlocks.ORANGE_VOID, "Orange Void Block");
			translationBuilder.add(DyedVoidBlocks.YELLOW_VOID, "Yellow Void Block");
			translationBuilder.add(DyedVoidBlocks.LIME_VOID, "Lime Void Block");
			translationBuilder.add(DyedVoidBlocks.GREEN_VOID, "Green Void Block");
			translationBuilder.add(DyedVoidBlocks.CYAN_VOID, "Cyan Void Block");
			translationBuilder.add(DyedVoidBlocks.LIGHT_BLUE_VOID, "Light Blue Void Block");
			translationBuilder.add(DyedVoidBlocks.BLUE_VOID, "Blue Void Block");
			translationBuilder.add(DyedVoidBlocks.PURPLE_VOID, "Purple Void Block");
			translationBuilder.add(DyedVoidBlocks.MAGENTA_VOID, "Magenta Void Block");
			translationBuilder.add(DyedVoidBlocks.PINK_VOID, "Pink Void Block");

			translationBuilder.add(DyedVoidBlocks.BLACK_VOID_FACE, "Void Face");
			translationBuilder.add(DyedVoidBlocks.WHITE_VOID_FACE, "Luminous Void Face");
			translationBuilder.add(DyedVoidBlocks.LIGHT_GRAY_VOID_FACE, "Light Gray Void Face");
			translationBuilder.add(DyedVoidBlocks.GRAY_VOID_FACE, "Gray Void Face");
			translationBuilder.add(DyedVoidBlocks.BROWN_VOID_FACE, "Brown Void Face");
			translationBuilder.add(DyedVoidBlocks.RED_VOID_FACE, "Red Void Face");
			translationBuilder.add(DyedVoidBlocks.ORANGE_VOID_FACE, "Orange Void Face");
			translationBuilder.add(DyedVoidBlocks.YELLOW_VOID_FACE, "Yellow Void Face");
			translationBuilder.add(DyedVoidBlocks.LIME_VOID_FACE, "Lime Void Face");
			translationBuilder.add(DyedVoidBlocks.GREEN_VOID_FACE, "Green Void Face");
			translationBuilder.add(DyedVoidBlocks.CYAN_VOID_FACE, "Cyan Void Face");
			translationBuilder.add(DyedVoidBlocks.LIGHT_BLUE_VOID_FACE, "Light Blue Void Face");
			translationBuilder.add(DyedVoidBlocks.BLUE_VOID_FACE, "Blue Void Face");
			translationBuilder.add(DyedVoidBlocks.PURPLE_VOID_FACE, "Purple Void Face");
			translationBuilder.add(DyedVoidBlocks.MAGENTA_VOID_FACE, "Magenta Void Face");
			translationBuilder.add(DyedVoidBlocks.PINK_VOID_FACE, "Pink Void Face");

			translationBuilder.add("itemGroup.dyedvoid.group", "The Dyed Void");
			translationBuilder.add("subtitles.dyedvoid.fill_void_bottle", "Bottle truly empties");
		}
	}

	public static class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {

		public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(DyedVoidItems.NO_GRAVITY_TAG).add(
					DyedVoidItems.VOID_BOTTLE_ITEM,
					DyedVoidItems.BLACK_VOID,
					DyedVoidItems.WHITE_VOID,
					DyedVoidItems.LIGHT_GRAY_VOID,
					DyedVoidItems.GRAY_VOID,
					DyedVoidItems.BROWN_VOID,
					DyedVoidItems.RED_VOID,
					DyedVoidItems.ORANGE_VOID,
					DyedVoidItems.YELLOW_VOID,
					DyedVoidItems.LIME_VOID,
					DyedVoidItems.GREEN_VOID,
					DyedVoidItems.CYAN_VOID,
					DyedVoidItems.LIGHT_BLUE_VOID,
					DyedVoidItems.BLUE_VOID,
					DyedVoidItems.PURPLE_VOID,
					DyedVoidItems.MAGENTA_VOID,
					DyedVoidItems.PINK_VOID,

					DyedVoidItems.BLACK_VOID_FACE,
					DyedVoidItems.WHITE_VOID_FACE,
					DyedVoidItems.LIGHT_GRAY_VOID_FACE,
					DyedVoidItems.GRAY_VOID_FACE,
					DyedVoidItems.BROWN_VOID_FACE,
					DyedVoidItems.RED_VOID_FACE,
					DyedVoidItems.ORANGE_VOID_FACE,
					DyedVoidItems.YELLOW_VOID_FACE,
					DyedVoidItems.LIME_VOID_FACE,
					DyedVoidItems.GREEN_VOID_FACE,
					DyedVoidItems.CYAN_VOID_FACE,
					DyedVoidItems.LIGHT_BLUE_VOID_FACE,
					DyedVoidItems.BLUE_VOID_FACE,
					DyedVoidItems.PURPLE_VOID_FACE,
					DyedVoidItems.MAGENTA_VOID_FACE,
					DyedVoidItems.PINK_VOID_FACE
			);
		}
	}

	public static class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {

		public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			getOrCreateTagBuilder(DyedVoidBlocks.VOID_BLOCKS_TAG)
					.add(voidBlocks.toArray(new Block[0]))
					.add(voidFaces.toArray(new Block[0]));

			getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
			getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
			getOrCreateTagBuilder(BlockTags.AXE_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
			getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
			getOrCreateTagBuilder(BlockTags.HOE_MINEABLE).forceAddTag(DyedVoidBlocks.VOID_BLOCKS_TAG);
		}
	}

	public static class BlockLootTableGenerator extends FabricBlockLootTableProvider {
		public LootTable.Builder multifaceGrowthDrops(Block drop) {
			return LootTable.builder().pool(LootPool.builder().with(applyExplosionDecay(drop, ItemEntry.builder(drop).apply(Direction.values(), direction -> SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F), true).conditionally(BlockStatePropertyLootCondition.builder(drop).properties(StatePredicate.Builder.create().exactMatch(MultifaceGrowthBlock.getProperty(direction), true)))).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(-1.0F), true)))));
		}

		protected BlockLootTableGenerator(FabricDataOutput dataOutput) {
			super(dataOutput);
		}

		@Override
		public void generate() {
            voidBlocks.forEach(this::addDrop);
			for (Block face : voidFaces) {
				addDrop(face, multifaceGrowthDrops(face));
			}
		}
	}
}
