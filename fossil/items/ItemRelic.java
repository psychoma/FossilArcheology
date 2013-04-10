package fossil.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemRelic extends Item
{
    public ItemRelic(int var1)
    {
        super(var1);
        this.maxStackSize = 64;
    }

    @Override
    public void updateIcons(IconRegister iconRegister)
    {
             iconIndex = iconRegister.registerIcon("Fossil:RelicScrap");
    }

}
