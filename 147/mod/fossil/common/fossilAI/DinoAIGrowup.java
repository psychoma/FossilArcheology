package mod.fossil.common.fossilAI;

import mod.fossil.client.FossilOptions;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import mod.fossil.common.fossilEnums.EnumSituation;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.DamageSource;

public class DinoAIGrowup extends EntityAIBase
{
    private static final int GROW_TIME_COUNT = 12000;
    protected EntityDinosaurce AITarget;
    protected int ageLimit;
    protected int healingOnGrowing;

    public DinoAIGrowup(EntityDinosaurce var1, int var2)
    {
        this.AITarget = null;
        this.ageLimit = 0;
        this.healingOnGrowing = 1;
        this.AITarget = var1;
        this.ageLimit = var2;
    }

    public DinoAIGrowup(EntityDinosaurce var1, int var2, int var3)
    {
        this(var1, var2);
        this.healingOnGrowing = var3;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (!FossilOptions.DinoGrows)
        {
            return false;
        }
        else if (this.AITarget.getDinoAge() > this.ageLimit)
        {
            return false;
        }
        else
        {
            this.AITarget.increaseDinoAgeTick();
            return this.AITarget.getDinoAgeTick() >= 12000;
        }
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        this.AITarget.setDinoAgeTick(0);
        this.AITarget.increaseDinoAge();
        this.AITarget.CheckSkin();

        if (this.AITarget.getHealth() < this.AITarget.getMaxHealth())
        {
            this.AITarget.heal(this.healingOnGrowing);
        }

        this.AITarget.updateSize(false);
        this.AITarget.setPosition(this.AITarget.posX, this.AITarget.posY, this.AITarget.posZ);

        if (!this.AITarget.CheckSpace())
        {
            this.AITarget.decreaseDinoAge();
            this.AITarget.CheckSkin();

            if (this.AITarget.getHealth() > this.healingOnGrowing)
            {
                this.AITarget.attackEntityFrom(DamageSource.generic, this.healingOnGrowing);
            }

            this.AITarget.updateSize(false);
            this.AITarget.setPosition(this.AITarget.posX, this.AITarget.posY, this.AITarget.posZ);

            if (this.AITarget.isTamed())
            {
                this.AITarget.SendStatusMessage(EnumSituation.NoSpace, this.AITarget.SelfType);
            }
        }
    }
}
