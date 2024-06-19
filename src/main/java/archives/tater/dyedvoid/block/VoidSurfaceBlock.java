package archives.tater.dyedvoid.block;

import net.minecraft.block.LichenGrower;
import net.minecraft.block.MultifaceGrowthBlock;

public class VoidSurfaceBlock extends MultifaceGrowthBlock {
    public VoidSurfaceBlock(Settings settings) {
        super(settings);
    }

    @Override
    public LichenGrower getGrower() {
        return null;
    }
}
