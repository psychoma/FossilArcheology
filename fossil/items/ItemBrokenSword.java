package fossil.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemBrokenSword extends Item
{
    public ItemBrokenSword(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
    }

    @Override
    public void updateIcons(IconRegister iconRegister)
    {
             iconIndex = iconRegister.registerIcon("Fossil:BrokenSword");
    }

}
