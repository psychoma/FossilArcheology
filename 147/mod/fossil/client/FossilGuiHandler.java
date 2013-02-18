package mod.fossil.client;

import mod.fossil.common.entity.mob.EntityDinosaurce;
import mod.fossil.common.guiBlocks.ContainerAnalyzer;
import mod.fossil.common.guiBlocks.ContainerCultivate;
import mod.fossil.common.guiBlocks.ContainerFeeder;
import mod.fossil.common.guiBlocks.ContainerPedia;
import mod.fossil.common.guiBlocks.ContainerTimeMachine;
import mod.fossil.common.guiBlocks.ContainerWorktable;
import mod.fossil.common.guiBlocks.GuiAnalyzer;
import mod.fossil.common.guiBlocks.GuiCultivate;
import mod.fossil.common.guiBlocks.GuiFeeder;
import mod.fossil.common.guiBlocks.GuiPedia;
import mod.fossil.common.guiBlocks.GuiTimeMachine;
import mod.fossil.common.guiBlocks.GuiWorktable;
import mod.fossil.common.guiBlocks.TileEntityAnalyzer;
import mod.fossil.common.guiBlocks.TileEntityCultivate;
import mod.fossil.common.guiBlocks.TileEntityFeeder;
import mod.fossil.common.guiBlocks.TileEntityTimeMachine;
import mod.fossil.common.guiBlocks.TileEntityWorktable;
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
		case 4: return new GuiPedia(player.inventory, EntityDinosaurce.pediaingDino, world);
		case 5: return new GuiTimeMachine(player.inventory, (TileEntityTimeMachine) tile_entity);
		
		}

		return null;

	}
}
