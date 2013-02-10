package mod_Fossil;

import net.minecraft.item.Item;

public class ItemRelic extends Item
{
    public ItemRelic(int i)
    {
        super(i);
        maxStackSize = 64;
    }
    public String getTextureFile()
    {
        return FossilCommonProxy.FOS_ITEMS_PNG;
    }
}