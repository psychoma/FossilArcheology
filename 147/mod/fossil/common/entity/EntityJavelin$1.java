package mod.fossil.common.entity;

import net.minecraft.item.EnumToolMaterial;

class EntityJavelin$1
{
    static final int[] $SwitchMap$net$minecraft$item$EnumToolMaterial = new int[EnumToolMaterial.values().length];

    static
    {
        try
        {
            $SwitchMap$net$minecraft$item$EnumToolMaterial[EnumToolMaterial.WOOD.ordinal()] = 1;
        }
        catch (NoSuchFieldError var5)
        {
            ;
        }

        try
        {
            $SwitchMap$net$minecraft$item$EnumToolMaterial[EnumToolMaterial.IRON.ordinal()] = 2;
        }
        catch (NoSuchFieldError var4)
        {
            ;
        }

        try
        {
            $SwitchMap$net$minecraft$item$EnumToolMaterial[EnumToolMaterial.EMERALD.ordinal()] = 3;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
            $SwitchMap$net$minecraft$item$EnumToolMaterial[EnumToolMaterial.STONE.ordinal()] = 4;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
            $SwitchMap$net$minecraft$item$EnumToolMaterial[EnumToolMaterial.GOLD.ordinal()] = 5;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
