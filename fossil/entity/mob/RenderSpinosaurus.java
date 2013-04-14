package fossil.entity.mob;

import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fossil.entity.ModelDinoEgg;

public class RenderSpinosaurus extends RenderLiving
{

    
    public RenderSpinosaurus(ModelBase var1, float var2)
    {
        super(var1, var2);
    }

    public void RenderDino(EntitySpinosaurus var1, double var2, double var4, double var6, float var8, float var9)
    {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        this.mainModel.onGround = this.renderSwingProgress(var1, var9);

        if (this.renderPassModel != null)
        {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }

        this.mainModel.isRiding = var1.isRiding();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }

        try
        {
            float var10 = var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var9;
            float var11 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var9;
            float var12 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
            this.renderLivingAt(var1, var2, var4, var6);
            float var13 = this.handleRotationFloat(var1, var9);
            this.rotateCorpse(var1, var13, var10, var9);
            float var14 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(var1.getDinoWidth(), -var1.getDinoHeight(), var1.getDinoLength());
            this.preRenderCallback(var1, var9);
            GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
            float var15 = var1.prevLegYaw + (var1.legYaw - var1.prevLegYaw) * var9;
            float var16 = var1.legSwing - var1.legYaw * (1.0F - var9);

            if (var15 > 1.0F)
            {
                var15 = 1.0F;
            }

            this.loadDownloadableImageTexture(var1.skinUrl, var1.getTexture());
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            this.mainModel.setLivingAnimations(var1, var16, var15, var9);

            if (var1.getEntityToAttack() == null && var1.ItemInMouth == null && !var1.Screaming)
            {
                ((ModelSpinosaurus)this.mainModel).CloseMouth(10);
            }
            else
            {
                ((ModelSpinosaurus)this.mainModel).OpenMouth(5);
            }

            ((ModelSpinosaurus)this.mainModel).render(var1, var16, var15, var13, var11 - var10, var12, var14);

            for (int var17 = 0; var17 < 4; ++var17)
            {
                if (this.shouldRenderPass(var1, var17, var9) >= 0)
                {
                    this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var12, var14);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            this.renderEquippedItems(var1, var9);
            float var25 = var1.getBrightness(var9);
            int var18 = this.getColorMultiplier(var1, var25, var9);

            if ((var18 >> 24 & 255) > 0 || var1.hurtTime > 0 || var1.deathTime > 0)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (var1.hurtTime > 0 || var1.deathTime > 0)
                {
                    GL11.glColor4f(var25, 0.0F, 0.0F, 0.4F);
                    ((ModelSpinosaurus)this.mainModel).render(var1, var16, var15, var13, var11 - var10, var12, var14);

                    for (int var19 = 0; var19 < 4; ++var19)
                    {
                        if (this.shouldRenderPass(var1, var19, var9) >= 0)
                        {
                            GL11.glColor4f(var25, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var12, var14);
                        }
                    }
                }

                if ((var18 >> 24 & 255) > 0)
                {
                    float var26 = (float)(var18 >> 16 & 255) / 255.0F;
                    float var20 = (float)(var18 >> 8 & 255) / 255.0F;
                    float var21 = (float)(var18 & 255) / 255.0F;
                    float var22 = (float)(var18 >> 24 & 255) / 255.0F;
                    GL11.glColor4f(var26, var20, var21, var22);
                    ((ModelSpinosaurus)this.mainModel).render(var1, var16, var15, var13, var11 - var10, var12, var14);

                    for (int var23 = 0; var23 < 4; ++var23)
                    {
                        if (this.inheritRenderPass(var1, var23, var9) >= 0)
                        {
                            GL11.glColor4f(var26, var20, var21, var22);
                            this.renderPassModel.render(var1, var16, var15, var13, var11 - var10, var12, var14);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }
            //this.renderEquippedItems(var1, var9);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
        catch (Exception var24)
        {
            var24.printStackTrace();
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        this.passSpecialRender(var1, var2, var4, var6);
    }

    public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.RenderDino((EntitySpinosaurus)var1, var2, var4, var6, var8, var9);
    }
    
    /*protected void renderEquippedItems(EntityLiving var1, float var2)
    {
        float var3 = 1.0F + 0.0F * (float)((EntityDinosaur)var1).getDinoAge();
        ItemStack var4 = ((EntityRaptor)var1).ItemInMouth;

        if (var4 != null)
        {
            GL11.glPushMatrix();
            ((ModelRaptor)this.mainModel).head3_up.postRender(0.01F);
            float var5;

            if (var4.itemID < 256 && RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType()))
            {
                var5 = 0.5F;
                GL11.glTranslatef(0.0F, 0.4F, -0.75F);
                var5 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(var5 * var3, -var5 * var3, var5 * var3);
            }
            else if (Item.itemsList[var4.itemID].isFull3D())
            {
                var5 = 0.625F;
                GL11.glTranslatef(0.0F, 0.4F, -0.75F);
                GL11.glScalef(var5 * var3, -var5 * var3, var5 * var3);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            }
            else
            {
                var5 = 0.375F;
                GL11.glTranslatef(0.0F, 0.4F, -0.75F);
                GL11.glScalef(var5 * var3, var5 * var3, var5 * var3);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }

            this.renderManager.itemRenderer.renderItem(var1, var4, 1);
            GL11.glPopMatrix();
        }
    }*/

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        this.RenderDino((EntitySpinosaurus)var1, var2, var4, var6, var8, var9);
    }
}
