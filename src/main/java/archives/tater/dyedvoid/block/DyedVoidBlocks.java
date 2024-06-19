package archives.tater.dyedvoid.block;

import archives.tater.dyedvoid.DyedVoid;
import archives.tater.dyedvoid.DyedVoidSounds;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class DyedVoidBlocks {

    private static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(DyedVoid.MOD_ID, name), block);
    }

    private static Block registerVoid(String name, boolean luminant, Function<AbstractBlock.Settings, Block> constructor) {
        FabricBlockSettings settings = FabricBlockSettings.create()
                .strength(0)
                .hardness(3)
                .sounds(DyedVoidSounds.VOID_BLOCK_SOUND_GROUP);

        if (luminant) {
            settings.emissiveLighting((state, world, pos) -> true);
            settings.luminance(15);
        }

        return register(name, constructor.apply(settings));
    }

    private static Block registerVoidBlock(String colorName, boolean luminant) {
        return registerVoid(colorName + "_void", luminant, Block::new);
    }

    private static Block registerVoidBlock(String colorName) {
        return registerVoidBlock(colorName, true);
    }

    private static Block registerVoidFace(String colorName, boolean luminant) {
        return registerVoid(colorName + "_void_face", luminant, VoidSurfaceBlock::new);
    }

    private static Block registerVoidFace(String colorName) {
        return registerVoidFace(colorName, true);
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

    public static final Block WHITE_VOID_FACE = registerVoidFace("white");
    public static final Block LIGHT_GRAY_VOID_FACE = registerVoidFace("light_gray");
    public static final Block GRAY_VOID_FACE = registerVoidFace("gray");
    public static final Block BLACK_VOID_FACE = registerVoidFace("black", false);
    public static final Block BROWN_VOID_FACE = registerVoidFace("brown");
    public static final Block RED_VOID_FACE = registerVoidFace("red");
    public static final Block ORANGE_VOID_FACE = registerVoidFace("orange");
    public static final Block YELLOW_VOID_FACE = registerVoidFace("yellow");
    public static final Block LIME_VOID_FACE = registerVoidFace("lime");
    public static final Block GREEN_VOID_FACE = registerVoidFace("green");
    public static final Block CYAN_VOID_FACE = registerVoidFace("cyan");
    public static final Block LIGHT_BLUE_VOID_FACE = registerVoidFace("light_blue");
    public static final Block BLUE_VOID_FACE = registerVoidFace("blue");
    public static final Block PURPLE_VOID_FACE = registerVoidFace("purple");
    public static final Block MAGENTA_VOID_FACE = registerVoidFace("magenta");
    public static final Block PINK_VOID_FACE = registerVoidFace("pink");

    public static final TagKey<Block> VOID_BLOCKS_TAG = TagKey.of(RegistryKeys.BLOCK, new Identifier(DyedVoid.MOD_ID, "void_blocks"));

    public static void initialize() {}
}
