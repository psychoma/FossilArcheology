package mod_Fossil;

import net.minecraft.item.Item;

public class ItemBrokenSword extends Item
{
    public ItemBrokenSword(int i)
    {
        super(i);
        maxStackSize = 1;
    }
    public String getTextureFile()
    {
        return FossilCommonProxy.FOS_ITEMS_PNG;
    }
}