package archives.tater.dyedvoid;

import archives.tater.dyedvoid.block.DyedVoidBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DyedVoidItems {

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DyedVoid.MOD_ID, name), item);
    }

    private static Item registerBlockItem(Block block, Item.Settings settings) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, settings));
    }

    private static Item registerBlockItem(Block block) {
        return registerBlockItem(block, new FabricItemSettings());
    }

    public static final Item WHITE_VOID = registerBlockItem(DyedVoidBlocks.WHITE_VOID);
    public static final Item LIGHT_GRAY_VOID = registerBlockItem(DyedVoidBlocks.LIGHT_GRAY_VOID);
    public static final Item GRAY_VOID = registerBlockItem(DyedVoidBlocks.GRAY_VOID);
    public static final Item BLACK_VOID = registerBlockItem(DyedVoidBlocks.BLACK_VOID);
    public static final Item BROWN_VOID = registerBlockItem(DyedVoidBlocks.BROWN_VOID);
    public static final Item RED_VOID = registerBlockItem(DyedVoidBlocks.RED_VOID);
    public static final Item ORANGE_VOID = registerBlockItem(DyedVoidBlocks.ORANGE_VOID);
    public static final Item YELLOW_VOID = registerBlockItem(DyedVoidBlocks.YELLOW_VOID);
    public static final Item LIME_VOID = registerBlockItem(DyedVoidBlocks.LIME_VOID);
    public static final Item GREEN_VOID = registerBlockItem(DyedVoidBlocks.GREEN_VOID);
    public static final Item CYAN_VOID = registerBlockItem(DyedVoidBlocks.CYAN_VOID);
    public static final Item LIGHT_BLUE_VOID = registerBlockItem(DyedVoidBlocks.LIGHT_BLUE_VOID);
    public static final Item BLUE_VOID = registerBlockItem(DyedVoidBlocks.BLUE_VOID);
    public static final Item PURPLE_VOID = registerBlockItem(DyedVoidBlocks.PURPLE_VOID);
    public static final Item MAGENTA_VOID = registerBlockItem(DyedVoidBlocks.MAGENTA_VOID);
    public static final Item PINK_VOID = registerBlockItem(DyedVoidBlocks.PINK_VOID);

    public static final Item WHITE_VOID_FACE = registerBlockItem(DyedVoidBlocks.WHITE_VOID_FACE);
    public static final Item LIGHT_GRAY_VOID_FACE = registerBlockItem(DyedVoidBlocks.LIGHT_GRAY_VOID_FACE);
    public static final Item GRAY_VOID_FACE = registerBlockItem(DyedVoidBlocks.GRAY_VOID_FACE);
    public static final Item BLACK_VOID_FACE = registerBlockItem(DyedVoidBlocks.BLACK_VOID_FACE);
    public static final Item BROWN_VOID_FACE = registerBlockItem(DyedVoidBlocks.BROWN_VOID_FACE);
    public static final Item RED_VOID_FACE = registerBlockItem(DyedVoidBlocks.RED_VOID_FACE);
    public static final Item ORANGE_VOID_FACE = registerBlockItem(DyedVoidBlocks.ORANGE_VOID_FACE);
    public static final Item YELLOW_VOID_FACE = registerBlockItem(DyedVoidBlocks.YELLOW_VOID_FACE);
    public static final Item LIME_VOID_FACE = registerBlockItem(DyedVoidBlocks.LIME_VOID_FACE);
    public static final Item GREEN_VOID_FACE = registerBlockItem(DyedVoidBlocks.GREEN_VOID_FACE);
    public static final Item CYAN_VOID_FACE = registerBlockItem(DyedVoidBlocks.CYAN_VOID_FACE);
    public static final Item LIGHT_BLUE_VOID_FACE = registerBlockItem(DyedVoidBlocks.LIGHT_BLUE_VOID_FACE);
    public static final Item BLUE_VOID_FACE = registerBlockItem(DyedVoidBlocks.BLUE_VOID_FACE);
    public static final Item PURPLE_VOID_FACE = registerBlockItem(DyedVoidBlocks.PURPLE_VOID_FACE);
    public static final Item MAGENTA_VOID_FACE = registerBlockItem(DyedVoidBlocks.MAGENTA_VOID_FACE);
    public static final Item PINK_VOID_FACE = registerBlockItem(DyedVoidBlocks.PINK_VOID_FACE);

    public static final Item VOID_BOTTLE_ITEM = register("void_bottle", new VoidBottleItem(new FabricItemSettings()
            .maxCount(16)
            .recipeRemainder(Items.GLASS_BOTTLE)
    ));

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RED_VOID))
            .displayName(Text.translatable("itemGroup.dyedvoid.group"))
            .entries((context, entries) -> {
                entries.add(VOID_BOTTLE_ITEM);

                entries.add(WHITE_VOID);
                entries.add(LIGHT_GRAY_VOID);
                entries.add(GRAY_VOID);
                entries.add(BLACK_VOID);
                entries.add(BROWN_VOID);
                entries.add(RED_VOID);
                entries.add(ORANGE_VOID);
                entries.add(YELLOW_VOID);
                entries.add(LIME_VOID);
                entries.add(GREEN_VOID);
                entries.add(CYAN_VOID);
                entries.add(LIGHT_BLUE_VOID);
                entries.add(BLUE_VOID);
                entries.add(PURPLE_VOID);
                entries.add(MAGENTA_VOID);
                entries.add(PINK_VOID);

                entries.add(WHITE_VOID_FACE);
                entries.add(LIGHT_GRAY_VOID_FACE);
                entries.add(GRAY_VOID_FACE);
                entries.add(BLACK_VOID_FACE);
                entries.add(BROWN_VOID_FACE);
                entries.add(RED_VOID_FACE);
                entries.add(ORANGE_VOID_FACE);
                entries.add(YELLOW_VOID_FACE);
                entries.add(LIME_VOID_FACE);
                entries.add(GREEN_VOID_FACE);
                entries.add(CYAN_VOID_FACE);
                entries.add(LIGHT_BLUE_VOID_FACE);
                entries.add(BLUE_VOID_FACE);
                entries.add(PURPLE_VOID_FACE);
                entries.add(MAGENTA_VOID_FACE);
                entries.add(PINK_VOID_FACE);
            })
            .build();

    public static final TagKey<Item> NO_GRAVITY_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier(DyedVoid.MOD_ID, "no_gravity"));

    public static void initalize() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(DyedVoid.MOD_ID, "item_group"), DyedVoidItems.ITEM_GROUP);
    }
}
