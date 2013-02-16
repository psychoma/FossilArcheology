package mod.fossil.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.world.World;

public class BlockFern extends BlockFlower
{
    boolean lv2;

    protected BlockFern(int var1, int var2)
    {
        super(var1, var2);
        this.lv2 = false;
        this.blockIndexInTexture = Fossil.fernPics[0];
        this.setTickRandomly(true);
        float var3 = 0.5F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.25F, 0.5F + var3);
        this.disableStats();
        this.setRequiresSelfNotify();
    }

    public BlockFern(int var1, int var2, boolean var3)
    {
        this(var1, var2);
        this.lv2 = var3;
    }

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/Fos_terrian.png";
    }

    /**
     * Gets passed in the blockID of the block below and supposed to return true if its allowed to grow on the type of
     * blockID passed in. Args: blockID
     */
    protected boolean canThisPlantGrowOnThisBlockID(int var1)
    {
        return var1 == Block.grass.blockID;
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World var1, int var2, int var3, int var4, Random var5)
    {
        super.updateTick(var1, var2, var3, var4, var5);
        int var6 = var1.getBlockMetadata(var2, var3, var4);

        if (CheckUnderTree(var1, var2, var3, var4) && (this.CheckSubType(var6) == 0 && var6 < 5 || this.CheckSubType(var6) == 1 && var6 % 7 < 3))
        {
            if (var5.nextInt(10) > 1)
            {
                ++var6;

                if (!this.lv2 && var6 % 7 > 3)
                {
                    if (var1.isAirBlock(var2, var3 + 1, var4))
                    {
                        var1.setBlockWithNotify(var2, var3 + 1, var4, Fossil.fernUpper.blockID);
                        var1.setBlockMetadataWithNotify(var2, var3 + 1, var4, 5 + 7 * this.CheckSubType(var6));
                    }
                    else if (var1.getBlockId(var2, var3 + 1, var4) != Fossil.fernUpper.blockID && var1.getBlockId(var2, var3 - 1, var4) != Fossil.ferns.blockID)
                    {
                        --var6;
                    }
                }

                if (var6 == 1 && (new Random()).nextInt(10) < 5)
                {
                    var6 += 7;
                }

                var1.setBlockMetadataWithNotify(var2, var3, var4, var6);
            }
            else if (!this.lv2 && var5.nextInt(100) < 5)
            {
                this.breakBlock(var1, var2, var3, var4, this.blockID, var6);
            }
        }

        var1.getBlockId(var2, var3, var4);

        if (var6 % 7 >= 3)
        {
            for (int var8 = -1; var8 <= 1; ++var8)
            {
                for (int var9 = -1; var9 <= 1; ++var9)
                {
                    for (int var10 = -1; var10 <= 1; ++var10)
                    {
                        if ((var8 != 0 || var10 != 0 || var9 != 0) && var1.getBlockId(var2 + var8, var9 + var3, var4 + var10) != this.blockID && var1.getBlockId(var2 + var8, var9 + var3 - 1, var4 + var10) == Block.grass.blockID && (var1.isAirBlock(var2 + var8, var9 + var3, var4 + var10) || var1.getBlockId(var2 + var8, var9 + var3, var4 + var10) == Block.tallGrass.blockID) && CheckUnderTree(var1, var2 + var8, var9 + var3, var4 + var10) && (new Random()).nextInt(10) <= 5)
                        {
                            var1.setBlockWithNotify(var2 + var8, var9 + var3, var4 + var10, this.blockID);
                            var1.setBlockMetadataWithNotify(var2 + var8, var9 + var3, var4 + var10, 0 + 7 * (new Random()).nextInt(1));
                        }
                    }
                }
            }
        }
    }

    public void fertilize(World var1, int var2, int var3, int var4)
    {
        var1.setBlockMetadataWithNotify(var2, var3, var4, 5 + 7 * (new Random()).nextInt(1));
    }

    private float getGrowthRate(World var1, int var2, int var3, int var4)
    {
        float var5 = 1.0F;
        int var6 = var1.getBlockId(var2, var3, var4 - 1);
        int var7 = var1.getBlockId(var2, var3, var4 + 1);
        int var8 = var1.getBlockId(var2 - 1, var3, var4);
        int var9 = var1.getBlockId(var2 + 1, var3, var4);
        int var10 = var1.getBlockId(var2 - 1, var3, var4 - 1);
        int var11 = var1.getBlockId(var2 + 1, var3, var4 - 1);
        int var12 = var1.getBlockId(var2 + 1, var3, var4 + 1);
        int var13 = var1.getBlockId(var2 - 1, var3, var4 + 1);
        boolean var14 = var8 == this.blockID || var9 == this.blockID;
        boolean var15 = var6 == this.blockID || var7 == this.blockID;
        boolean var16 = var10 == this.blockID || var11 == this.blockID || var12 == this.blockID || var13 == this.blockID;

        for (int var17 = var2 - 1; var17 <= var2 + 1; ++var17)
        {
            for (int var18 = var4 - 1; var18 <= var4 + 1; ++var18)
            {
                int var19 = var1.getBlockId(var17, var3 - 1, var18);
                float var20 = 0.0F;

                if (var19 == Block.grass.blockID)
                {
                    var20 = 1.0F;

                    if (var1.getBlockMetadata(var17, var3 - 1, var18) > 0)
                    {
                        var20 = 3.0F;
                    }
                }

                if (var17 != var2 || var18 != var4)
                {
                    var20 /= 4.0F;
                }

                var5 += var20;
            }
        }

        if (var16 || var14 && var15)
        {
            var5 /= 2.0F;
        }

        return var5;
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public int getBlockTextureFromSideAndMetadata(int var1, int var2)
    {
        if (var2 < 0)
        {
            var2 = 0;
        }

        return var2 % 7 == 0 ? Fossil.fernPics[0] : (!this.lv2 ? Fossil.fernPics[var2] : Fossil.fernPics[var2 + 2]);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return 6;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    public void dropBlockAsItemWithChance(World var1, int var2, int var3, int var4, int var5, float var6, int var7)
    {
        super.dropBlockAsItemWithChance(var1, var2, var3, var4, var5, var6, var7);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        return 0;
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random var1)
    {
        return 1;
    }

    public static boolean CheckUnderTree(World var0, int var1, int var2, int var3)
    {
        for (int var4 = var2; var4 <= 128; ++var4)
        {
            if (var0.getBlockId(var1, var4, var3) == Block.leaves.blockID)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World var1, int var2, int var3, int var4)
    {
        boolean var5 = true;

        if (this.lv2)
        {
            return var1.getBlockId(var2, var3 - 1, var4) == Fossil.ferns.blockID;
        }
        else
        {
            int var6 = var1.getBlockMetadata(var2, var3, var4);
            var5 = var1.getBlockId(var2, var3 - 1, var4) == Block.grass.blockID && CheckUnderTree(var1, var2, var3, var4);

            if (var6 % 7 > 3)
            {
                var5 &= var1.getBlockId(var2, var3 + 1, var4) == Fossil.fernUpper.blockID;
            }

            return var5;
        }
    }

    public int CheckSubType(int var1)
    {
        return var1 > 7 ? 1 : 0;
    }

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World var1, int var2, int var3, int var4)
    {
        return Fossil.fernSeed.itemID;
    }
}
