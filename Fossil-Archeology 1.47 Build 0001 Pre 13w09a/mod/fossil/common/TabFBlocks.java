package mod.fossil.common;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;

public class TabFBlocks extends CreativeTabs 
{

	public TabFBlocks(int par1, String par2Str)
    {
            super(par1, par2Str);
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
       return Fossil.drum.blockID;
    }
    
    public String getTranslatedTabLabel()
    {
     return "Fossil Blocks";
    }
	
}
