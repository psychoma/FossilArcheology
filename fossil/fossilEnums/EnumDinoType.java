package fossil.fossilEnums;

import net.minecraft.item.Item;
import fossil.Fossil;
import fossil.entity.mob.EntityBrachiosaurus;
import fossil.entity.mob.EntityMosasaurus;
import fossil.entity.mob.EntityNautilus;
import fossil.entity.mob.EntityPlesiosaur;
import fossil.entity.mob.EntityPterosaur;
import fossil.entity.mob.EntityVelociraptor;
import fossil.entity.mob.EntityStegosaurus;
import fossil.entity.mob.EntityTRex;
import fossil.entity.mob.EntityTriceratops;
import fossil.entity.mob.EntityDilophosaurus;

interface C
{
	/*public static final boolean NO_MODEL =false;
	public static final boolean MODEL =true;
	public static final boolean NO_TAME =false;
	public static final boolean TAME =true;
	public static final boolean NO_RIDE =false;
	public static final boolean RIDE =true;
	public static final boolean NO_CARRY =false;
	public static final boolean CARRY =true;*/
	
	public static final int NOTHING = 0;
	
	public static final int NO_FEEDER = 0 << 0;//Bits 0+1:	Dinos Can't use Feeder at all
	public static final int HERBIVORE = 1 << 0;//Bit 0:		Dino can use Herbivore Part of Feeder 
	public static final int CARNIVORE = 2 << 0;//Bit 1: 	Dino can use Carnivore Part of Feeder 
	public static final int HERB_CARN = 3 << 0;//Bits 0+1: 	Dinos can use Herbivore and Carnivore Part of Feeder 
	
	public static final int MODEL =1 << 2;//Bit 2: Dino is Modelable
	public static final int TAME =1 << 3;//Bit 3: Dino is Tameable
	public static final int RIDE =1 << 4;//Bit 4: Dino is Rideable
	public static final int CARRY =1 << 5;//Bit 5: Dino can Carry Items
}

public enum EnumDinoType
{
	//Name(Class							Modelable	Tameable	Rideable	Can Carry Items
    /*Triceratops(EntityTriceratops.class, 	C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY),
    Velociraptor(EntityVelociraptor.class, 	C.NO_MODEL,	C.TAME,		C.NO_RIDE,	C.CARRY),
    TRex(EntityTRex.class, 					C.NO_MODEL,	C.NO_TAME,	C.RIDE,		C.NO_CARRY),
    Pterosaur(EntityPterosaur.class, 		C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY),
    Nautilus(EntityNautilus.class, 			C.NO_MODEL,	C.NO_TAME,	C.NO_RIDE,	C.NO_CARRY),//I think not really neccessary...
    Plesiosaur(EntityPlesiosaur.class, 		C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY),
    Mosasaurus(EntityMosasaurus.class, 		C.NO_MODEL,	C.NO_TAME,	C.NO_RIDE,	C.NO_CARRY),
    Stegosaurus(EntityStegosaurus.class, 	C.NO_MODEL,	C.TAME,		C.NO_RIDE,	C.NO_CARRY),
    Dilophosaurus(EntityDilophosaurus.class,C.NO_MODEL,	C.TAME,		C.NO_RIDE,	C.CARRY),
    Brachiosaurus(EntityBrachiosaurus.class,C.MODEL,	C.TAME,		C.RIDE,		C.NO_CARRY);*/


    Triceratops(EntityTriceratops.class, 	C.MODEL | C.TAME | C.RIDE | C.HERBIVORE),
    Velociraptor(EntityVelociraptor.class, 	C.TAME | C.CARRY | C.CARNIVORE),
    TRex(EntityTRex.class, 					C.RIDE | C.CARNIVORE),
    Pterosaur(EntityPterosaur.class, 		C.MODEL | C.TAME | C.RIDE | C.CARNIVORE),
    Nautilus(EntityNautilus.class, 			C.NOTHING),
    Plesiosaur(EntityPlesiosaur.class, 		C.MODEL | C.TAME | C.RIDE | C.CARNIVORE),
    Mosasaurus(EntityMosasaurus.class, 		C.NOTHING),
    Stegosaurus(EntityStegosaurus.class, 	C.TAME | C.HERBIVORE),
    Dilophosaurus(EntityDilophosaurus.class,C.TAME | C.CARRY | C.CARNIVORE),
    Brachiosaurus(EntityBrachiosaurus.class,C.MODEL | C.TAME | C.RIDE | C.HERBIVORE);

    private final Class dinoClass;
    /*private final boolean modelable;
    private final boolean tameable;
    private final boolean rideable;
    private final boolean carryitems;*/
    public int Flags=0;
    public Item OrderItem;
    public Item DropItem;
    public Item DNAItem;
    public Item EggItem;

