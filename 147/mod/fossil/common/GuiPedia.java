package mod.fossil.common;

import cpw.mods.fml.relauncher.Side;

import cpw.mods.fml.relauncher.SideOnly;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiPedia extends GuiContainer
{
    private EntityDinosaurce dino;

    public GuiPedia(InventoryPlayer var1, EntityDinosaurce var2, World var3)
    {
        super(new ContainerPedia());
        this.dino = var2;
        this.xSize = 178;
        this.ySize = 164;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
        int var1 = (this.width - this.xSize) / 2;
        int var2 = (this.height - this.ySize) / 2;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
        this.fontRenderer.drawString(this.dino.getDinoAge() + " days", 115, 31, 4210752);
        this.fontRenderer.drawString("" + this.dino.getHealth() + '/' + this.dino.getMaxHealth(), 115, 47, 4210752);
        this.fontRenderer.drawString("" + this.dino.getHunger() + '/' + this.dino.getHungerLimit(), 115, 62, 4210752);
        String[] var3 = this.dino.additionalPediaMessage();

        if (var3 != null)
        {
            for (int var4 = 0; var4 < var3.length && var4 <= 6; ++var4)
            {
                this.fontRenderer.drawString(var3[var4], 104, 80 + this.fontRenderer.FONT_HEIGHT * var4, 4210752);
            }
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        int var4 = this.mc.renderEngine.getTexture("/mod/fossil/common/textures/UIPedia.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        super.drawScreen(var1, var2, var3);
        int var4 = (this.width - this.xSize) / 2;
        int var5 = (this.height - this.ySize) / 2;
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        RenderHelper.enableStandardItemLighting();
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        super.onGuiClosed();
        EntityDinosaurce.pediaingDino = null;
    }
}
