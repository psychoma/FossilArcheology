package mod.fossil.common.entity.mob;

import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import mod.fossil.common.Fossil;
import mod.fossil.common.fossilAI.DinoAIFollowOwner;
import mod.fossil.common.fossilAI.DinoAIGrowup;
import mod.fossil.common.fossilAI.DinoAIPickItem;
import mod.fossil.common.fossilAI.DinoAIStarvation;
import mod.fossil.common.fossilAI.DinoAIUseFeeder;
import mod.fossil.common.fossilEnums.EnumDinoEating;
import mod.fossil.common.fossilEnums.EnumDinoType;
import mod.fossil.common.fossilEnums.EnumOrderType;
import mod.fossil.common.fossilEnums.EnumSituation;
import net.minecraft.block.Block;
import net.minecraft.block.StepSound;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.src.ModLoader;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPterosaur extends EntityDinosaurce
{
    protected final int AGE_LIMIT = 8;
    public final float HuntLimit = (float)(this.getHungerLimit() * 4 / 5);
    private boolean looksWithInterest;
    private float field_25048_b;
    private float field_25054_c;
    private boolean isWolfShaking;
    private boolean field_25052_g;
    public ItemStack ItemInMouth = null;
    public int LearningChestTick = 900;
    public int SubType = 0;
    public int BreedTick = 3000;
    public float AirSpeed = 0.0F;
    public float AirAngle = 0.0F;
    public float AirPitch = 0.0F;
    public float LastAirPitch = 0.0F;
    public boolean Landing = false;

    public EntityPterosaur(World var1)
    {
        super(var1);
        this.SelfType = EnumDinoType.Pterosaur;
        this.looksWithInterest = false;
        this.CheckSkin();
        this.setSize(0.8F, 0.8F);
        this.moveSpeed = 2.0F;
        this.health = 10;
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(0, new DinoAIGrowup(this, 8));
        this.tasks.addTask(0, new DinoAIStarvation(this));
        this.tasks.addTask(1, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityTRex.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityBrachiosaurus.class, 8.0F, 0.3F, 0.35F));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, this.moveSpeed, true));
        this.tasks.addTask(5, new DinoAIFollowOwner(this, this.moveSpeed, 5.0F, 2.0F));
        this.tasks.addTask(6, new DinoAIUseFeeder(this, this.moveSpeed, 24, this.HuntLimit, EnumDinoEating.Carnivorous));
        this.tasks.addTask(6, new DinoAIPickItem(this, Item.fishRaw, this.moveSpeed, 24, this.HuntLimit));
        this.tasks.addTask(6, new DinoAIPickItem(this, Item.fishCooked, this.moveSpeed, 24, this.HuntLimit));
        this.tasks.addTask(6, new DinoAIPickItem(this, Fossil.sjl, this.moveSpeed * 2.0F, 24, this.HuntLimit));
        this.tasks.addTask(7, new EntityAIWander(this, 0.3F));
        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return false;
    }

    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return this.isModelized() ? false : this.riddenByEntity == null;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setBoolean("Angry", this.isSelfAngry());
        var1.setFloat("Airspeed", this.AirSpeed);
        var1.setFloat("AirAngle", this.AirAngle);
        var1.setFloat("AirPitch", this.AirPitch);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
        this.setSelfAngry(var1.getBoolean("Angry"));
        this.setSelfSitting(var1.getBoolean("Sitting"));

        if (var1.hasKey("SubType"))
        {
            this.SubType = var1.getInteger("SubType");
        }

        this.InitSize();
        this.AirSpeed = var1.getFloat("Airspeed");
        this.AirAngle = var1.getFloat("AirAngle");
        this.AirPitch = var1.getFloat("AirPitch");
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return false;
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return this.worldObj.getClosestPlayerToEntity(this, 8.0D) != null ? "PTS_living" : null;
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "PTS_hurt";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "raptor_death";
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.worldObj.checkIfAABBIsClear(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).size() == 0 && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        this.HandleRiding();
        super.onLivingUpdate();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        this.field_25054_c = this.field_25048_b;

        if (this.looksWithInterest)
        {
            this.field_25048_b += (1.0F - this.field_25048_b) * 0.4F;
        }
        else
        {
            this.field_25048_b += (0.0F - this.field_25048_b) * 0.4F;
        }

        if (this.looksWithInterest)
        {
            this.numTicksToChaseTarget = 10;
        }
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    public int getVerticalFaceSpeed()
    {
        return this.isSelfSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    /**
     * Disables a mob's ability to move on its own while true.
     */
    protected boolean isMovementCeased()
    {
        return this.isSelfSitting() || this.field_25052_g;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        return this.isSelfAngry() ? this.worldObj.getClosestPlayerToEntity(this, 16.0D) : null;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource var1, int var2)
    {
        return this.modelizedDrop() ? true : super.attackEntityFrom(var1, var2);
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity var1, float var2)
    {
        if (var2 > 2.0F && var2 < 5.0F && this.rand.nextInt(10) == 0)
        {
            if (this.onGround)
            {
                double var3 = var1.posX - this.posX;
                double var5 = var1.posZ - this.posZ;
                float var7 = MathHelper.sqrt_double(var3 * var3 + var5 * var5);
                this.motionX = var3 / (double)var7 * 0.5D * 0.800000011920929D + this.motionX * 0.20000000298023224D;
                this.motionZ = var5 / (double)var7 * 0.5D * 0.800000011920929D + this.motionZ * 0.20000000298023224D;
                this.jump();
            }
        }
        else if ((double)var2 < 1.899999976158142D && var1.boundingBox.maxY > this.boundingBox.minY && var1.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            var1.attackEntityFrom(DamageSource.causeMobDamage(this), 2 + this.getDinoAge());
        }
    }

    public void updateRiderPosition()
    {
        float var1 = -this.getGLY();

        if (this.riddenByEntity != null)
        {
            if (this.onGround)
            {
                this.riddenByEntity.setPosition(this.posX, this.posY - (double)var1 * 1.1D, this.posZ);
            }
            else if (this.Landing)
            {
                this.riddenByEntity.setPosition(this.posX, this.posY - (double)var1, this.posZ);
            }
            else
            {
                this.riddenByEntity.setPosition(this.posX, this.posY - (double)var1 * 0.6D, this.posZ);
            }
        }
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        if (this.isModelized())
        {
            return this.modelizedInteract(var1);
        }
        else
        {
            ItemStack var2 = var1.inventory.getCurrentItem();

            if (FMLCommonHandler.instance().getSide().isClient() && var2 != null && var2.itemID == Fossil.dinoPedia.itemID)
            {
                EntityDinosaurce.pediaingDino = this;
                var1.openGui(var1, 4, this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ);
                return true;
            }
            else
            {
                if (this.isTamed())
                {
                    if (var2 != null)
                    {
                        if (var2.itemID == Item.arrow.itemID && var1.username.equalsIgnoreCase(this.getOwnerName()))
                        {
                            if (!this.worldObj.isRemote)
                            {
                                this.isJumping = false;
                                this.setPathToEntity((PathEntity)null);
                                this.OrderStatus = EnumOrderType.values()[(Fossil.EnumToInt(this.OrderStatus) + 1) % 3];
                                this.SendOrderMessage(this.OrderStatus);

                                switch (EntityPterosaur$1.$SwitchMap$mod_Fossil$EnumOrderType[this.OrderStatus.ordinal()])
                                {
                                    case 1:
                                        this.setSelfSitting(true);
                                        break;

                                    case 2:
                                        this.setSelfSitting(false);
                                        break;

                                    case 3:
                                        this.setSelfSitting(false);
                                }
                            }

                            return true;
                        }

                        if (this.EOCInteract(var2, var1))
                        {
                            return true;
                        }

                        if (var2 != null && (var2.itemID == Item.fishRaw.itemID || var2.itemID == Item.fishCooked.itemID || var2.itemID == Fossil.sjl.itemID))
                        {
                            if (this.HandleEating(30))
                            {
                                --var2.stackSize;

                                if (var2.stackSize <= 0)
                                {
                                    var1.inventory.setInventorySlotContents(var1.inventory.currentItem, (ItemStack)null);
                                }

                                this.heal(3);
                            }

                            return true;
                        }

                        return false;
                    }

                    if (this.ItemInMouth != null)
                    {
                        if (this.worldObj.isRemote)
                        {
                            return false;
                        }

                        int var3 = this.ItemInMouth.stackSize;

                        if (var1.inventory.addItemStackToInventory(this.ItemInMouth))
                        {
                            ModLoader.onItemPickup(var1, this.ItemInMouth);
                            this.worldObj.playSoundAtEntity(var1, "random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                            this.ItemInMouth = null;
                            return true;
                        }

                        return false;
                    }

                    if (this.isTamed() && this.getDinoAge() > 4 && !this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == var1))
                    {
                        var1.rotationYaw = this.rotationYaw;
                        var1.mountEntity(this);
                        this.setPathToEntity((PathEntity)null);
                        this.renderYawOffset = this.rotationYaw;
                        return true;
                    }
                }

                return false;
            }
        }
    }

    public void handleHealthUpdate(byte var1)
    {
        if (var1 == 7)
        {
            this.showHeartsOrSmokeFX(true);
        }
        else if (var1 == 6)
        {
            this.showHeartsOrSmokeFX(false);
        }
        else if (var1 == 8)
        {
            this.field_25052_g = true;
        }
        else
        {
            super.handleHealthUpdate(var1);
        }
    }

    /**
     * Will return how many at most can spawn in a chunk at once.
     */
    public int getMaxSpawnedInChunk()
    {
        return 100;
    }

    public boolean isSelfAngry()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 2) != 0;
    }

    public boolean isSelfSitting()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void setSelfAngry(boolean var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (var1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 2)));
            this.moveSpeed = 2.0F;
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -3)));
        }
    }

    public void setSelfSitting(boolean var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (var1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 1)));
        }
        else
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -2)));
        }
    }

    public void setTamed(boolean var1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (var1)
        {
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 | 4)));
        }
        else
        {
            this.ItemInMouth = null;
            this.SendStatusMessage(EnumSituation.Bytreate);
            this.dataWatcher.updateObject(16, Byte.valueOf((byte)(var2 & -5)));
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        if (this.riddenByEntity != null && !this.Landing)
        {
            this.riddenByEntity.fallDistance = var1;
        }

        int var2 = (int)Math.ceil((double)(var1 - 3.0F));

        if (!this.worldObj.isRemote)
        {
            if (var2 > 0)
            {
                int var3 = this.worldObj.getBlockId(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY - 0.20000000298023224D - (double)this.yOffset), MathHelper.floor_double(this.posZ));

                if (var3 > 0)
                {
                    StepSound var4 = Block.blocksList[var3].stepSound;
                    this.worldObj.playSoundAtEntity(this, var4.getBreakSound(), var4.getVolume() * 0.5F, var4.getPitch() * 0.75F);
                }
            }
        }
    }

    private void InitSize()
    {
        this.CheckSkin();
        this.updateSize(false);
        this.setPosition(this.posX, this.posY, this.posZ);
    }

    public void CheckSkin()
    {
        if (!this.isModelized())
        {
            this.texture = "/mod/fossil/common/textures/Pterosaur.png";
        }
        else
        {
            this.texture = this.getModelTexture();
        }
    }

    public boolean CheckSpace()
    {
        return !this.isEntityInsideOpaqueBlock();
    }

    public boolean HandleEating(int var1)
    {
        if (this.getHunger() >= this.getHungerLimit())
        {
            return false;
        }
        else
        {
            this.increaseHunger(var1);
            this.showHeartsOrSmokeFX(false);

            if (this.getHunger() >= this.getHungerLimit())
            {
                this.setHunger(this.getHungerLimit());
            }

            return true;
        }
    }

    public void ChangeSubType(int var1)
    {
        if (var1 <= 2 && var1 >= 0)
        {
            this.SubType = var1;
            this.CheckSkin();
        }
    }

    public void ShowPedia(EntityPlayer var1)
    {
        this.PediaTextCorrection(this.SelfType, var1);

        if (this.isTamed())
        {
            Fossil.ShowMessage(OwnerText + this.getOwnerName(), var1);
            Fossil.ShowMessage(AgeText + this.getDinoAge(), var1);
            Fossil.ShowMessage(HelthText + this.health + "/" + 20, var1);
            Fossil.ShowMessage(HungerText + this.getHunger() + "/" + this.getHungerLimit(), var1);

            if (this.getDinoAge() >= 5)
            {
                Fossil.ShowMessage(FlyText, var1);
            }

            if (this.getDinoAge() >= 8)
            {
                Fossil.ShowMessage(RidiableText, var1);
            }
        }
        else
        {
            Fossil.ShowMessage(UntamedText, var1);
        }
    }

    public String[] additionalPediaMessage()
    {
        String[] var1 = null;

        if (!this.isTamed())
        {
            var1 = new String[] {UntamedText};
        }
        else
        {
            ArrayList var2 = new ArrayList();

            if (this.getDinoAge() >= 5)
            {
                var2.add(FlyText);
            }

            if (this.getDinoAge() >= 8)
            {
                var2.add(RidiableText);
            }

            if (!var2.isEmpty())
            {
                var1 = new String[1];
                var1 = (String[])var2.toArray(var1);
            }
        }

        return var1;
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    public void jump()
    {
        this.motionY = 0.8D;
    }

    private void HandleRiding()
    {
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityClientPlayerMP)
        {
            EntityClientPlayerMP var1 = (EntityClientPlayerMP)this.riddenByEntity;
            this.HandleLanding();

            if (!this.onGround && !this.inWater)
            {
                this.AirAngle -= var1.movementInput.moveStrafe;

                if (this.AirAngle > 30.0F)
                {
                    this.AirAngle = 30.0F;
                }

                if (this.AirAngle < -30.0F)
                {
                    this.AirAngle = -30.0F;
                }

                if (Math.abs(this.AirAngle) > 10.0F)
                {
                    this.rotationYaw += (float)(this.AirAngle > 0.0F ? 1 : -1);
                }

                while (this.rotationYaw < -180.0F)
                {
                    this.rotationYaw += 360.0F;
                }

                while (this.rotationYaw >= 180.0F)
                {
                    this.rotationYaw -= 360.0F;
                }

                if (this.Landing)
                {
                    this.AirPitch = 0.0F;

                    if (!this.isCollidedVertically)
                    {
                        this.motionY = -0.2D;
                    }
                    else
                    {
                        this.motionY = 0.0D;
                    }

                    this.setMoveForward(this.AirSpeed);
                }
                else
                {
                    if ((this.isCollidedHorizontally || this.isCollidedVertically) && this.AirSpeed != 0.0F)
                    {
                        this.AirSpeed = 0.0F;
                        this.setMoveForward(0.0F);
                        return;
                    }

                    if (this.AirSpeed == 0.0F && this.moveForward != 0.0F)
                    {
                        this.AirSpeed = this.moveForward * this.moveSpeed;
                    }

                    this.AirAngle -= var1.movementInput.moveStrafe;

                    if (this.AirAngle > 30.0F)
                    {
                        this.AirAngle = 30.0F;
                    }

                    if (this.AirAngle < -30.0F)
                    {
                        this.AirAngle = -30.0F;
                    }

                    this.AirPitch -= var1.movementInput.moveForward * 2.0F;

                    if (this.AirPitch > 90.0F)
                    {
                        this.AirPitch = 90.0F;
                    }

                    if (this.AirPitch < -60.0F)
                    {
                        this.AirPitch = -60.0F;
                    }

                    float var2 = (float)((double)this.AirPitch * 0.017453292519943295D);

                    if (this.LastAirPitch >= this.AirPitch)
                    {
                        double var3 = Math.cos((double)var2);

                        if (var2 < 0.0F)
                        {
                            ++var3;
                        }

                        this.setMoveForward(this.AirSpeed * (float)var3);

                        if (this.AirPitch < 60.0F && this.moveForward > 0.1F)
                        {
                            this.motionY = Math.sin((double)var2) * 0.4D;
                        }
                    }

                    this.LastAirPitch = this.AirPitch;
                }
            }
            else
            {
                if (this.AirSpeed != 0.0F)
                {
                    this.AirSpeed = 0.0F;
                }

                if (this.AirAngle != 0.0F)
                {
                    this.AirAngle = 0.0F;
                }

                if (this.AirPitch != 0.0F)
                {
                    this.AirPitch = 0.0F;
                }

                for (this.rotationYaw -= var1.movementInput.moveStrafe * 5.0F; this.rotationYaw < -180.0F; this.rotationYaw += 360.0F)
                {
                    ;
                }

                while (this.rotationYaw >= 180.0F)
                {
                    this.rotationYaw -= 360.0F;
                }

                this.setMoveForward(var1.movementInput.moveForward * this.moveSpeed);
            }
        }
    }

    public void SetOrder(EnumOrderType var1)
    {
        this.OrderStatus = var1;
    }

    public EntityPterosaur spawnBabyAnimal(EntityAgeable var1)
    {
        return new EntityPterosaur(this.worldObj);
    }

    public int getMaxHealth()
    {
        return 20;
    }

    public void HandleLanding()
    {
        if (this.riddenByEntity != null && !this.isCollidedVertically && !this.onGround)
        {
            if (!this.Landing && this.AirPitch > 60.0F)
            {
                this.Landing = true;
            }
        }
        else
        {
            this.Landing = false;
        }
    }

    public void updateSize(boolean var1)
    {
        if (var1)
        {
            int var10000 = this.getDinoAge();
            this.getClass();

            if (var10000 < 8)
            {
                this.increaseDinoAge();
            }
        }

        this.setSize((float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge())), (float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge())));
    }

    public EnumOrderType getOrderType()
    {
        return this.OrderStatus;
    }

    protected int foodValue(Item var1)
    {
        return 0;
    }

    public void HandleEating(Item var1) {}

    public void HoldItem(Item var1) {}

    public float getGLX()
    {
        return (float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge()));
    }

    public float getGLY()
    {
        return (float)(0.800000011920929D + 0.2D * (double)((float)this.getDinoAge()));
    }

    public EntityAgeable func_90011_a(EntityAgeable var1)
    {
        return this.spawnBabyAnimal(var1);
    }

	@Override
	public EntityAgeable createChild(EntityAgeable var1) 
	{
		return null;
	}
}
