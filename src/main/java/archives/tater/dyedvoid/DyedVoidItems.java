package archives.tater.dyedvoid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class DyedVoidItems {

    private static Item register(Identifier identifier, Item item) {
        return Registry.register(Registries.ITEM, identifier, item);
    }

    private static Item register(String path, Item item) {
        return register(new Identifier(DyedVoid.MOD_ID, path), item);
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

    public static final Item END_VOID = registerBlockItem(DyedVoidBlocks.END_VOID);

    public static final Item VOID_BOTTLE_ITEM = register("void_bottle", new VoidBottleItem(new FabricItemSettings()
            .maxCount(16)
            .recipeRemainder(Items.GLASS_BOTTLE)
    ));

    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(RED_VOID))
            .displayName(Text.translatable("itemGroup.dyedvoid.group"))
            .entries((context, entries) -> {
                entries.add(VOID_BOTTLE_ITEM);
                entries.add(LIGHT_GRAY_VOID);
                entries.add(WHITE_VOID);
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
                entries.add(END_VOID);
            })
            .build();

    public static final Item DUMMY_END_PORTAL = register(new Identifier("dyedvoid/dummy/end_portal"), new BlockItem(Blocks.END_PORTAL, new FabricItemSettings()));
    public static final Item DUMMY_END_GATEWAY = register(new Identifier("dyedvoid/dummy/end_gateway"), new BlockItem(Blocks.END_GATEWAY, new FabricItemSettings()));

    public static final TagKey<Item> NO_GRAVITY_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier(DyedVoid.MOD_ID, "no_gravity"));

    public static void initalize() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(DyedVoid.MOD_ID, "item_group"), DyedVoidItems.ITEM_GROUP);
    }
}
