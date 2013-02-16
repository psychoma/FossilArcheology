package mod.fossil.common.fossilEnums;

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


public enum EnumDinoType
{
    Triceratops(EntityTriceratops.class, true),
    Raptor(EntityRaptor.class, false),
    TRex(EntityTRex.class, false),
    Pterosaur(EntityPterosaur.class, true),
    Nautilus(EntityNautilus.class, false),
    Plesiosaur(EntityPlesiosaur.class, true),
    Mosasaurus(EntityMosasaurus.class, false),
    Stegosaurus(EntityStegosaurus.class, false),
    dilphosaur(Entitydil.class, false),
    Brachiosaurus(EntityBrachiosaurus.class, true);
    private final Class dinoClass;
    private final boolean modelable;

    private EnumDinoType(Class var3, boolean var4)
    {
        this.dinoClass = var3;
        this.modelable = var4;
    }

    public Class getDinoClass()
    {
        return this.dinoClass;
    }

    public boolean isModelable()
    {
        return this.modelable;
    }
}
