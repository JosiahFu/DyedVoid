package archives.tater.dyedvoid;

import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiWorldInteractionRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.util.Identifier;

import java.util.List;

public class DyedVoidEmiPlugin implements EmiPlugin {
    @Override
    public void register(EmiRegistry registry) {
        registry.addRecipe(EmiWorldInteractionRecipe.builder()
                        .id(new Identifier(DyedVoid.MOD_ID, "end_void"))
                        .leftInput(EmiStack.of(DyedVoidItems.BLACK_VOID))
                        .rightInput(EmiIngredient.of(List.of(EmiStack.of(DyedVoidItems.DUMMY_END_PORTAL), EmiStack.of(DyedVoidItems.DUMMY_END_GATEWAY))), true)
                        .output(EmiStack.of(DyedVoidItems.END_VOID))
                .build());
    }
}
