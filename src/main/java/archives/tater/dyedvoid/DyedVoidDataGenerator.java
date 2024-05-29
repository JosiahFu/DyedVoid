package archives.tater.dyedvoid;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class DyedVoidDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelGenerator::new);
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

		}
	}
}
