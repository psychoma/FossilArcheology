// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

package mod_Fossil;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

// Referenced classes of package net.minecraft.src:
//            Item, World, Block, ItemStack,
//            EntityPlayer

public class ItemFernSeed extends Item
{
    public ItemFernSeed(int i, int j)
    {
        super(i);
        field_318_a = j;
    }
    public String getTextureFile()
    {
        return "/skull/Fos_items.png";
    }
    
    @Override
    
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return true;
        }

        int i1 = par3World.getBlockId(par4, par5, par6);

        if (i1 == Block.grass.blockID && par3World.isAirBlock(par4, par5 + 1, par6) && BlockFern.CheckUnderTree(par3World, par4, par5, par6))
        {
            par3World.setBlockWithNotify(par4, par5 + 1, par6, field_318_a);
            par1ItemStack.stackSize--;
            return true;
        }
        else
        {
            return false;
        }

    	
    }

    private int field_318_a;
}