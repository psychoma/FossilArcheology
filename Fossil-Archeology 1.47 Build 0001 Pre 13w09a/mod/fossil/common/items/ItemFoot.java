package mod.fossil.common.items;

import net.minecraft.item.Item;

public class ItemFoot extends Item
{
    public ItemFoot(int var1)
    {
        super(var1);
        this.maxStackSize = 64;
    }

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/Fos_items.png";
    }
}