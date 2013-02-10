// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package mod_Fossil;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;

// Referenced classes of package net.minecraft.src:
//            Item

public class ForgeItemArmor extends ItemArmor
{
    public ForgeItemArmor(int i, EnumArmorMaterial enumarmormaterial, int j, int k)
    {
        super(i, enumarmormaterial, j, k);
    }
    public String getTextureFile()
    {
        return FossilCommonProxy.FOS_ITEMS_PNG;
    }
}
