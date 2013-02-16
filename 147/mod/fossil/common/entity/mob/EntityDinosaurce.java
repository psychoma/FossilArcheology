package mod.fossil.common.entity.mob;

import com.google.common.io.ByteArrayDataInput;


import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import mod.fossil.common.Fossil;
import mod.fossil.common.entity.EntityDinoEgg;
import mod.fossil.common.fossilAI.DinoAIControlledByPlayer;
import mod.fossil.common.fossilEnums.EnumDinoType;
import mod.fossil.common.fossilEnums.EnumOrderType;
import mod.fossil.common.fossilEnums.EnumSituation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityDinosaurce extends EntityTameable implements IEntityAdditionalSpawnData
{
    public static final int HUNGER_TICK_DATA_INDEX = 18;
    public static final int HUNGER_DATA_INDEX = 19;
    public static final int AGE_TICK_DATA_INDEX = 20;
    public static final int AGE_DATA_INDEX = 21;
    public static final int SUBSPECIES_INDEX = 22;
    public static final int MODELIZED_INDEX = 23;
    public static final int GROW_TIME_COUNT = 12000;
    public int attackStrength = 0;
    public static String SelfName = "";
    public static String OwnerText = "Owner:";
    public static String UntamedText = "Untamed";
    public static String EnableChestText = " * Chests";
    public static String AgeText = "Age:";
    public static String HelthText = "Health:";
    public static String HungerText = "Hunger:";
    public static String CautionText = "Dangerous";
    public static String RidiableText = " * Rideable";
    public static String WeakText = "Dying";
    public static String FlyText = " * Can Fly";
    public EnumDinoType SelfType = null;
    public final int BREED_LIMIT = 3000;
    public int BreedTick = 3000;
    public static EntityDinosaurce pediaingDino = null;
    protected DinoAIControlledByPlayer ridingHandler;
    public final int HungerTickLimit = 300;
    public EnumOrderType OrderStatus;

    public DinoAIControlledByPlayer getRidingHandler()
    {
        return this.ridingHandler;
    }

    public boolean isModelized()
    {
        return this.dataWatcher.getWatchableObjectByte(23) >= 0;
    }

    public void setModelized(boolean var1)
    {
        if (this.SelfType.isModelable())
        {
            this.dataWatcher.updateObject(23, Byte.valueOf((byte)(var1 ? 0 : -1)));

            if (var1)
            {
                this.texture = this.getModelTexture();
            }
        }
    }

    public int getHungerLimit()
    {
        return 100;
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(21, new Integer(0));
        this.dataWatcher.addObject(20, new Integer(0));
        this.dataWatcher.addObject(19, new Integer(100));
        this.dataWatcher.addObject(18, new Integer(300));
        this.dataWatcher.addObject(22, new Integer(1));
        this.dataWatcher.addObject(23, new Byte((byte) - 1));
    }

    public int getSubSpecies()
    {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public void setSubSpecies(int var1)
    {
        this.dataWatcher.updateObject(22, Integer.valueOf(var1));
    }

    public int getDinoAge()
    {
        return this.dataWatcher.getWatchableObjectInt(21);
    }

    public int getDinoAgeTick()
    {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public int getHunger()
    {
        return this.dataWatcher.getWatchableObjectInt(19);
    }

    public int getHungerTick()
    {
        return this.dataWatcher.getWatchableObjectInt(18);
    }

    public void increaseHunger(int var1)
    {
        this.setHunger(this.getHunger() + var1);
    }

    public void increaseHungerTick()
    {
        this.setHungerTick(this.getHungerTick() + 1);
    }

    public void setDinoAge(int var1)
    {
        this.dataWatcher.updateObject(21, Integer.valueOf(var1));
    }

    public void increaseDinoAge()
    {
        this.setDinoAge(this.getDinoAge() + 1);
    }

    public void increaseDinoAgeTick()
    {
        this.setDinoAgeTick(this.getDinoAgeTick() + 1);
    }

    public void decreaseDinoAge()
    {
        if (this.getDinoAge() > 0)
        {
            this.setDinoAge(this.getDinoAge() - 1);
        }
    }

    public void decreaseHunger()
    {
        if (this.getHunger() > 0)
        {
            this.increaseHunger(-1);
        }
    }

    public void decreaseHungerTick()
    {
        if (this.getHungerTick() > 0)
        {
            this.setHungerTick(this.getHungerTick() - 1);
        }
    }

    public void setDinoAgeTick(int var1)
    {
        this.dataWatcher.updateObject(20, Integer.valueOf(var1));
    }

    public void setHunger(int var1)
    {
        this.dataWatcher.updateObject(19, Integer.valueOf(var1));
    }

    public void setHungerTick(int var1)
    {
        this.dataWatcher.updateObject(18, Integer.valueOf(var1));
    }

    protected String getModelTexture()
    {
        return "/mod/fossil/common/textures/DinoModel" + this.SelfType.toString() + ".png";
    }

    /**
     * Returns the texture's file path as a String.
     */
    public String getTexture()
    {
        return this.isModelized() ? this.getModelTexture() : this.texture;
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getTalkInterval()
    {
        return 360;
    }

    @SideOnly(Side.CLIENT)
    public abstract void ShowPedia(EntityPlayer var1);

    public abstract boolean HandleEating(int var1);

    public static void UpdateGlobleText() {}

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return false;
    }

    public boolean attackEntityAsMob(Entity var1)
    {
        int var2 = this.attackStrength;

        if (this.isPotionActive(Potion.damageBoost))
        {
            var2 += 3 << this.getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }

        if (this.isPotionActive(Potion.weakness))
        {
            var2 -= 2 << this.getActivePotionEffect(Potion.weakness).getAmplifier();
        }

        return var1.attackEntityFrom(DamageSource.causeMobDamage(this), var2);
    }

    public void HandleBreed()
    {
        if (this.getDinoAge() > 4)
        {
            --this.BreedTick;

            if (this.BreedTick == 0)
            {
                int var1 = 0;
                List var2 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(32.0D, 32.0D, 32.0D));

                for (int var3 = 0; var3 < var2.size(); ++var3)
                {
                    if (var2.get(var3) instanceof EntityDinosaurce)
                    {
                        EntityDinosaurce var4 = (EntityDinosaurce)var2.get(var3);

                        if (var4.SelfType == this.SelfType)
                        {
                            ++var1;
                        }
                    }
                }

                if (var1 > 50)
                {
                    var1 = 50;
                }

                if (var1 > 80)
                {
                    return;
                }

                if ((new Random()).nextInt(100) < var1)
                {
                    EntityDinoEgg var5 = null;
                    var5 = new EntityDinoEgg(this.worldObj, this.SelfType);
                    var5.setLocationAndAngles(this.posX + (double)((new Random()).nextInt(3) - 1), this.posY, this.posZ + (double)((new Random()).nextInt(3) - 1), this.worldObj.rand.nextFloat() * 360.0F, 0.0F);

                    if (this.worldObj.checkIfAABBIsClear(var5.boundingBox) && this.worldObj.getCollidingBoundingBoxes(var5, var5.boundingBox).size() == 0)
                    {
                        this.worldObj.spawnEntityInWorld(var5);
                    }

                    this.showHeartsOrSmokeFX(true);
                }

                this.BreedTick = 3000;
            }
        }
    }

    public boolean CheckSpace()
    {
        return !this.isEntityInsideOpaqueBlock();
    }

    protected void getPathOrWalkableBlock(Entity var1, float var2)
    {
        PathEntity var3 = this.worldObj.getPathEntityToEntity(this, var1, 16.0F, true, false, true, false);
        this.setPathToEntity(var3);
    }

    /**
     * Sets the width and height of the entity. Args: width, height
     */
    public void setSize(float var1, float var2)
    {
        super.setSize(var1, var2);
    }

    public void SendOrderMessage(EnumOrderType var1)
    {
        String var2 = "";
        String var3 = "Order.";
        var2 = Fossil.GetLangTextByKey("Order.Head");
        var2 = var2 + Fossil.GetLangTextByKey("Order." + var1.toString());
        Fossil.ShowMessage(var2, (EntityPlayer)this.getOwner());
    }

    public void SendStatusMessage(EnumSituation var1)
    {
        this.SendStatusMessage(var1, this.SelfType);
    }

    public void SendStatusMessage(EnumSituation var1, EnumDinoType var2)
    {
        if (this.worldObj.getClosestPlayerToEntity(this, 25.0D) != null)
        {
            String var3 = GetNameByEnum(var2, true);
            String var4 = "";
            String var5 = "Status.";
            String var6 = "Head.";
            String var7 = "";

            switch (EntityDinosaurce$1.$SwitchMap$mod_Fossil$EnumSituation[var1.ordinal()])
            {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    var7 = Fossil.GetLangTextByKey("Status.Head.SomeOf");
                    break;

                case 7:
                case 8:
                    var7 = Fossil.GetLangTextByKey("Status.Head.This");
                    var3 = GetNameByEnum(var2, false);
                    break;

                case 9:
                    var7 = Fossil.GetLangTextByKey("Status.Head.Nervous");
                    var3 = GetNameByEnum(var2, false);
                    break;

                case 10:
                case 11:
                case 12:
                    var3 = "";
            }

            var4 = var7 + var3 + Fossil.GetLangTextByKey("Status." + var1.toString());
            Fossil.ShowMessage(var4, (EntityPlayer)this.getOwner());
        }
    }

    public void showHeartsOrSmokeFX(boolean var1)
    {
        String var2 = "heart";

        if (!var1)
        {
            var2 = "smoke";
        }

        for (int var3 = 0; var3 < 7; ++var3)
        {
            double var4 = this.rand.nextGaussian() * 0.02D;
            double var6 = this.rand.nextGaussian() * 0.02D;
            double var8 = this.rand.nextGaussian() * 0.02D;
            this.worldObj.spawnParticle(var2, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, var4, var6, var8);
        }
    }

    public static String GetNameByEnum(EnumDinoType var0, boolean var1)
    {
        String var2 = "Dino.";
        String var3 = ".Plural";
        String var4 = Fossil.GetLangTextByKey("Dino." + var0.toString());
        String var5 = Fossil.GetLangTextByKey("Dino." + var0.toString() + ".Plural");

        if (var5.equals(" "))
        {
            var5 = var4;
        }

        return var1 ? var5 : var4;
    }

    public void PediaTextCorrection(EnumDinoType var1, EntityPlayer var2)
    {
        SelfName = GetNameByEnum(var1, false);
        String var3 = "PediaText.";
        Fossil.ShowMessage(SelfName, var2);
        OwnerText = Fossil.GetLangTextByKey("PediaText.owner");
        UntamedText = Fossil.GetLangTextByKey("PediaText.Untamed");
        EnableChestText = Fossil.GetLangTextByKey("PediaText.EnableChest");
        AgeText = Fossil.GetLangTextByKey("PediaText.Age");
        HelthText = Fossil.GetLangTextByKey("PediaText.Health");
        HungerText = Fossil.GetLangTextByKey("PediaText.Hunger");
        CautionText = Fossil.GetLangTextByKey("PediaText.Caution");
        RidiableText = Fossil.GetLangTextByKey("PediaText.Ridiable");
        WeakText = Fossil.GetLangTextByKey("PediaText.Weak");
        FlyText = Fossil.GetLangTextByKey("PediaText.Fly");
    }

    public EntityDinosaurce(World var1)
    {
        super(var1);
        this.OrderStatus = EnumOrderType.Follow;
    }

    public float GetDistanceWithXYZ(double var1, double var3, double var5)
    {
        return (float)Math.sqrt(Math.pow(this.posX - var1, 2.0D) + Math.pow(this.posY - var3, 2.0D) + Math.pow(this.posZ - var5, 2.0D));
    }

    public void FaceToCoord(int var1, int var2, int var3)
    {
        double var4 = (double)var1;
        double var6 = (double)var3;
        float var8 = (float)(Math.atan2(var6, var4) * 180.0D / Math.PI) - 90.0F;
        this.rotationYaw = this.updateRotation(this.rotationYaw, var8, 360.0F);
    }

    private float updateRotation(float var1, float var2, float var3)
    {
        float var4;

        for (var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F)
        {
            ;
        }

        while (var4 >= 180.0F)
        {
            var4 -= 360.0F;
        }

        if (var4 > var3)
        {
            var4 = var3;
        }

        if (var4 < -var3)
        {
            var4 = -var3;
        }

        return var1 + var4;
    }

    public float GetDistanceWithTileEntity(TileEntity var1)
    {
        return var1 != null ? (float)Math.sqrt(Math.pow(this.posX - (double)var1.xCoord, 2.0D) + Math.pow(this.posY - (double)var1.yCoord, 2.0D) + Math.pow(this.posZ - (double)var1.zCoord, 2.0D)) : -1.0F;
    }

    public float GetDistanceWithEntity(Entity var1)
    {
        return (float)Math.sqrt(Math.pow(this.posX - var1.posX, 2.0D) + Math.pow(this.posY - var1.posY, 2.0D) + Math.pow(this.posZ - var1.posZ, 2.0D));
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return this.isModelized() ? Item.bone.itemID : Fossil.rawDinoMeat.itemID;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean var1, int var2)
    {
        int var3 = this.getDropItemId();
        int var4 = this.isModelized() ? 0 : this.SelfType.ordinal();

        if (var3 > 0)
        {
            int var5 = this.getDinoAge();

            for (int var6 = 0; var6 < var5; ++var6)
            {
                this.entityDropItem(new ItemStack(var3, 1, var4), 0.0F);
            }
        }
    }

    public abstract void updateSize(boolean var1);

    public boolean isYoung()
    {
        return false;
    }

    public boolean isTamed()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 4) != 0;
    }

    public String getOwnerName()
    {
        return this.dataWatcher.getWatchableObjectString(17);
    }

    public void setOwner(String var1)
    {
        this.dataWatcher.updateObject(17, var1);
    }

    protected boolean modelizedInteract(EntityPlayer var1)
    {
        this.faceEntity(var1, 360.0F, 360.0F);
        ItemStack var2 = var1.inventory.getCurrentItem();

        if (var2 == null)
        {
            return false;
        }
        else
        {
            int var3 = Item.stick.itemID;
            int var4 = Item.bone.itemID;
            int var5 = var2.itemID;

            if (var5 == var4)
            {
                this.increaseDinoAge();
                --var2.stackSize;
            }

            return false;
        }
    }

    protected void updateEntityActionState()
    {
        if (!this.isModelized())
        {
            super.updateEntityActionState();
        }
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return this.isModelized() ? 0.0F : 0.5F + 0.1F * (float)this.getDinoAge();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setBoolean("isModelized", this.isModelized());
        var1.setInteger("Hunger", this.getHunger());
        var1.setInteger("HungerTick", this.getHungerTick());
        var1.setInteger("DinoAge", this.getDinoAge());
        var1.setInteger("AgeTick", this.getDinoAgeTick());
        var1.setByte("OrderStatus", (byte)Fossil.EnumToInt(this.OrderStatus));

        if (this.getOwnerName() == null)
        {
            var1.setString("Owner", "");
        }
        else
        {
            var1.setString("Owner", this.getOwnerName());
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("isModelized"))
        {
            this.setModelized(var1.getBoolean("isModelized"));
        }

        this.setDinoAge(var1.getInteger("DinoAge"));
        this.setDinoAgeTick(var1.getInteger("AgeTick"));
        this.setHunger(var1.getInteger("Hunger"));

        if (this.getHunger() <= 0)
        {
            this.setHunger(this.getHungerLimit());
        }

        this.OrderStatus = EnumOrderType.values()[var1.getByte("OrderStatus")];
        String var2 = var1.getString("Owner");

        if (var2.length() > 0)
        {
            this.setOwner(var2);
            this.setTamed(true);
        }
    }

    protected boolean modelizedDrop()
    {
        if (!this.isModelized())
        {
            return false;
        }
        else
        {
            if (!this.worldObj.isRemote)
            {
                this.entityDropItem(new ItemStack(Fossil.biofossil, 1), 0.0F);
                this.dropFewItems(false, 0);
                this.setDead();
            }

            return true;
        }
    }

    protected abstract int foodValue(Item var1);

    public abstract EnumOrderType getOrderType();

    public void HandleEating(Item var1)
    {
        this.HandleEating(this.foodValue(var1));
    }

    public abstract void HoldItem(Item var1);

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    public void PickUpItem(Item var1)
    {
        if (this.foodValue(var1) > 0)
        {
            this.HandleEating(var1);
        }
        else
        {
            this.HoldItem(var1);
        }
    }

    public void CheckSkin() {}

    protected boolean EOCInteract(ItemStack var1, EntityPlayer var2)
    {
        if (var1 != null && var1.itemID == Fossil.chickenEss.itemID)
        {
            if (this.getDinoAge() < 8 && this.getHunger() > 0)
            {
                --var1.stackSize;

                if (var1.stackSize <= 0)
                {
                    var2.inventory.setInventorySlotContents(var2.inventory.currentItem, (ItemStack)null);
                }

                var2.inventory.addItemStackToInventory(new ItemStack(Item.glassBottle, 1));
                this.setDinoAgeTick(12000);
                this.setHunger(1 + (new Random()).nextInt(this.getHunger()));
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    public void BlockInteractive() {}

    /**
     * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
     * by a player and the player is holding a carrot-on-a-stick
     */
    public boolean canBeSteered()
    {
        ItemStack var1 = ((EntityPlayer)this.riddenByEntity).getHeldItem();
        return var1 != null && var1.itemID == Fossil.whip.itemID;
    }

    public void SetOrder(EnumOrderType var1)
    {
        this.OrderStatus = var1;
    }

    public void writeSpawnData(ByteArrayDataOutput var1) {}

    public void readSpawnData(ByteArrayDataInput var1) {}

    public abstract float getGLX();

    public abstract float getGLY();

    public float getGLZ()
    {
        return this.getGLX();
    }

    public String[] additionalPediaMessage()
    {
        return null;
    }
}
