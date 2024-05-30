package archives.tater.dyedvoid;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static java.util.Map.entry;

public class DyedVoidDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(LangGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
	}

	public static class ModelGenerator extends FabricModelProvider {

		public ModelGenerator(FabricDataOutput output) {
			super(output);
		}

		private static final Model VOID_BLOCK_MODEL = new Model(Optional.of(new Identifier(DyedVoid.MOD_ID, "block/void_block")), Optional.empty(), TextureKey.ALL);
		private static final TexturedModel.Factory VOID_BLOCK_FACTORY = TexturedModel.makeFactory(TextureMap::all, VOID_BLOCK_MODEL);

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.WHITE_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.BLACK_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.LIGHT_GRAY_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.GRAY_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.BROWN_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.RED_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.ORANGE_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.YELLOW_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.LIME_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.GREEN_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.CYAN_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.LIGHT_BLUE_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.BLUE_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.PURPLE_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.MAGENTA_VOID, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoidBlocks.PINK_VOID, VOID_BLOCK_FACTORY);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(DyedVoidItems.VOID_BOTTLE_ITEM, Models.GENERATED);
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
			translationBuilder.add(DyedVoidItems.VOID_BOTTLE_ITEM, "Bottle of void");
			translationBuilder.add(DyedVoidItems.BLACK_VOID, "Void");
			translationBuilder.add(DyedVoidItems.WHITE_VOID, "Luminous Void");
			translationBuilder.add(DyedVoidItems.LIGHT_GRAY_VOID, "Light Gray Void");
			translationBuilder.add(DyedVoidItems.GRAY_VOID, "Gray Void");
			translationBuilder.add(DyedVoidItems.BROWN_VOID, "Brown Void");
			translationBuilder.add(DyedVoidItems.RED_VOID, "Red Void");
			translationBuilder.add(DyedVoidItems.ORANGE_VOID, "Orange Void");
			translationBuilder.add(DyedVoidItems.YELLOW_VOID, "Yellow Void");
			translationBuilder.add(DyedVoidItems.LIME_VOID, "Lime Void");
			translationBuilder.add(DyedVoidItems.GREEN_VOID, "Green Void");
			translationBuilder.add(DyedVoidItems.CYAN_VOID, "Cyan Void");
			translationBuilder.add(DyedVoidItems.LIGHT_BLUE_VOID, "Light Blue Void");
			translationBuilder.add(DyedVoidItems.BLUE_VOID, "Blue Void");
			translationBuilder.add(DyedVoidItems.PURPLE_VOID, "Purple Void");
			translationBuilder.add(DyedVoidItems.MAGENTA_VOID, "Magenta Void");
			translationBuilder.add(DyedVoidItems.PINK_VOID, "Pink Void");
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
					DyedVoidItems.PINK_VOID
			);
		}
	}
}
