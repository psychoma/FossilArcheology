package fossil.biomes;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenRiver;

public class BiomeRiver2 extends BiomeGenBase
{
    public BiomeRiver2(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.minHeight = -0.28F;
        this.maxHeight = 0.29F;
    }
}