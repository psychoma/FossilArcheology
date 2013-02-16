package mod.fossil.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.item.Item;

public class BlockFossil extends BlockStone
{
    public BlockFossil(int var1, int var2)
    {
        super(var1, var2);
        this.blockIndexInTexture = 0;
    }

    public String getTextureFile()
    {
        return "/mod/fossil/common/textures/Fos_terrian.png";
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int var1, Random var2, int var3)
    {
        int var4 = (new Random()).nextInt(20000);
        return var4 >= 20 && var4 <= 30 ? Fossil.gen.itemID : (var4 <= 4500 ? Fossil.biofossil.itemID : (var4 > 4500 && var4 <= 9800 ? Fossil.relic.itemID : (var4 > 9800 && var4 <= 17800 ? Item.bone.itemID : (var4 > 17800 && var4 <= 19800 ? Fossil.blockSkull.blockID : (var4 > 19800 && var4 <= 19900 ? Fossil.brokenSword.itemID : (var4 > 19900 && var4 <= 20100 ? Fossil.brokenhelmet.itemID : Block.cobblestone.blockID))))));
    }
}