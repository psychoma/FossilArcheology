package mod.fossil.common.items;

import java.util.List;

import mod.fossil.common.fossilEnums.EnumDinoType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemDNA extends Item
{
    public static final int TypeCount = EnumDinoType.values().length;

    public ItemDNA(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 64;
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    //public int getIconFromDamage(int var1)
    //{
    //    return var1 < TypeCount ? 6 + var1 : 0;
    //}

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/Fos_items.png";
    }

    /*public String getItemNameIS(ItemStack var1)
    {
        switch (ItemDNA$1.$SwitchMap$mod_Fossil$EnumDinoType[this.GetTypeFromInt(var1.getItemDamage()).ordinal()])
        {
            case 1:
                return "DNAtriceratops";

            case 2:
                return "DNARaptor";

            case 3:
                return "DNATRex";

            case 4:
                return "DNAPterosaur";

            case 5:
                return "DNANautilus";

            case 6:
                return "DNAPlesiosaur";

            case 7:
                return "DNAMosasaurus";

            case 8:
                return "DNAStegosaurus";

            case 9:
                return "DNAUtahraptor";

            case 10:
                return "DNABrachiosaurus";

            default:
                return "DNA";
        }
    }*/

    private EnumDinoType GetTypeFromInt(int var1)
    {
        EnumDinoType[] var2 = EnumDinoType.values();
        return var2[var1];
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    /*public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < EnumDinoType.values().length; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }*/
}
