package mod.fossil.common.fossilAI;

import mod.fossil.common.IHighIntellegent;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import mod.fossil.common.fossilEnums.EnumSituation;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class DinoAILearnChest extends EntityAIBase
{
    IHighIntellegent learnerInterface = null;
    EntityDinosaurce learner = null;
    TileEntityChest nearbyChest = null;
    int learningTick = 300;

    public DinoAILearnChest(EntityDinosaurce var1)
    {
        this.learner = var1;

        if (this.learner instanceof IHighIntellegent)
        {
            this.learnerInterface = (IHighIntellegent)this.learner;
        }
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.learner != null && this.learnerInterface != null)
        {
            if (this.learnerInterface.isLeartChest())
            {
                return false;
            }
            else
            {
                this.nearbyChest = this.getNearbyChest(15, 3, 15);
                return this.nearbyChest != null;
            }
        }
        else
        {
            return false;
        }
    }

    private TileEntityChest getNearbyChest(int var1, int var2, int var3)
    {
        World var4 = this.learner.worldObj;
        TileEntity var5 = null;

        if (var4 == null)
        {
            return null;
        }
        else
        {
            for (int var6 = -var1; var6 <= var1; ++var6)
            {
                for (int var7 = -var2; var7 <= var2; ++var6)
                {
                    if (this.learner.posY + (double)var7 > 0.0D)
                    {
                        for (int var8 = -var3; var8 < var3; ++var8)
                        {
                            var5 = var4.getBlockTileEntity((int)(this.learner.posX + (double)var6), (int)(this.learner.posY + (double)var7), (int)(this.learner.posZ + (double)var8));

                            if (var5 instanceof TileEntityChest)
                            {
                                return (TileEntityChest)var5;
                            }
                        }
                    }
                }
            }

            return null;
        }
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
        --this.learningTick;

        if (this.learningTick <= 0)
        {
            this.learner.SendStatusMessage(EnumSituation.LearingChest, this.learner.SelfType);
            this.learnerInterface.setLearntChest(true);
        }
    }
}
