package mod_Fossil;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityFailuresaurus extends EntityZombie
{
    public EntityFailuresaurus(World world)
    {
        super(world);
        texture = "/skull/Failuresaurus.png";
    }
    protected int getDropItemId()
    {
        return mod_Fossil.biofossil.shiftedIndex;
    }
    protected void jump()
    {
        return;
    }
}
