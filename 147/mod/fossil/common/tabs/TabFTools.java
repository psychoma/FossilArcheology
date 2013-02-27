package mod.fossil.common.tabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.fossil.common.Fossil;
import net.minecraft.creativetab.CreativeTabs;

public class TabFTools extends CreativeTabs 
{

	public TabFTools(int par1, String par2Str)
    {
            super(par1, par2Str);
    }
    
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
       return Fossil.genHoe.itemID;
    }
    
    public String getTranslatedTabLabel()
    {
     return "Fossil Tools";
    }
	
}
