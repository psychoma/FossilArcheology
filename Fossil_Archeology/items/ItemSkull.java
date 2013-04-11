package mods.Fossil_Archeology.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSkull extends Item
{
    public ItemSkull(int var1)
    {
        super(var1);
        this.maxStackSize = 64;
    }

    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister iconRegister)
    {
             iconIndex = iconRegister.registerIcon("Fossil_Archeology:Skull");
    }

}