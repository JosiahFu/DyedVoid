package archives.tater.dyedvoid;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static java.util.Map.entry;

public class DyedVoidDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(LangGenerator::new);
	}

	public static class ModelGenerator extends FabricModelProvider {

		public ModelGenerator(FabricDataOutput output) {
			super(output);
		}

		private static final Model VOID_BLOCK_MODEL = new Model(Optional.of(new Identifier(DyedVoid.MOD_ID, "block/void_block")), Optional.empty(), TextureKey.ALL);
		private static final TexturedModel.Factory VOID_BLOCK_FACTORY = TexturedModel.makeFactory(TextureMap::all, VOID_BLOCK_MODEL);

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
			blockStateModelGenerator.registerSingleton(DyedVoid.WHITE_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.BLACK_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.LIGHT_GRAY_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.GRAY_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.BROWN_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.RED_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.ORANGE_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.YELLOW_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.LIME_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.GREEN_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.CYAN_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.LIGHT_BLUE_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.BLUE_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.PURPLE_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.MAGENTA_VOID_BLOCK, VOID_BLOCK_FACTORY);
			blockStateModelGenerator.registerSingleton(DyedVoid.PINK_VOID_BLOCK, VOID_BLOCK_FACTORY);
		}

		@Override
		public void generateItemModels(ItemModelGenerator itemModelGenerator) {
			itemModelGenerator.register(DyedVoid.VOID_BOTTLE_ITEM, Models.GENERATED);
		}
	}

	public static class RecipeGenerator extends FabricRecipeProvider {
		Map<Item, Item> dyes = Map.ofEntries(
				entry(DyedVoid.ORANGE_VOID_ITEM, Items.ORANGE_DYE),
				entry(DyedVoid.MAGENTA_VOID_ITEM, Items.MAGENTA_DYE),
				entry(DyedVoid.LIGHT_BLUE_VOID_ITEM, Items.LIGHT_BLUE_DYE),
				entry(DyedVoid.YELLOW_VOID_ITEM, Items.YELLOW_DYE),
				entry(DyedVoid.LIME_VOID_ITEM, Items.LIME_DYE),
				entry(DyedVoid.PINK_VOID_ITEM, Items.PINK_DYE),
				entry(DyedVoid.GRAY_VOID_ITEM, Items.GRAY_DYE),
				entry(DyedVoid.LIGHT_GRAY_VOID_ITEM, Items.LIGHT_GRAY_DYE),
				entry(DyedVoid.CYAN_VOID_ITEM, Items.CYAN_DYE),
				entry(DyedVoid.PURPLE_VOID_ITEM, Items.PURPLE_DYE),
				entry(DyedVoid.BLUE_VOID_ITEM, Items.BLUE_DYE),
				entry(DyedVoid.BROWN_VOID_ITEM, Items.BROWN_DYE),
				entry(DyedVoid.GREEN_VOID_ITEM, Items.GREEN_DYE),
				entry(DyedVoid.RED_VOID_ITEM, Items.RED_DYE)
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
                    .input('#', DyedVoid.WHITE_VOID_ITEM)
                    .input('%', dyeItem)
                    .criterion(hasItem(DyedVoid.WHITE_VOID_ITEM), conditionsFromItem(DyedVoid.WHITE_VOID_ITEM))
                    .group("dye_void_block")
                    .offerTo(exporter)
			);

			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, DyedVoid.BLACK_VOID_BLOCK, 4)
					.pattern("##")
					.pattern("##")
					.input('#', DyedVoid.VOID_BOTTLE_ITEM)
					.criterion(hasItem(DyedVoid.VOID_BOTTLE_ITEM), conditionsFromItem(DyedVoid.VOID_BOTTLE_ITEM))
					.offerTo(exporter);

			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, DyedVoid.WHITE_VOID_ITEM, 8)
					.pattern("###")
					.pattern("#%#")
					.pattern("###")
					.input('#', DyedVoid.BLACK_VOID_ITEM)
					.input('%', Items.GLOW_INK_SAC)
					.criterion(hasItem(DyedVoid.BLACK_VOID_ITEM), conditionsFromItem(DyedVoid.BLACK_VOID_ITEM))
					.offerTo(exporter);
		}
	}

	public static class LangGenerator extends FabricLanguageProvider {

		protected LangGenerator(FabricDataOutput dataOutput) {
			super(dataOutput);
		}

		@Override
		public void generateTranslations(TranslationBuilder translationBuilder) {
			translationBuilder.add(DyedVoid.VOID_BOTTLE_ITEM, "Bottle of void");
			translationBuilder.add(DyedVoid.BLACK_VOID_ITEM, "Void");
			translationBuilder.add(DyedVoid.WHITE_VOID_ITEM, "Luminous Void");
			translationBuilder.add(DyedVoid.LIGHT_GRAY_VOID_ITEM, "Light Gray Void");
			translationBuilder.add(DyedVoid.GRAY_VOID_ITEM, "Gray Void");
			translationBuilder.add(DyedVoid.BROWN_VOID_ITEM, "Brown Void");
			translationBuilder.add(DyedVoid.RED_VOID_ITEM, "Red Void");
			translationBuilder.add(DyedVoid.ORANGE_VOID_ITEM, "Orange Void");
			translationBuilder.add(DyedVoid.YELLOW_VOID_ITEM, "Yellow Void");
			translationBuilder.add(DyedVoid.LIME_VOID_ITEM, "Lime Void");
			translationBuilder.add(DyedVoid.GREEN_VOID_ITEM, "Green Void");
			translationBuilder.add(DyedVoid.CYAN_VOID_ITEM, "Cyan Void");
			translationBuilder.add(DyedVoid.LIGHT_BLUE_VOID_ITEM, "Light Blue Void");
			translationBuilder.add(DyedVoid.BLUE_VOID_ITEM, "Blue Void");
			translationBuilder.add(DyedVoid.PURPLE_VOID_ITEM, "Purple Void");
			translationBuilder.add(DyedVoid.MAGENTA_VOID_ITEM, "Magenta Void");
			translationBuilder.add(DyedVoid.PINK_VOID_ITEM, "Pink Void");
			translationBuilder.add("itemGroup.dyedvoid.group", "The Dyed Void");
			translationBuilder.add("subtitles.dyedvoid.fill_void_bottle", "Bottle truly empties");
		}
	}
}
