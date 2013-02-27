package mod.fossil.common.gens;

import cpw.mods.fml.common.IWorldGenerator;


import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
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
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBeach;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenPlains;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.biome.BiomeGenTaiga;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldGenAcademy implements IWorldGenerator
{
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
    	BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(blockX, blockZ);
    	WorldGeneratorAcademy academy = new WorldGeneratorAcademy();
    	
    	if((biome instanceof BiomeGenDesert) || (biome instanceof BiomeGenJungle) || (biome instanceof BiomeGenTaiga) || (biome instanceof BiomeGenPlains))// then add ||BiomeGenXYZ if you want more.
   	 	{
    	for (int x = 0;x<2;x++)
        {
            int var6 = blockX + random.nextInt(512);
            int var7 = blockZ + random.nextInt(512);
            int var8 = world.getHeightValue(var6, var7);

            if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 1, var7) == Block.dirt.blockID || world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
            {
            	academy.generate(world, random, var6, var8, var7);
            }
        }
    	
    }
    }
    private void generateNether(World world, Random random, int blockX, int blockZ) 
	{
			  
	}
    
}
