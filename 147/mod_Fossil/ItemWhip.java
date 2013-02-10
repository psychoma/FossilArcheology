package mod_Fossil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCarrotOnAStick;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemWhip extends ItemCarrotOnAStick
{
    public ItemWhip(int par1)
    {
        super(par1);
        this.setIconCoord(1, 4);
        this.setMaxStackSize(1);
        this.setMaxDamage(25);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
     * hands.
     */
    public boolean shouldRotateAroundWhenRendering()
    {
        return true;
    }

    public String getTextureFile()
    {
        return FossilCommonProxy.FOS_ITEMS_PNG;
    }
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	/*
        if (par3EntityPlayer.isRiding() && par3EntityPlayer.ridingEntity instanceof EntityDinosaurce)
        {
            EntityDinosaurce var4 = (EntityDinosaurce)par3EntityPlayer.ridingEntity;

            if (var4.getRidingHandler().isControlledByPlayer() && par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() >= 7)
            {
                var4.getRidingHandler().boostSpeed();
                par1ItemStack.damageItem(7, par3EntityPlayer);

                if (par1ItemStack.stackSize == 0)
                {
                    return new ItemStack(Item.fishingRod);
                }
            }
        }*/

        return par1ItemStack;
    }
}
