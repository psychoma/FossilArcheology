package fossil.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenRiver;

public class BiomeRiver1 extends BiomeGenBase
{
    public BiomeRiver1(int par1)
    {
        super(par1);
        this.setBiomeName("River 1");
        this.spawnableCreatureList.clear();
        this.minHeight = -0.35F;
        this.maxHeight = 0.3F;
    }
}