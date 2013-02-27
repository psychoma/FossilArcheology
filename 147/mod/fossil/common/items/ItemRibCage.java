package mod.fossil.common.items;

import mod.fossil.common.Fossil;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemRibCage extends ItemArmor implements IArmorTextureProvider
{

	public ItemRibCage(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		
          super(par1, par2EnumArmorMaterial, par3, par4);
          this.setCreativeTab(Fossil.tabFArmor);
 
    }
 
    public String getTextureFile()
	{
           return "/mod/fossil/common/textures/Fos_items.png";
    }
 
    public String getArmorTextureFile(ItemStack par1)
    {
        if ( par1.itemID == Fossil.skullHelmet.itemID|| par1.itemID == Fossil.ribCage.itemID|| par1.itemID == Fossil.feet.itemID)
        {
              return "/mod/fossil/common/armor/bone_1.png";
        }
        if(par1.itemID == Fossil.femurs.itemID)
		{
              return "/mod/fossil/common/armor/bone_2.png";
        }
			  return "/mod/fossil/common/armor/bone_2.png";
    }
	
}