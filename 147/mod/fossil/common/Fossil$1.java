package mod.fossil.common;

import mod.fossil.common.fossilEnums.EnumEmbyos;

class mod_Fossil$1
{
    static final int[] $SwitchMap$mod_Fossil$EnumEmbyos = new int[EnumEmbyos.values().length];

    static
    {
        try
        {
            $SwitchMap$mod_Fossil$EnumEmbyos[EnumEmbyos.Cow.ordinal()] = 1;
        }
        catch (NoSuchFieldError var3)
        {
            ;
        }

        try
        {
            $SwitchMap$mod_Fossil$EnumEmbyos[EnumEmbyos.Sheep.ordinal()] = 2;
        }
        catch (NoSuchFieldError var2)
        {
            ;
        }

        try
        {
            $SwitchMap$mod_Fossil$EnumEmbyos[EnumEmbyos.Pig.ordinal()] = 3;
        }
        catch (NoSuchFieldError var1)
        {
            ;
        }
    }
}
