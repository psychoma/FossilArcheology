package fossil.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fossil.Fossil;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemRibCage extends ItemArmor implements IArmorTextureProvider
{
	private String iconPath;
	@SideOnly(Side.CLIENT)
	private Icon icon;

	public ItemRibCage(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{	
          super(par1, par2EnumArmorMaterial, par3, par4);
    }
 
	@Override
	public void updateIcons(IconRegister iconRegister)
	{
	         iconIndex = iconRegister.registerIcon("Fossil:BonePlate");
	}

    public String getArmorTextureFile(ItemStack par1)
    {
        if ( par1.itemID == Fossil.skullHelmet.itemID|| par1.itemID == Fossil.ribCage.itemID|| par1.itemID == Fossil.feet.itemID)
        {
              return "/fossil/armor/bone_1.png";
        }
        if(par1.itemID == Fossil.femurs.itemID)
		{
              return "/fossil/armor/bone_2.png";
        }
			  return "/fossil/armor/bone_2.png";
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int par1) 
    {
      return this.icon;
    }
	
}