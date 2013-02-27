package mod.fossil.common.items;

import java.util.Random;

import mod.fossil.common.entity.EntityDinoEgg;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import mod.fossil.common.entity.mob.EntityNautilus;
import mod.fossil.common.fossilEnums.EnumDinoType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBioFossil extends Item
{
    public ItemBioFossil(int var1)
    {
        super(var1);
        this.maxStackSize = 64;
    }

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/Fos_items.png";
    }

    public boolean tryPlaceIntoWorld(ItemStack var1, EntityPlayer var2, World var3, int var4, int var5, int var6, int var7, float var8, float var9, float var10)
    {
        if (var3.isRemote)
        {
            return true;
        }
        else
        {
            Class var11 = this.getRandomModel().getDinoClass();
            EntityDinosaurce var12;

            try
            {
                var12 = (EntityDinosaurce)var11.getConstructor(new Class[] {World.class}).newInstance(new Object[] {var3});
            }
            catch (Throwable var14)
            {
                var14.printStackTrace();
                return false;
            }

            var12.setModelized(true);
            var12.setLocationAndAngles((double)var4, (double)(var5 + 1), (double)var6, var3.rand.nextFloat() * 360.0F, 0.0F);
            var12.faceEntity(var2, 360.0F, 360.0F);

            if (var3.checkIfAABBIsClear(var12.boundingBox) && var3.getCollidingBoundingBoxes(var12, var12.boundingBox).size() == 0)
            {
                var3.spawnEntityInWorld(var12);
                --var1.stackSize;
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean spawnCreature(World var0, EnumDinoType var1, double var2, double var4, double var6)
    {
        Object var8;

        if (var1 == EnumDinoType.Nautilus)
        {
            var8 = new EntityNautilus(var0);
        }
        else
        {
            var8 = new EntityDinoEgg(var0, var1);
        }

        if (var8 != null)
        {
            ((Entity)var8).setLocationAndAngles(var2, var4, var6, var0.rand.nextFloat() * 360.0F, 0.0F);
            var0.spawnEntityInWorld((Entity)var8);
        }

        return var8 != null;
    }

    private EnumDinoType getRandomModel()
    {
        EnumDinoType[] var1 = EnumDinoType.values();
        int var2 = var1.length;
        Random var4 = new Random();
        EnumDinoType var3;

        do
        {
            var3 = var1[var4.nextInt(var2)];
        }
        while (!var3.isModelable());

        return var3;
    }
}
