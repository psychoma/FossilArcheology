package mods.Fossil_Archeology.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.Fossil_Archeology.items.forgeItems.*;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemChickenSoup extends ForgeItem
{
    public static final int BASEICONLOC = 58;
    public static final Item CONTAINER = Item.bucketEmpty;

    public ItemChickenSoup(int var1)
    {
        super(var1);
        //this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 1;
    }
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("Fossil_Archeology:Raw_Chicken_Soup");
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    //public int getIconFromDamage(int var1)
    //{
    //    return 58 + (var1 == 0 ? 0 : 2);
    //}

    /*public String getItemNameIS(ItemStack var1)
    {
        return var1.getItemDamage() == 1 ? "CookedChickenSoup" : "RawChickenSoup";
    }*/
}
