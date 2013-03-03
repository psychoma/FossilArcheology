package fossil.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;

public class BiomeOcean1 extends BiomeGenBase
{
    public BiomeOcean1(int par1)
    {
        super(par1);
        this.setBiomeName("Ocean 1");
        this.spawnableCreatureList.clear();
        this.minHeight = -0.1F;
        this.maxHeight = 0.6F;
    }
}