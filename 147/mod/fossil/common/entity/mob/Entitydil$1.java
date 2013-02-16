package mod.fossil.common.entity.mob;

import mod.fossil.common.fossilEnums.EnumOrderType;


class Entitydil$1
{
    static final int[] $SwitchMap$mod_Fossil$EnumOrderType = new int[EnumOrderType.values().length];

    static
    {
        try
        {
            $SwitchMap$mod_Fossil$EnumOrderType[EnumOrderType.Stay.ordinal()] = 1;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
            $SwitchMap$mod_Fossil$EnumOrderType[EnumOrderType.Follow.ordinal()] = 2;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
            $SwitchMap$mod_Fossil$EnumOrderType[EnumOrderType.FreeMove.ordinal()] = 3;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