    /**
     * Params: Class, Modelable,Tameable,Rideable,CanCarryItems
     */
    /*private EnumDinoType(Class class0, boolean model0,boolean tame0,boolean ride0,boolean carry0/*,Item i0*)
    {
        this.dinoClass = class0;
        this.modelable = model0;
        this.tameable = tame0;
        this.rideable = ride0;
        this.carryitems = carry0;
        //this.OrderItem = i0;
    }*/
    /**
     * Params: Class, Flags
     */
    private EnumDinoType(Class class0, int f0)
    {
        this.dinoClass = class0;
        this.Flags=f0;
        //this.OrderItem = i0;
    }
    private void setDetails(Item order,Item drop,Item dna,Item egg)
    {
    	this.DropItem = drop;
        this.DNAItem = dna;
        this.EggItem = egg;
        this.OrderItem = order;
    }
    public static void init()
    {//								Order Item			Drop Item				DNA Item				Egg Item
    	Triceratops.setDetails(		Item.stick,			Fossil.rawTriceratops, 	Fossil.dnaTriceratops, 	Fossil.eggTriceratops);
        Velociraptor.setDetails(	Item.bone,			Fossil.rawVelociraptor, Fossil.dnaVelociraptor, Fossil.eggVelociraptor);
        TRex.setDetails(			Fossil.skullStick,	Fossil.rawTRex, 		Fossil.dnaTRex, 		Fossil.eggTRex);
        Pterosaur.setDetails(		Item.arrow,			Fossil.rawPterosaur, 	Fossil.dnaPterosaur, 	Fossil.eggPterosaur);
        Nautilus.setDetails(		null,				Fossil.rawNautilus, 	Fossil.dnaNautilus, 	Fossil.shellNautilus);
        Plesiosaur.setDetails(		Fossil.magicConch,	Fossil.rawPlesiosaur, 	Fossil.dnaPlesiosaur, 	Fossil.eggPlesiosaur);
        Mosasaurus.setDetails(		null,				Fossil.rawMosasaurus, 	Fossil.dnaMosasaurus, 	Fossil.eggMosasaurus);
        Stegosaurus.setDetails(		Item.stick,			Fossil.rawStegosaurus, 	Fossil.dnaStegosaurus, 	Fossil.eggStegosaurus);
        Dilophosaurus.setDetails(	Item.bone,			Fossil.rawDilophosaurus,Fossil.dnaDilophosaurus,Fossil.eggDilophosaurus);
        Brachiosaurus.setDetails(	Item.stick,			Fossil.rawBrachiosaurus,Fossil.dnaBrachiosaurus,Fossil.eggBrachiosaurus);
    }
    public boolean isDinoDNA(Item i0)
    {
		for(int i=0;i<this.values().length;i++)
		{
		    if(this.values()[i].DNAItem.itemID == i0.itemID)
		    	return true;
		}
		return false;
    }
    public boolean isDinoDrop(Item i0)
    {
		for(int i=0;i<this.values().length;i++)
		{
		    if(this.values()[i].DropItem.itemID == i0.itemID)
		    	return true;
		}
		return false;
    }
    public Item getDNA(Item i0)
    {
		for(int i=0;i<this.values().length;i++)
		{
		    if(this.values()[i].DropItem.itemID == i0.itemID || this.values()[i].EggItem.itemID == i0.itemID)
		    	return this.values()[i].DNAItem;
		}
		return null;
    }
    public Item getDrop(Item i0)
    {
		for(int i=0;i<this.values().length;i++)
		{
		    if(this.values()[i].DNAItem.itemID == i0.itemID || this.values()[i].EggItem.itemID == i0.itemID)
			return this.values()[i].DropItem;
		}
		return null;
    }
    public Item getEgg(Item i0)
    {
		for(int i=0;i<this.values().length;i++)
		{
		    if(this.values()[i].DNAItem.itemID == i0.itemID || this.values()[i].DropItem.itemID == i0.itemID)
			return this.values()[i].EggItem;
		}
		return null;
    }
    public Class getDinoClass()
    {
        return this.dinoClass;
    }
    public boolean isModelable()
    {
        return (this.Flags & C.MODEL) != 0;
    }
    public boolean isTameable()
    {
        return (this.Flags & C.TAME) != 0;
    }
    public boolean isRideable()
    {
        return (this.Flags & C.RIDE) != 0;
    }
    public boolean canCarryItems()
    {
        return (this.Flags & C.CARRY) != 0;
    }
    public boolean useFeeder()
    {
    	return (this.Flags & C.HERB_CARN) != 0;
    }
    public boolean isHerbivore()
    {
        return (this.Flags & C.HERBIVORE) != 0;
    }
    public boolean isCarnivore()
    {
    	return (this.Flags & C.CARNIVORE) != 0;
    }
}
