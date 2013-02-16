package mod.fossil.common.entity.mob;

import mod.fossil.common.Fossil;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityFailuresaurus extends EntityZombie
{
    public EntityFailuresaurus(World var1)
    {
        super(var1);
        this.texture = "/mod/fossil/common/textures/Failuresaurus.png";
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return Fossil.biofossil.itemID;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump() {}
}
