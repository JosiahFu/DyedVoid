package archives.tater.dyedvoid.datagen;

import archives.tater.dyedvoid.DyedVoidBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

import java.util.Arrays;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {

    public BlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        Arrays.stream(DyedVoidBlocks.VOID_BLOCKS).forEach(this::addDrop);
    }
}
