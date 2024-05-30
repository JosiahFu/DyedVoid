package archives.tater.dyedvoid;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;


public class DyedVoidSounds {
    private static SoundEvent register(String path) {
        Identifier identifier = new Identifier(DyedVoid.MOD_ID, path);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static final SoundEvent FILL_VOID_BOTTLE = register("fill_void_bottle");
    public static final SoundEvent VOID_BLOCK_PLACE = register("place_void_block");

    public static final BlockSoundGroup VOID_BLOCK_SOUND_GROUP = new BlockSoundGroup(
            0.05f,
            0.7f,
            SoundEvents.INTENTIONALLY_EMPTY,
            SoundEvents.INTENTIONALLY_EMPTY,
            VOID_BLOCK_PLACE,
            SoundEvents.INTENTIONALLY_EMPTY,
            SoundEvents.INTENTIONALLY_EMPTY
    );

    public static void initialize() {}
}
