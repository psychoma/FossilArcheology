package mod.fossil.common.items;

import java.util.List;

import mod.fossil.common.IViviparous;
import mod.fossil.common.entity.mob.EntityPregnantCow;
import mod.fossil.common.entity.mob.EntityPregnantPig;
import mod.fossil.common.entity.mob.EntityPregnantSheep;
import mod.fossil.common.fossilEnums.EnumEmbyos;
import mod.fossil.common.items.forgeItems.*;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemStack;

public class ItemEmbryoSyringe extends ForgeItem
{
    private String[] ItemNames = new String[] {"EmbyoPig", "EmbyoSheep", "EmbyoCow", "EmbyoSaberCat", "EmbyoMammoth"};

    public ItemEmbryoSyringe(int var1)
    {
        super(var1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 64;
        this.initItemNameVector();
    }

    private void initItemNameVector() {}

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/needle.png";
    }

    /**
     * Gets an icon index based on an item's damage value
     */
    public int getIconFromDamage(int var1)
    {
        return var1;
    }

    public String getItemNameIS(ItemStack var1)
    {
        int var2 = var1.getItemDamage();
        return var2 < this.ItemNames.length ? this.ItemNames[var2] : "EmbyoSyring";
    }

    public static EnumEmbyos GetEmbyo(int var0)
    {
        return EnumEmbyos.values()[var0];
    }

    /**
     * dye sheep, place saddles, etc ...
     */
    public boolean itemInteractionForEntity(ItemStack var1, EntityLiving var2)
    {
        if (var2 instanceof EntityAnimal && ((EntityAnimal)var2).getGrowingAge() == 0)
        {
            Object var3 = null;

            if (var2 instanceof EntityPig)
            {
                var3 = new EntityPregnantPig(var2.worldObj);
            }

            if (var2 instanceof EntityCow)
            {
                var3 = new EntityPregnantCow(var2.worldObj);
            }

            if (var2 instanceof EntitySheep)
            {
                var3 = new EntityPregnantSheep(var2.worldObj);
                ((EntitySheep)var3).setFleeceColor(((EntitySheep)var2).getFleeceColor());
                ((EntitySheep)var3).setSheared(((EntitySheep)var2).getSheared());
            }

            if (var3 != null)
            {
                ((IViviparous)var3).SetEmbyo(GetEmbyo(var1.getItemDamage()));
                ((EntityAnimal)var3).setLocationAndAngles(var2.posX, var2.posY, var2.posZ, var2.rotationYaw, var2.rotationPitch);
                var2.setDead();

                if (!var2.worldObj.isRemote)
                {
                    var2.worldObj.spawnEntityInWorld((EntityAnimal)var3);
                }

                --var1.stackSize;
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(int var1, CreativeTabs var2, List var3)
    {
        for (int var4 = 0; var4 < EnumEmbyos.values().length; ++var4)
        {
            var3.add(new ItemStack(var1, 1, var4));
        }
    }
}
