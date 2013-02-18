package mod.fossil.common.gens;

import cpw.mods.fml.common.IWorldGenerator;


import java.io.File;
import java.io.FileInputStream;
import java.util.Random;

import mod.fossil.common.Fossil;
import mod.fossil.common.RelicHoleList;
import mod.fossil.common.fossilEnums.EnumDinoType;
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
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenAcademy implements IWorldGenerator
{
    private File SchFile = new File(this.getClass().getResource("/FossilStructers/").getFile(), "academy1.schematic");
    //private FileInputStream SchInp;
    private NBTInputStream SchSource;
    private CompoundTag Tg;
    private int WidthX;
    private int Layers;
    private int WidthZ;
    private byte[] BlockArray;
    private byte[] MDArray;
    private int GenCount = 0;
    private RelicHoleList Damages;
    private static final int GenLimit = 500;

    public WorldGenAcademy()
    {
        Fossil.DebugMessage("Model route:" + this.SchFile.getPath());

        if (this.SchFile.exists())
        {
            try
            {
                //this.SchInp = new FileInputStream(this.SchFile);
                //this.SchSource = new NBTInputStream(this.SchInp);
                this.Tg = (CompoundTag)this.SchSource.readTag();
                this.SchSource.close();
            }
            catch (Throwable var2)
            {
                return;
            }

            this.WidthX = ((ShortTag)((ShortTag)this.Tg.getValue().get("Width"))).getValue().shortValue();
            this.Layers = ((ShortTag)((ShortTag)this.Tg.getValue().get("Height"))).getValue().shortValue();
            this.WidthZ = ((ShortTag)((ShortTag)this.Tg.getValue().get("Length"))).getValue().shortValue();
            this.BlockArray = ((ByteArrayTag)((ByteArrayTag)this.Tg.getValue().get("Blocks"))).getValue();
            this.MDArray = ((ByteArrayTag)((ByteArrayTag)this.Tg.getValue().get("Data"))).getValue();
            Fossil.DebugMessage("Academy model loaded");
        }
    }

    public void generate(Random var1, int var2, int var3, World var4, IChunkProvider var5, IChunkProvider var6)
    {
        var2 *= 16;
        var3 *= 16;
        int var7 = 50 + var1.nextInt(20);

        if (this.SchFile.exists())
        {
            if (this.GenCount != 0)
            {
                --this.GenCount;
            }
            else
            {
                this.GenCount = 500;
                int var12 = 0;
                int var13 = this.WidthX * this.WidthZ / 2;
                this.Damages = new RelicHoleList(var1, this.WidthX, this.Layers, this.WidthZ, this.BlockArray, 15, 3);
                int var8;
                int var9;
                int var10;

                for (var9 = 2; var9 < this.Layers; ++var9)
                {
                    if (var9 == 2)
                    {
                        var8 = 0;

                        while (var8 < this.WidthX)
                        {
                            var10 = 0;

                            while (true)
                            {
                                if (var10 < this.WidthZ)
                                {
                                    if (var4.isBlockNormalCube(var2 - this.WidthX / 2 + var8, var7 + var9, var3 - this.WidthZ / 2 + var10))
                                    {
                                        ++var12;
                                    }

                                    if (var12 < var13)
                                    {
                                        ++var10;
                                        continue;
                                    }
                                }

                                ++var8;
                                break;
                            }
                        }

                        if (var12 < var13)
                        {
                            return;
                        }
                    }
                    else if (!var4.isAirBlock(var2 - this.WidthX / 2, var7 + var9, var3 - this.WidthZ / 2))
                    {
                        return;
                    }
                }

                for (var9 = 0; var9 < this.Layers; ++var9)
                {
                    for (var10 = 0; var10 < this.WidthZ; ++var10)
                    {
                        for (var8 = 0; var8 < this.WidthX; ++var8)
                        {
                            int var11 = var9 * this.WidthX * this.WidthZ + var10 * this.WidthX + var8;
                            short var14 = (short)this.BlockArray[var11];
                            short var15 = (short)this.MDArray[var11];

                            if (var14 == Block.torchWood.blockID)
                            {
                                var14 = 0;
                            }

                            if (var14 == Block.glowStone.blockID && var1.nextInt(100) < 10)
                            {
                                var14 = 0;
                            }

                            if (var14 != Block.brick.blockID && var14 != Block.blockClay.blockID)
                            {
                                if (this.Damages.isHole(var8, var9, var10))
                                {
                                    var14 = 0;
                                }
                            }
                            else
                            {
                                if (var14 == Block.blockClay.blockID)
                                {
                                    var15 = 2;
                                }

                                var14 = (short)Block.stoneBrick.blockID;
                            }

                            if (var14 != 0)
                            {
                                if (var14 == Block.blockDiamond.blockID)
                                {
                                    var14 = (short)Fossil.blockcultivateIdle.blockID;
                                }

                                var4.setBlockAndMetadata(var2 - this.WidthX / 2 + var8, var7 + var9, var3 - this.WidthZ / 2 + var10, var14, var15);

                                if (Block.blocksList[var14] != null && Block.blocksList[var14].hasTileEntity(var15))
                                {
                                    this.SetupTileEntitys(var4, var1, var2 - this.WidthX / 2 + var8, var7 + var9, var3 - this.WidthZ / 2 + var10, var9);
                                }
                            }
                            else
                            {
                                Material var16 = var4.getBlockMaterial(var2 - this.WidthX / 2 + var8, var7 + var9, var3 - this.WidthZ / 2 + var10);

                                if (var16 != Material.water && var16 != Material.sand)
                                {
                                    var4.setBlockAndMetadataWithNotify(var2 - this.WidthX / 2 + var8, var7 + var9, var3 - this.WidthZ / 2 + var10, 0, 0);
                                }
                            }
                        }
                    }
                }

                Fossil.DebugMessage("Placing Academy of " + " at " + var2 + ',' + var7 + ',' + var3);
            }
        }
    }

    public void SetupTileEntitys(World var1, Random var2, int var3, int var4, int var5, int var6)
    {
        TileEntity var7 = var1.getBlockTileEntity(var3, var4, var5);
        boolean var8 = false;

        if (var7 != null)
        {
            if (var7 instanceof TileEntityChest)
            {
                TileEntityChest var9 = (TileEntityChest)var7;
                ItemStack var10 = null;
                var8 = var6 > this.Layers / 2;
                int var11 = var8 ? 1 : var2.nextInt(10);

                for (int var12 = 1; var12 <= var11; ++var12)
                {
                    var10 = this.GetRandomContent(var2, var8);

                    if (var10 != null)
                    {
                        var9.setInventorySlotContents(var2.nextInt(var9.getSizeInventory()), var10);
                    }
                }
            }
        }
    }

    public ItemStack GetRandomContent(Random var1, boolean var2)
    {
        int var3 = var1.nextInt(1000);
        return var2 ? new ItemStack(Item.itemsList[Item.record13.itemID + var1.nextInt(11)], 1) : (var3 < 10 ? new ItemStack(Fossil.ancientJavelin, var1.nextInt(4) + 1) : (var3 < 200 ? new ItemStack(Fossil.biofossil) : (var3 < 300 ? new ItemStack(Fossil.dna, var1.nextInt(2) + 1, var1.nextInt(EnumDinoType.values().length)) : (var3 < 400 ? new ItemStack(Item.potion, 1, 1 + var1.nextInt(19)) : null))));
    }
}
