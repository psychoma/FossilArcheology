package mod_Fossil;

import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityTameable;

public class DinoAITargetNonTamedExceptSelfClass extends EntityAITargetNonTamed
{
    public DinoAITargetNonTamedExceptSelfClass(
            EntityTameable par1EntityTameable, Class par2Class, float par3,
            int par4, boolean par5)
    {
        super(par1EntityTameable, par2Class, par3, par4, par5);
        // TODO Auto-generated constructor stub
    }
    public boolean shouldExecute()
    {
        boolean result = super.shouldExecute();

//        if (result && this.targetEntity instanceof EntityDinosaurce && ((EntityDinosaurce)this.targetEntity).SelfType == ((EntityDinosaurce)this.taskOwner).SelfType)
        if (result && this.taskOwner instanceof EntityDinosaurce && ((EntityDinosaurce)this.taskOwner).SelfType == ((EntityDinosaurce)this.taskOwner).SelfType)

        {
            return false;
        }

        return result;
    }
}
