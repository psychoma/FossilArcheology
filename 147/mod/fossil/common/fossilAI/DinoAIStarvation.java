package mod.fossil.common.fossilAI;

import mod.fossil.common.FossilOptions;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;

public class DinoAIStarvation extends EntityAIBase
{
    EntityDinosaurce mover = null;

    public DinoAIStarvation(EntityDinosaurce var1)
    {
        this.mover = var1;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!FossilOptions.DinoHunger)
        {
            return false;
        }
        else
        {
            this.mover.decreaseHungerTick();
            return this.mover.getHungerTick() <= 0 && this.mover.worldObj.difficultySetting > 0 && this.mover.worldObj.getClosestPlayerToEntity(this.mover, 24.0D) != null;
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        EntityDinosaurce var10000 = this.mover;
        this.mover.getClass();
        var10000.setHungerTick(300);
        this.mover.decreaseHunger();

        if (this.mover.getHunger() <= 0)
        {
            this.handleStarvation();
        }
    }

    private void handleStarvation()
    {
        this.mover.attackEntityFrom(DamageSource.starve, 20);
    }
}
