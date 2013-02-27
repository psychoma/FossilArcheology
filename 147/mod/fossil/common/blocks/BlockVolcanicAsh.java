package mod.fossil.common.blocks;

import java.util.Random;

import mod.fossil.common.Fossil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockVolcanicAsh extends Block
{
    public BlockVolcanicAsh(int i, int j)
    {
        super(i, j, Material.grass);
    }

    public int idDropped(int i, Random random, int j)
    {
        return Fossil.volcanicAsh.blockID;
    }
}