package mod_Fossil;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
public class RenderStegosaurus extends RenderLiving
{
    public RenderStegosaurus(ModelBase modelbase, float f)
    {
        super(modelbase, f);
        setRenderPassModel(modelbase);
    }
    public RenderStegosaurus(ModelBase modelbase, ModelBase modelbase1, float f)
    {
        super(modelbase, f);
        setRenderPassModel(modelbase1);
    }
    public void renderCow(EntityStegosaurus entityStegosaurs, double d, double d1, double d2,
            float f, float f1)
    {
        //super.doRenderLiving(entityTriceratops, d, d1, d2, f, f1);
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        mainModel.onGround = renderSwingProgress(entityStegosaurs, f1);

        if (renderPassModel != null)
        {
            renderPassModel.onGround = mainModel.onGround;
        }

        mainModel.isRiding = entityStegosaurs.isRiding();

        if (renderPassModel != null)
        {
            renderPassModel.isRiding = mainModel.isRiding;
        }

        try
        {
            float f2 = entityStegosaurs.prevRenderYawOffset + (entityStegosaurs.renderYawOffset - entityStegosaurs.prevRenderYawOffset) * f1;
            float f3 = entityStegosaurs.prevRotationYaw + (entityStegosaurs.rotationYaw - entityStegosaurs.prevRotationYaw) * f1;
            float f4 = entityStegosaurs.prevRotationPitch + (entityStegosaurs.rotationPitch - entityStegosaurs.prevRotationPitch) * f1;
            renderLivingAt(entityStegosaurs, d, d1, d2);
            float f5 = handleRotationFloat(entityStegosaurs, f1);
            rotateCorpse(entityStegosaurs, f5, f2, f1);
            float f6 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(entityStegosaurs.getGLX(), - entityStegosaurs.getGLY(), entityStegosaurs.getGLZ());
            preRenderCallback(entityStegosaurs, f1);
            GL11.glTranslatef(0.0F, -24F * f6 - 0.0078125F, 0.0F);
            float f7 = entityStegosaurs.prevLegYaw + (entityStegosaurs.legYaw - entityStegosaurs.prevLegYaw) * f1;
            float f8 = entityStegosaurs.legSwing - entityStegosaurs.legYaw * (1.0F - f1);

            if (f7 > 1.0F)
            {
                f7 = 1.0F;
            }

            loadDownloadableImageTexture(entityStegosaurs.skinUrl, entityStegosaurs.getTexture());
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            mainModel.setLivingAnimations(entityStegosaurs, f8, f7, f1);

            if (!entityStegosaurs.Running)
            {
                ((ModelStegosaurus)mainModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
            }
            else
            {
                ((ModelStegosaurus)mainModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
            }

            for (int i = 0; i < 4; i++)
            {
                if (shouldRenderPass(entityStegosaurs, i, f1) >= 0)
                {
                    ((ModelStegosaurus)renderPassModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            renderEquippedItems(entityStegosaurs, f1);
            float f9 = entityStegosaurs.getBrightness(f1);
            int j = getColorMultiplier(entityStegosaurs, f9, f1);

            if ((j >> 24 & 0xff) > 0 || entityStegosaurs.hurtTime > 0 || entityStegosaurs.deathTime > 0)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (entityStegosaurs.hurtTime > 0 || entityStegosaurs.deathTime > 0)
                {
                    GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);

                    if (!entityStegosaurs.Running)
                    {
                        ((ModelStegosaurus)mainModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                    }
                    else
                    {
                        ((ModelStegosaurus)mainModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                    }

                    for (int k = 0; k < 4; k++)
                    {
                        if (inheritRenderPass(entityStegosaurs, k, f1) >= 0)
                        {
                            GL11.glColor4f(f9, 0.0F, 0.0F, 0.4F);
                            ((ModelStegosaurus)renderPassModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                        }
                    }
                }

                if ((j >> 24 & 0xff) > 0)
                {
                    float f10 = (float)(j >> 16 & 0xff) / 255F;
                    float f11 = (float)(j >> 8 & 0xff) / 255F;
                    float f12 = (float)(j & 0xff) / 255F;
                    float f13 = (float)(j >> 24 & 0xff) / 255F;
                    GL11.glColor4f(f10, f11, f12, f13);

                    if (!entityStegosaurs.Running)
                    {
                        ((ModelStegosaurus)mainModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                    }
                    else
                    {
                        ((ModelStegosaurus)mainModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                    }

                    for (int l = 0; l < 4; l++)
                    {
                        if (inheritRenderPass(entityStegosaurs, l, f1) >= 0)
                        {
                            GL11.glColor4f(f10, f11, f12, f13);
                            ((ModelStegosaurus)renderPassModel).render(entityStegosaurs, f8, f7, f5, f3 - f2, f4, f6);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        passSpecialRender(entityStegosaurs, d, d1, d2);
    }

    public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,
            float f, float f1)
    {
        //GL11.glScalef(10F,10F,10F);
        renderCow((EntityStegosaurus)entityliving, d, d1, d2, f, f1);
    }

    public void doRender(Entity entity, double d, double d1, double d2,
            float f, float f1)
    {
        renderCow((EntityStegosaurus)entity, d, d1, d2, f, f1);
    }
    protected int shouldRenderPass(EntityLiving entityliving, int i, float f)
    {
        return -1;
    }
}