package mod.fossil.common.guiBlocks;

import cpw.mods.fml.relauncher.Side;

import cpw.mods.fml.relauncher.SideOnly;
import mod.fossil.common.entity.EntityDinoEgg;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import mod.fossil.common.Fossil;

@SideOnly(Side.CLIENT)
public class GuiPedia extends GuiContainer
{
    public GuiPedia(/*InventoryPlayer var1*/)
    {
        super(new ContainerPedia());
        this.xSize = 178;
        this.ySize = 164;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        super.initGui();
    }

    /**
     * Print a String to left or Right, starting with 0
     */
    public void PrintStringLR(String str0,boolean left0,int line)
    {
    	//this.fontRenderer.drawString(str0, 20+(left0? 80 : 0), 15+(left0 ? left++*15 :right++*15), 4210752);//Doesn't work because can't reset left or right
    	this.fontRenderer.drawString(str0, 18+(left0? 0 : 78), 15*(line+1), 4210752);
    }
    public void PrintStringLR(String str0,boolean left0,int line,int r,int g,int b)
    {
    	//this.fontRenderer.drawString(str0, 20+(left0? 80 : 0), 15+(left0 ? left++*15 :right++*15), 4210752);//Doesn't work because can't reset left or right
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, 18+(left0? 0 : 78), 15*(line+1), col);
    }
    
    /**
     * Print a String to X,Y
     */
    public void PrintStringXY(String str0,int x0,int y0)
    {
    	this.fontRenderer.drawString(str0, x0, y0, 4210752);
    }
    public void PrintStringXY(String str0,int x0,int y0,int r,int g,int b)
    {
    	int col=(r << 16) | (g << 8) | b;
    	this.fontRenderer.drawString(str0, x0, y0, col);
    }
    
    /**
     * Print a Symbol at X,Y
     */
    public void PrintItemXY(Item it0,int x0,int y0)
    {
    	double px=((double)(it0.getIconFromDamage(0)%16))/16D;
    	double py=((double)(it0.getIconFromDamage(0)/16))/16D;
    	
    	GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture(it0.getTextureFile()));
        //this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(it0.getTextureFile()));//Does the same as the line above
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)x0			, (double)(y0 + 16)	, 0D, px		, py+0.0625D);//x,y,z to place at, the relative x,y of the whole texture
        var9.addVertexWithUV((double)(x0 + 16)	, (double)(y0 + 16)	, 0D, px+0.0625D, py+0.0625D);
        var9.addVertexWithUV((double)(x0 + 16)	, (double)y0		, 0D, px+0.0625D, py);
        var9.addVertexWithUV((double)x0			, (double)y0		, 0D, px		, py);
        var9.draw();
    	int pos0=it0.getIconFromDamage(0);
        //this.drawTexturedModalRect(x0, y0, pos0 % 16 * 16, pos0 / 16 * 16, 16, 16);//Does this, too
    }
    
    /**
     * Print a Picture at X,Y
     */
    public void PrintPictXY(String str0,int x0,int y0,int width,int height)
    {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture(str0));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Tessellator var9 = Tessellator.instance;
        var9.startDrawingQuads();
        var9.addVertexWithUV((double)x0				, (double)(y0 + height)	, 0D, 0D, 1D);
        var9.addVertexWithUV((double)(x0 + width)	, (double)(y0 + height)	, 0D, 1D, 1D);
        var9.addVertexWithUV((double)(x0 + width)	, (double)y0			, 0D, 1D, 0D);
        var9.addVertexWithUV((double)x0				, (double)y0			, 0D, 0D, 0D);
        var9.draw();
    }
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int var1, int var2)
    {
    	if(Fossil.ToPedia instanceof EntityDinosaurce)((EntityDinosaurce)Fossil.ToPedia).ShowPedia(this);	
    	if(Fossil.ToPedia instanceof EntityDinoEgg)((EntityDinoEgg)Fossil.ToPedia).ShowPedia(this);
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
    /*public void drawScreen(int var1, int var2, float var3)
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
    }*/

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        //EntityDinosaurce.pediaingDino = null;
        //Fossil.ToPedia=null;
        super.onGuiClosed();
    }
}
