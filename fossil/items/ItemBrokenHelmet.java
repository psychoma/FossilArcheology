package fossil.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemBrokenHelmet extends Item
{
    public ItemBrokenHelmet(int var1)
    {
        super(var1);
        this.maxStackSize = 1;
    }

    @Override
    public void updateIcons(IconRegister iconRegister)
    {
             iconIndex = iconRegister.registerIcon("Fossil:BrokenHelmet");
    }

}
