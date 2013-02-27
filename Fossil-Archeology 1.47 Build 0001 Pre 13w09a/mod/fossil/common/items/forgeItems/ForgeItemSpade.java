package mod.fossil.common.items.forgeItems;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;

public class ForgeItemSpade extends ItemSpade
{
    public ForgeItemSpade(int var1, EnumToolMaterial var2)
    {
        super(var1, var2);
    }

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/Fos_items.png";
    }
}
