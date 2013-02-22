package mod.fossil.common.fossilEnums;

import mod.fossil.common.Fossil;



public enum EnumOrderType
{
    Stay,
    Follow,
    FreeMove;

    public final EnumOrderType Next()
    {
        switch (this.ordinal())//(EnumOrderType$1.$SwitchMap$mod_Fossil$EnumOrderType[this.ordinal()])
        {
            case 1:
                return Follow;

            case 2:
                return FreeMove;

            case 3:
                return Stay;

            default:
                return FreeMove;
        }
    }
    /*public final int ToInt()
    {
        switch (EnumOrderType$1.$SwitchMap$mod_Fossil$EnumOrderType[this.ordinal()])
        {
            case 1:
                return 0;

            case 2:
                return 1;

            case 3:
                return 2;

            default:
                return 0;
        }
    }*/

    public final String GetOrderString()
    {
        return Fossil.GetLangTextByKey("Order." + this.toString());
    }
}
