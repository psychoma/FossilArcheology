package mod.fossil.common;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;

import mod.fossil.common.Fossil;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class FossilGenerator implements IWorldGenerator
{
    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        for (int var7 = 0; var7 < 20; ++var7)
        {
            int var8 = var1.nextInt(100);

            if (var8 > 50)
            {
                (new WorldGenMinable(Fossil.blockFossil.blockID, 10)).generate(var4, var1, var2 * 16 + var1.nextInt(16), var1.nextInt(60), var3 * 16 + var1.nextInt(16));
            }
            else
            {
                (new WorldGenMinable(Fossil.blockPermafrost.blockID, 5)).generate(var4, var1, var2 * 16 + var1.nextInt(16), var1.nextInt(40), var3 * 16 + var1.nextInt(16));
            }
        }
    }
}
