package mods.Fossil_Archeology.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemBrokenHelmet extends Item
{
    public ItemBrokenHelmet(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
    }

    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("Fossil_Archeology:BrokenHelmet");
    }

}
