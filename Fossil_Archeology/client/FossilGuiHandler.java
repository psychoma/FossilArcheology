package mods.Fossil_Archeology.client;

import mods.Fossil_Archeology.entity.mob.EntityDinosaur;
import mods.Fossil_Archeology.guiBlocks.ContainerAnalyzer;
import mods.Fossil_Archeology.guiBlocks.ContainerCultivate;
import mods.Fossil_Archeology.guiBlocks.ContainerFeeder;
import mods.Fossil_Archeology.guiBlocks.ContainerPedia;
import mods.Fossil_Archeology.guiBlocks.ContainerTimeMachine;
import mods.Fossil_Archeology.guiBlocks.ContainerWorktable;
import mods.Fossil_Archeology.guiBlocks.GuiAnalyzer;
import mods.Fossil_Archeology.guiBlocks.GuiCultivate;
import mods.Fossil_Archeology.guiBlocks.GuiFeeder;
import mods.Fossil_Archeology.guiBlocks.GuiPedia;
import mods.Fossil_Archeology.guiBlocks.GuiTimeMachine;
import mods.Fossil_Archeology.guiBlocks.GuiWorktable;
import mods.Fossil_Archeology.guiBlocks.TileEntityAnalyzer;
import mods.Fossil_Archeology.guiBlocks.TileEntityCultivate;
import mods.Fossil_Archeology.guiBlocks.TileEntityFeeder;
import mods.Fossil_Archeology.guiBlocks.TileEntityTimeMachine;
import mods.Fossil_Archeology.guiBlocks.TileEntityWorktable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class FossilGuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		switch(id)
		{

		case 0: return new ContainerAnalyzer(player.inventory, (TileEntityAnalyzer) tile_entity);
		case 1: return new ContainerCultivate(player.inventory, (TileEntityCultivate) tile_entity);
		case 2: return new ContainerFeeder(player.inventory, (TileEntityFeeder) tile_entity);
		case 3: return new ContainerWorktable(player.inventory, (TileEntityWorktable) tile_entity);
		case 4: return new ContainerPedia();
		case 5: return new ContainerTimeMachine(player.inventory, (TileEntityTimeMachine) tile_entity);
		
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{

		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		switch(id)
		{

		case 0: return new GuiAnalyzer(player.inventory, (TileEntityAnalyzer) tile_entity);
		case 1: return new GuiCultivate(player.inventory, (TileEntityCultivate) tile_entity);
		case 2: return new GuiFeeder(player.inventory, (TileEntityFeeder) tile_entity);
		case 3: return new GuiWorktable(player.inventory, (TileEntityWorktable) tile_entity);
		case 4: return new GuiPedia(/*player.inventory/*, EntityDinosaur.pediaingDino, world*/);
		case 5: return new GuiTimeMachine(player.inventory, (TileEntityTimeMachine) tile_entity);
		
		}

		return null;

	}
}
