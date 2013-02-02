package mod_Fossil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntity;
public class ContainerWorktable extends Container
{
    private TileEntityWorktable furnace;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;
    public ContainerWorktable(InventoryPlayer inventoryplayer, TileEntity tileentityfurnace)
    {
        cookTime = 0;
        burnTime = 0;
        itemBurnTime = 0;
        furnace = (TileEntityWorktable) tileentityfurnace;
        addSlotToContainer(new Slot(furnace, 0, 49, 20));
        addSlotToContainer(new Slot(furnace, 1, 80, 53));
        addSlotToContainer(new SlotFurnace(inventoryplayer.player, furnace, 2, 116, 21));

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++)
        {
            addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }
    }
    public void updateCraftingResults()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < crafters.size(); i++)
        {
            ICrafting icrafting = (ICrafting)crafters.get(i);

            if (cookTime != furnace.furnaceCookTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, furnace.furnaceCookTime);
            }

            if (burnTime != furnace.furnaceBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, furnace.furnaceBurnTime);
            }

            if (itemBurnTime != furnace.currentItemBurnTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, furnace.currentItemBurnTime);
            }
        }

        cookTime = furnace.furnaceCookTime;
        burnTime = furnace.furnaceBurnTime;
        itemBurnTime = furnace.currentItemBurnTime;
    }
    public void updateProgressBar(int i, int j)
    {
        if (i == 0)
        {
            furnace.furnaceCookTime = j;
        }

        if (i == 1)
        {
            furnace.furnaceBurnTime = j;
        }

        if (i == 2)
        {
            furnace.currentItemBurnTime = j;
        }
    }
    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return furnace.isUseableByPlayer(entityplayer);
    }
    /*
    public ItemStack func_82846_b(int i)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(i);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i == 2)
            {
                if(!mergeItemStack(itemstack1, 3, 39, true))
                {
                    return null;
                }
            } else
            if(i >= 3 && i < 30)
            {
                if(!mergeItemStack(itemstack1, 30, 39, false))
                {
                    return null;
                }
            } else
            if(i >= 30 && i < 39)
            {
                if(!mergeItemStack(itemstack1, 3, 30, false))
                {
                    return null;
                }
            } else
            {
                if(!mergeItemStack(itemstack1, 3, 39, false))
                {
                    return null;
                }
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack(null);
            } else
            {
                slot.onSlotChanged();
            }
            if (itemstack1.stackSize == itemstack1.stackSize)
            {
                return null;
            }
        }
        return itemstack;
    }
    */

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(var5, 3, 39, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 1 && par2 != 0)
            {
                if (FurnaceRecipes.smelting().getSmeltingResult(var5) != null)
                {
                    if (!this.mergeItemStack(var5, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 3 && par2 < 30)
                {
                    if (!this.mergeItemStack(var5, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (par2 >= 30 && par2 < 39 && !this.mergeItemStack(var5, 3, 30, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 3, 39, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}