package mod.fossil.common.fossilEnums;

import net.minecraft.item.Item;
import mod.fossil.common.Fossil;
import mod.fossil.common.entity.mob.EntityBrachiosaurus;
import mod.fossil.common.entity.mob.EntityMosasaurus;
import mod.fossil.common.entity.mob.EntityNautilus;
import mod.fossil.common.entity.mob.EntityPlesiosaur;
import mod.fossil.common.entity.mob.EntityPterosaur;
import mod.fossil.common.entity.mob.EntityRaptor;
import mod.fossil.common.entity.mob.EntityStegosaurus;
import mod.fossil.common.entity.mob.EntityTRex;
import mod.fossil.common.entity.mob.EntityTriceratops;
import mod.fossil.common.entity.mob.Entitydil;

interface C
{
	public static final boolean NO_MODEL =false;
	public static final boolean MODEL =true;
	public static final boolean NO_TAME =false;
	public static final boolean TAME =true;
	public static final boolean NO_RIDE =false;
	public static final boolean RIDE =true;
	public static final boolean NO_CARRY =false;
	public static final boolean CARRY =true;
}

public enum EnumDinoType
{
	//Name(Class							Modelable	Tameable	Rideable	Can Carry Items Order Item
    Triceratops(EntityTriceratops.class, 	C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY,		Item.stick),
    Raptor(EntityRaptor.class, 				C.NO_MODEL,	C.TAME,		C.NO_RIDE,	C.CARRY,		Item.bone),
    TRex(EntityTRex.class, 					C.NO_MODEL,	C.NO_TAME,	C.RIDE,		C.NO_CARRY,		Fossil.skullStick),
    Pterosaur(EntityPterosaur.class, 		C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY,		Item.arrow),
    Nautilus(EntityNautilus.class, 			C.NO_MODEL,	C.NO_TAME,	C.NO_RIDE,	C.NO_CARRY,		null),//I think not really neccessary...
    Plesiosaur(EntityPlesiosaur.class, 		C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY,		Fossil.emptyShell),
    Mosasaurus(EntityMosasaurus.class, 		C.NO_MODEL,	C.NO_TAME,	C.NO_RIDE,	C.NO_CARRY,		null),
    Stegosaurus(EntityStegosaurus.class, 	C.NO_MODEL,	C.TAME,		C.NO_RIDE,	C.NO_CARRY,		Item.stick),
    Utahraptor(Entitydil.class, 			C.NO_MODEL,	C.TAME,		C.NO_RIDE,	C.CARRY,		Item.bone),
    Brachiosaurus(EntityBrachiosaurus.class,C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY,		Item.stick);
    private final Class dinoClass;
    private final boolean modelable;
    private final boolean tameable;
    private final boolean rideable;
    private final boolean carryitems;
    private Item OrderItem;

    /**
     * Params: Class, Modelable,Tameable,Rideable,CanCarryItems
     */
    private EnumDinoType(Class class0, boolean model0,boolean tame0,boolean ride0,boolean carry0,Item i0)
    {
        this.dinoClass = class0;
        this.modelable = model0;
        this.tameable = tame0;
        this.rideable = ride0;
        this.carryitems = carry0;
        this.OrderItem = i0;
    }

    public Class getDinoClass()
    {
        return this.dinoClass;
    }
    public boolean isModelable()
    {
        return this.modelable;
    }
    public boolean isTameable()
    {
        return this.tameable;
    }
    public boolean isRideable()
    {
        return this.rideable;
    }
    public boolean canCarryItems()
    {
        return this.carryitems;
    }
    public Item getOrderItem()
    {
        return this.OrderItem;
    }
}
