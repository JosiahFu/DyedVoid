package archives.tater.dyedvoid;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class DyedVoidBlocks {

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(DyedVoid.MOD_ID, name), block);
    }

    private static Block registerVoidBlock(String colorName, boolean luminant) {
        FabricBlockSettings settings = FabricBlockSettings.create()
                .strength(0)
                .hardness(3)
                .emissiveLighting((state, world, pos) -> true)
                .sounds(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP)
                .luminance(luminant ? 15 : 0);

        return register(colorName + "_void", new Block(settings));
    }

    private static Block registerVoidBlock(String colorName) {
        return registerVoidBlock(colorName, true);
    }

    public static final Block WHITE_VOID = registerVoidBlock("white");
    public static final Block LIGHT_GRAY_VOID = registerVoidBlock("light_gray");
    public static final Block GRAY_VOID = registerVoidBlock("gray");
    public static final Block BLACK_VOID = registerVoidBlock("black", false);
    public static final Block BROWN_VOID = registerVoidBlock("brown");
    public static final Block RED_VOID = registerVoidBlock("red");
    public static final Block ORANGE_VOID = registerVoidBlock("orange");
    public static final Block YELLOW_VOID = registerVoidBlock("yellow");
    public static final Block LIME_VOID = registerVoidBlock("lime");
    public static final Block GREEN_VOID = registerVoidBlock("green");
    public static final Block CYAN_VOID = registerVoidBlock("cyan");
    public static final Block LIGHT_BLUE_VOID = registerVoidBlock("light_blue");
    public static final Block BLUE_VOID = registerVoidBlock("blue");
    public static final Block PURPLE_VOID = registerVoidBlock("purple");
    public static final Block MAGENTA_VOID = registerVoidBlock("magenta");
    public static final Block PINK_VOID = registerVoidBlock("pink");

    public static final TagKey<Block> VOID_BLOCKS_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier(DyedVoid.MOD_ID, "void_blocks"));

    public static void initialize() {}
}
