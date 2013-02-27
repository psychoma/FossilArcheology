package mod.fossil.common.gens;

import cpw.mods.fml.common.IWorldGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import mod.fossil.common.Fossil;
import mod.fossil.common.tags.ByteArrayTag;
import mod.fossil.common.tags.CompoundTag;
import mod.fossil.common.tags.NBTInputStream;
import mod.fossil.common.tags.ShortTag;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenWeaponShopA implements IWorldGenerator
{
    private File SchFile;
    private FileInputStream SchInp;
    private NBTInputStream SchSource;
    private ArrayList ModelTagList = new ArrayList();
    private int WidthX;
    private int Layers;
    private int WidthZ;
    private byte[] BlockArray;
    private byte[] MDArray;
    private int ShopCount = 0;
    private static final int ShopLimit = 6250;

    public WorldGenWeaponShopA()
    {
        Class var1 = this.getClass();
        URL var2 = var1.getResource("/mod/fossil/common/structures/");
        String var3 = var2.getFile();
        File var4 = new File(var3);
        this.SchFile = new File(var4, "WeaponShopA.schematic");
        Fossil.DebugMessage("Model route:" + this.SchFile.getPath());

        if (this.SchFile.exists())
        {
            try
            {
                this.SchInp = new FileInputStream(this.SchFile);
                this.SchSource = new NBTInputStream(this.SchInp);
                this.ModelTagList.add((CompoundTag)this.SchSource.readTag());
                Fossil.DebugMessage("Adding Tag...");
                this.SchSource.close();
            }
            catch (Throwable var6)
            {
                return;
            }

            Fossil.DebugMessage("WeaponShopA model loaded");
        }
    }
    
    @Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch (world.provider.dimensionId)
		  {
		   case -1: generateNether(world, random, chunkX*16, chunkZ*16);
		   case 0: generateSurface(world, random, chunkX*16, chunkZ*16);
		  }		
	}

    private void generateSurface(World world, Random random, int blockX, int blockZ)
    {
        blockX *= 16;
        blockZ *= 16;
        byte var7 = 50;

        if (!this.ModelTagList.isEmpty())
        {
            CompoundTag var8 = (CompoundTag)this.ModelTagList.get(random.nextInt(this.ModelTagList.size()));
            this.WidthX = ((ShortTag)((ShortTag)var8.getValue().get("Width"))).getValue().shortValue();
            this.Layers = ((ShortTag)((ShortTag)var8.getValue().get("Height"))).getValue().shortValue();
            this.WidthZ = ((ShortTag)((ShortTag)var8.getValue().get("Length"))).getValue().shortValue();
            this.BlockArray = ((ByteArrayTag)((ByteArrayTag)var8.getValue().get("Blocks"))).getValue();
            this.MDArray = ((ByteArrayTag)((ByteArrayTag)var8.getValue().get("Data"))).getValue();

            if (this.ShopCount != 0)
            {
                --this.ShopCount;
            }
            else
            {
                this.ShopCount = 6250;
                boolean var13 = false;
                boolean var16 = true;
                byte var17 = 12;
                byte var18 = 11;
                boolean var19 = false;

                for (int var20 = blockX; var20 <= blockX + var17; ++var20)
                {
                    for (int var21 = blockZ; var21 <= blockZ + var18; ++var21)
                    {
                        Material var22 = world.getBlockMaterial(var20, var7, var21);

                        if (!var22.isSolid())
                        {
                            return;
                        }
                    }
                }

                for (int var10 = 0; var10 < this.Layers; ++var10)
                {
                    for (int var11 = 0; var11 < var18; ++var11)
                    {
                        for (int var9 = 0; var9 < var17; ++var9)
                        {
                            int var12 = var10 * var17 * var18 + var11 * var17 + var9;
                            short var14 = (short)this.BlockArray[var12];
                            short var15 = (short)this.MDArray[var12];

                            if (var14 == Block.torchWood.blockID)
                            {
                                var14 = 0;
                            }

                            world.setBlockAndMetadata(blockX - var17 / 2 + var9, var7 + var10, blockZ - var18 / 2 + var11, var14, var15);

                            if (var14 == Block.chest.blockID)
                            {
                                this.SetupTileEntitys(world, random, blockX - var17 / 2 + var9, var7 + var10, blockZ - var18 / 2 + var11);
                            }
                        }
                    }
                }

                Fossil.DebugMessage("Placing Weapon Shop-A at " + blockX + ',' + var7 + ',' + blockZ);
            }
        }
    }

    public ItemStack GetRandomContent(Random var1)
    {
        int var2 = var1.nextInt(1000);
        return var2 <= 10 ? new ItemStack(Item.swordDiamond) : (var2 < 20 ? new ItemStack(Fossil.diamondjavelin, var1.nextInt(63) + 1) : (var2 < 35 ? new ItemStack(Item.swordGold) : (var2 < 50 ? new ItemStack(Fossil.goldjavelin, var1.nextInt(63) + 1) : (var2 < 70 ? new ItemStack(Item.swordSteel) : (var2 < 90 ? new ItemStack(Fossil.ironjavelin, var1.nextInt(63) + 1) : (var2 < 105 ? new ItemStack(Item.swordStone) : (var2 < 120 ? new ItemStack(Fossil.stonejavelin, var1.nextInt(63) + 1) : (var2 < 130 ? new ItemStack(Item.swordWood) : (var2 < 140 ? new ItemStack(Fossil.woodjavelin, var1.nextInt(63) + 1) : (var2 < 240 ? new ItemStack(Item.bow) : (var2 < 750 ? new ItemStack(Item.arrow, var1.nextInt(63) + 1) : null)))))))))));
    }

    public void SetupTileEntitys(World var1, Random var2, int var3, int var4, int var5)
    {
        TileEntity var6 = var1.getBlockTileEntity(var3, var4, var5);

        if (var6 != null)
        {
            if (!(var6 instanceof TileEntityFurnace))
            {
                if (var6 instanceof TileEntityChest)
                {
                    TileEntityChest var7 = (TileEntityChest)var6;
                    ItemStack var8 = null;
                    int var9 = var2.nextInt(27);

                    for (int var10 = 1; var10 <= var9; ++var10)
                    {
                        var8 = this.GetRandomContent(var2);

                        if (var8 != null)
                        {
                            var7.setInventorySlotContents(var2.nextInt(var7.getSizeInventory()), var8);
                        }
                    }
                }
            }
        }
    }
    
    private void generateNether(World world, Random random, int blockX, int blockZ) 
	{
			  
	}
    
}
