package fossil.gens;

import java.util.Random;

import fossil.biomes.BiomeJungle1;
import fossil.biomes.BiomeOcean1;
import fossil.biomes.BiomeOcean2;
import fossil.biomes.BiomeRiver1;
import fossil.biomes.BiomeRiver2;
import fossil.biomes.BiomeSwamp1;
import fossil.biomes.BiomeSwamp2;
import fossil.gens.WorldGeneratorBigShip;
import fossil.gens.WorldGeneratorCheheWreck;
import fossil.gens.WorldGeneratorGalleonWreck;
import fossil.gens.WorldGeneratorShipWreck180;
import fossil.gens.WorldGeneratorShipWreck270;
import fossil.gens.WorldGeneratorShipWreck90;
import fossil.gens.WorldGeneratorVikingWreck;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenOcean;
import net.minecraft.world.biome.BiomeGenSwamp;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenShips implements IWorldGenerator
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
    	WorldGeneratorBigShip bigship = new WorldGeneratorBigShip();
    	if(biome instanceof BiomeGenOcean)
   	 	{
    		
    		/*if(random.nextInt(20) == 0) //adjust the number in nextInt(). Higher values == rarer.
			{
				int Xcoord1 = blockX + random.nextInt(16);
				int Ycoord1 = random.nextInt(100);
				int Zcoord1 = blockZ + random.nextInt(16);

				if (random.nextInt(100) == 0 && (world.getBlockId(Xcoord1, Xcoord1 - 4, Zcoord1) == Block.waterStill.blockID)) //|| world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
					bigship.generate(world, random, Xcoord1, Ycoord1, Zcoord1);
    			}
   	 		}*/
    		for (int x = 0;x<4;x++)
    		{
    			int var6 = blockX + random.nextInt(512);
    			int var7 = blockZ + random.nextInt(512);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 4, var7) == Block.waterStill.blockID)) //|| world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				bigship.generate(world, random, var6, var8, var7);
    			}
    		}
    		
   	 	}
    	
    	WorldGeneratorCheheWreck chehewreck = new WorldGeneratorCheheWreck();
    	if(biome instanceof BiomeSwamp1)
   	 	{
    		/*if(random.nextInt(20) == 0) //adjust the number in nextInt(). Higher values == rarer.
			{
				int Xcoord1 = blockX + random.nextInt(16);
				int Ycoord1 = random.nextInt(100);
				int Zcoord1 = blockZ + random.nextInt(16);

				if (random.nextInt(100) == 0 && (world.getBlockId(Xcoord1, Xcoord1 - 4, Zcoord1) == Block.waterStill.blockID)) //|| world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
					chehewreck.generate(world, random, Xcoord1, Ycoord1, Zcoord1);
    			}
   	 		}*/
    	
    		for (int x = 0;x<4;x++)
    		{
    			int var6 = blockX + random.nextInt(16);
    			int var7 = blockZ + random.nextInt(16);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 4, var7) == Block.waterStill.blockID)) //|| world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				chehewreck.generate(world, random, var6, var8, var7);
    			}
    		}
   	 	}
    	
    	WorldGeneratorGalleonWreck galleonwreck = new WorldGeneratorGalleonWreck();
    	if(biome instanceof BiomeSwamp2)
   	 	{
    	
    		for (int x = 0;x<4;x++)
    		{
    			int var6 = blockX + random.nextInt(512);
    			int var7 = blockZ + random.nextInt(512);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 1, var7) == Block.waterStill.blockID))// || world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				galleonwreck.generate(world, random, var6, var8, var7);
    			}
    		}
   	 	}
    	
    	WorldGeneratorShipWreck180 shipwreck180 = new WorldGeneratorShipWreck180();
    	if(biome instanceof BiomeRiver1)
   	 	{
    	
    		for (int x = 0;x<4;x++)
    		{
    			int var6 = blockX + random.nextInt(512);
    			int var7 = blockZ + random.nextInt(512);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 1, var7) == Block.waterStill.blockID))// || world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				shipwreck180.generate(world, random, var6, var8, var7);
    			}
    		}
   	 	}
    	
    	WorldGeneratorShipWreck270 shipwreck270 = new WorldGeneratorShipWreck270();
    	if(biome instanceof BiomeOcean2)
   	 	{
    	
    		for (int x = 0;x<4;x++)
    		{
    			int var6 = blockX + random.nextInt(512);
    			int var7 = blockZ + random.nextInt(512);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 1, var7) == Block.waterStill.blockID))// || world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				shipwreck270.generate(world, random, var6, var8, var7);
    			}
    		}
   	 	}
    	
    	WorldGeneratorShipWreck90 shipwreck90 = new WorldGeneratorShipWreck90();
    	if(biome instanceof BiomeJungle1)
   	 	{
    	
    		for (int x = 0;x<4;x++)
    		{
    			int var6 = blockX + random.nextInt(512);
    			int var7 = blockZ + random.nextInt(512);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 1, var7) == Block.waterStill.blockID))// || world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				shipwreck90.generate(world, random, var6, var8, var7);
    			}
    		}
   	 	}
    	
    	WorldGeneratorVikingWreck vikingwreck = new WorldGeneratorVikingWreck();
    	if(biome instanceof BiomeOcean1)
   	 	{
    	
    		for (int x = 0;x<2;x++)
    		{
    			int var6 = blockX + random.nextInt(512);
    			int var7 = blockZ + random.nextInt(512);
    			int var8 = world.getHeightValue(var6, var7);

    			if (random.nextInt(100) == 0 && (world.getBlockId(var6, var8 - 1, var7) == Block.waterStill.blockID))// || world.getBlockId(var6, var8 - 1, var7) == 2 || world.getBlockId(var6, var8 - 1, var7) == Block.sand.blockID))
    			{
    				vikingwreck.generate(world, random, var6, var8, var7);
    			}
    		}
   	 	}
    	
   	 }
    
	private void generateNether(World world, Random random, int blockX, int blockZ) 
	{
			  
	}
    
}
