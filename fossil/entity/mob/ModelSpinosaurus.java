package fossil.entity.mob;


import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
public class ModelSpinosaurus extends ModelDinosaurs
{
  //fields
    ModelRenderer lowerBody;
	ModelRenderer lowerBodyPiece;
	ModelRenderer upperBody;
	ModelRenderer upperBodyPiece;
	ModelRenderer neck;
	ModelRenderer neckPiece;
	ModelRenderer head;
	ModelRenderer upperJawShape;
	ModelRenderer headShape;
	ModelRenderer lowerJaw;
	ModelRenderer teeth;
	ModelRenderer rightArm;
	ModelRenderer rightForeArm;
	ModelRenderer leftArm;
	ModelRenderer leftUpperArmPiece;
	ModelRenderer leftForeArm;
	ModelRenderer crests;
	ModelRenderer crest4;
	ModelRenderer crest1;
	ModelRenderer crest2;
	ModelRenderer crest5;
	ModelRenderer crest3;
	ModelRenderer crest6;
	ModelRenderer crest5Piece;
	ModelRenderer rightThigh;
	ModelRenderer rightCalf;
	ModelRenderer rightFoot;
	ModelRenderer leftThigh;
	ModelRenderer leftCalf;
	ModelRenderer leftFoot;
	ModelRenderer tail1;
	ModelRenderer tail1Piece;
	ModelRenderer tail2;
	ModelRenderer tail2Piece;
	ModelRenderer tail3;
	ModelRenderer tail3Piece;
	ModelRenderer leftThighPiece;
  
  public ModelSpinosaurus()
  {
    textureWidth = 128;
    textureHeight = 128;
    setTextureOffset("lowerBody.lowerBodyPiece", 0, 90);
    setTextureOffset("upperBody.upperBodyPiece", 72, 102);
    setTextureOffset("neck.neckPiece", 78, 83);
    setTextureOffset("head.upperJawShape", 78, 47);
    setTextureOffset("head.headShape", 80, 65);
    setTextureOffset("lowerJaw.lowerJawShape", 78, 32);
    setTextureOffset("teeth.tooth", 56, 84);
    setTextureOffset("teeth.tooth1", 56, 84);
    setTextureOffset("teeth.tooth2", 56, 84);
    setTextureOffset("teeth.tooth3", 56, 84);
    setTextureOffset("teeth.tooth4", 56, 84);
    setTextureOffset("teeth.tooth5", 56, 84);
    setTextureOffset("teeth.tooth6", 56, 84);
    setTextureOffset("teeth.tooth7", 56, 84);
    setTextureOffset("teeth.tooth8", 56, 84);
    setTextureOffset("teeth.tooth9", 56, 84);
    setTextureOffset("teeth.tooth10", 56, 84);
    setTextureOffset("teeth.tooth11", 56, 84);
    setTextureOffset("rightArm.rightUpperArmPiece", 0, 57);
    setTextureOffset("rightForeArm.rightForeArmPiece", 14, 58);
    setTextureOffset("leftArm.leftUpperArmPiece", 0, 57);
    setTextureOffset("leftForeArm.leftForeArmPiece", 14, 58);
    setTextureOffset("crest4.crest4Piece", 38, 50);
    setTextureOffset("crest1.crest1Piece", 38, 50);
    setTextureOffset("crest2.crest2Piece", 38, 50);
    setTextureOffset("crest5.crest5Piece", 48, 50);
    setTextureOffset("crest3.crest3Piece", 56, 50);
    setTextureOffset("crest6.crest6Piece", 38, 50);
    setTextureOffset("rightThigh.rightThighPiece", 0, 67);
    setTextureOffset("rightCalf.rightCalfPiece", 28, 74);
    setTextureOffset("rightFoot.rightFootPiece", 46, 76);
    setTextureOffset("leftThigh.leftThighPiece", 0, 67);
    setTextureOffset("leftCalf.leftCalfPiece", 28, 74);
    setTextureOffset("leftFoot.leftFootPiece", 46, 76);
    setTextureOffset("tail1.tail1Piece", 0, 0);
    setTextureOffset("tail2.tail2Piece", 52, 0);
    setTextureOffset("tail3.tail3Piece", 0, 26);
    
    lowerBody = new ModelRenderer(this, "lowerBody");
    lowerBody.setRotationPoint(0F, 0F, -1F);
    setRotation(lowerBody, 0F, 0F, 0F);
    lowerBody.mirror = true;
      lowerBody.addBox("lowerBodyPiece", -7F, -4F, -2F, 14, 16, 22);
    upperBody = new ModelRenderer(this, "upperBody");
    upperBody.setRotationPoint(0F, -3F, -2F);
    setRotation(upperBody, 0F, 0F, 0F);
    upperBody.mirror = true;
      upperBody.addBox("upperBodyPiece", -5F, 0F, -8F, 10, 14, 12);
    neck = new ModelRenderer(this, "neck");
    neck.setRotationPoint(0F, 1F, -9F);
    setRotation(neck, 0F, 0F, 0F);
    neck.mirror = true;
      neck.addBox("neckPiece", -3.5F, 0F, -10F, 7, 7, 12);
    head = new ModelRenderer(this, "head");
    head.setRotationPoint(0F, 3F, -9F);
    setRotation(head, 0F, 0F, 0F);
      head.addBox("upperJawShape", -3F, -2F, -21F, 6, 5, 13);
      head.addBox("headShape", -4.5F, -4F, -8F, 9, 9, 9);
    lowerJaw = new ModelRenderer(this, "lowerJaw");
    lowerJaw.setRotationPoint(0F, 3F, -8F);
    setRotation(lowerJaw, 0F, 0F, 0F);
      lowerJaw.addBox("lowerJawShape", -2.5F, 0F, -12.5F, 5, 2, 13);
      head.addChild(lowerJaw);
    teeth = new ModelRenderer(this, "teeth");
    teeth.setRotationPoint(0F, 0F, 0F);
    setRotation(teeth, 0F, 0F, 0F);
      teeth.addBox("tooth", -3F, 3F, -11F, 0, 1, 1);
      teeth.addBox("tooth1", -3F, 3F, -13F, 0, 1, 1);
      teeth.addBox("tooth2", -3F, 3F, -15F, 0, 1, 1);
      teeth.addBox("tooth3", -3F, 3F, -17F, 0, 1, 1);
      teeth.addBox("tooth4", -3F, 3F, -20F, 0, 1, 1);
      teeth.addBox("tooth5", 3F, 3F, -20F, 0, 1, 1);
      teeth.addBox("tooth6", 3F, 3F, -11F, 0, 1, 1);
      teeth.addBox("tooth7", 3F, 3F, -13F, 0, 1, 1);
      teeth.addBox("tooth8", 3F, 3F, -15F, 0, 1, 1);
      teeth.addBox("tooth9", 3F, 3F, -17F, 0, 1, 1);
      teeth.addBox("tooth10", -3F, 3F, -21F, 1, 1, 0);
      teeth.addBox("tooth11", 2F, 3F, -21F, 1, 1, 0);
      head.addChild(teeth);
      neck.addChild(head);
      upperBody.addChild(neck);
    rightArm = new ModelRenderer(this, "rightArm");
    rightArm.setRotationPoint(-5F, 6F, -4F);
    setRotation(rightArm, 0F, 0F, 0F);
      rightArm.addBox("rightUpperArmPiece", -3F, 0F, -2F, 3, 6, 4);
    rightForeArm = new ModelRenderer(this, "rightForeArm");
    rightForeArm.setRotationPoint(-1.5F, 5F, 1F);
    setRotation(rightForeArm, 0F, 0F, 0F);
    rightForeArm.mirror = true;
      rightForeArm.addBox("rightForeArmPiece", -1F, 0F, -1F, 2, 6, 3);
      rightArm.addChild(rightForeArm);
      upperBody.addChild(rightArm);
    leftArm = new ModelRenderer(this, "leftArm");
    leftArm.setRotationPoint(5F, 6F, -4F);
    setRotation(leftArm, 0F, 0F, 0F);
      leftArm.addBox("leftUpperArmPiece", 0F, 0F, -2F, 3, 6, 4);
    leftForeArm = new ModelRenderer(this, "leftForeArm");
    leftForeArm.setRotationPoint(1F, 5F, 1F);
    setRotation(leftForeArm, 0F, 0F, 0F);
      leftForeArm.addBox("leftForeArmPiece", -1F, 0F, -1.5F, 2, 6, 3);
      leftArm.addChild(leftForeArm);
      upperBody.addChild(leftArm);
      lowerBody.addChild(upperBody);
    crests = new ModelRenderer(this, "crests");
    crests.setRotationPoint(0F, -4F, 10F);
    setRotation(crests, 0F, 0F, 0F);
    crest4 = new ModelRenderer(this, "crest4");
    crest4.setRotationPoint(0F, 0F, 6.5F);
    setRotation(crest4, 0F, 0F, 0F);
      crest4.addBox("crest4Piece", -1F, -12F, -1.5F, 2, 13, 3);
      crests.addChild(crest4);
    crest1 = new ModelRenderer(this, "crest1");
    crest1.setRotationPoint(0F, 0F, -10.53333F);
    setRotation(crest1, 0F, 0F, 0F);
      crest1.addBox("crest1Piece", -1F, -12F, -1.5F, 2, 13, 3);
      crests.addChild(crest1);
    crest2 = new ModelRenderer(this, "crest2");
    crest2.setRotationPoint(0F, 0F, -7.5F);
    setRotation(crest2, 0F, 0F, 0F);
      crest2.addBox("crest2Piece", -1F, -14F, -1.5F, 2, 15, 3);
      crests.addChild(crest2);
    crest5 = new ModelRenderer(this, "crest5");
    crest5.setRotationPoint(0F, 0F, 9F);
    setRotation(crest5, 0F, 0F, 0F);
      crest5.addBox("crest5Piece", -1F, -8F, -1F, 2, 9, 2);
      crests.addChild(crest5);
    crest3 = new ModelRenderer(this, "crest3");
    crest3.setRotationPoint(0F, 0F, -2F);
    setRotation(crest3, 0F, 0F, 0F);
      crest3.addBox("crest3Piece", -1F, -16F, -4F, 2, 17, 8);
      crests.addChild(crest3);
    crest6 = new ModelRenderer(this, "crest6");
    crest6.setRotationPoint(0F, 0F, 3.5F);
    setRotation(crest6, 0F, 0F, 0F);
      crest6.addBox("crest6Piece", -1F, -14F, -1.5F, 2, 15, 3);
      crests.addChild(crest6);
      lowerBody.addChild(crests);
    rightThigh = new ModelRenderer(this, "rightThigh");
    rightThigh.setRotationPoint(-6F, -2F, 11F);
    setRotation(rightThigh, 0F, 0F, 0F);
      rightThigh.addBox("rightThighPiece", -6F, 0F, -4F, 6, 15, 8);
    rightCalf = new ModelRenderer(this, "rightCalf");
    rightCalf.setRotationPoint(-3F, 14F, 0F);
    setRotation(rightCalf, 0F, 0F, 0F);
      rightCalf.addBox("rightCalfPiece", -2F, -1F, -2F, 4, 11, 5);
    rightFoot = new ModelRenderer(this, "rightFoot");
    rightFoot.setRotationPoint(0F, 8F, 1F);
    setRotation(rightFoot, 0F, 0F, 0F);
      rightFoot.addBox("rightFootPiece", -2.5F, 0F, -7F, 5, 4, 10);
      rightCalf.addChild(rightFoot);
      rightThigh.addChild(rightCalf);
      lowerBody.addChild(rightThigh);
    leftThigh = new ModelRenderer(this, "leftThigh");
    leftThigh.setRotationPoint(6F, -2F, 11F);
    setRotation(leftThigh, 0F, 0F, 0F);
      leftThigh.addBox("leftThighPiece", 0F, 0F, -4F, 6, 15, 8);
    leftCalf = new ModelRenderer(this, "leftCalf");
    leftCalf.setRotationPoint(3F, 14F, 0F);
    setRotation(leftCalf, 0F, 0F, 0F);
      leftCalf.addBox("leftCalfPiece", -2F, -1F, -2F, 4, 11, 5);
    leftFoot = new ModelRenderer(this, "leftFoot");
    leftFoot.setRotationPoint(0F, 8F, 1F);
    setRotation(leftFoot, 0F, 0F, 0F);
      leftFoot.addBox("leftFootPiece", -2.5F, 0F, -7F, 5, 4, 10);
      leftCalf.addChild(leftFoot);
      leftThigh.addChild(leftCalf);
      lowerBody.addChild(leftThigh);
    tail1 = new ModelRenderer(this, "tail1");
    tail1.setRotationPoint(0F, 0F, 20F);
    setRotation(tail1, 0F, 0F, 0F);
      tail1.addBox("tail1Piece", -5F, -1F, -2F, 10, 10, 16);
    tail2 = new ModelRenderer(this, "tail2");
    tail2.setRotationPoint(0F, 1F, 12F);
    setRotation(tail2, 0F, 0F, 0F);
      tail2.addBox("tail2Piece", -3.5F, -1F, 0F, 7, 7, 16);
    tail3 = new ModelRenderer(this, "tail3");
    tail3.setRotationPoint(0F, 1F, 16F);
    setRotation(tail3, 0F, 0F, 0F);
      tail3.addBox("tail3Piece", -2F, -1F, -1F, 4, 4, 16);
      tail2.addChild(tail3);
      tail1.addChild(tail2);
      lowerBody.addChild(tail1);
  }
  
  public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
  {
      super.render(var1, var2, var3, var4, var5, var6, var7);
      this.setRotationAngles(var2, var3, var4, var5, var6, var7, ((EntityDinosaur)var1).isModelized());
     lowerBody.render(var7);
     /*
	 upperBody.render(var7);
	 neck.render(var7);
	 head.render(var7);
	 lowerJaw.render(var7);
	 teeth.render(var7);
	 rightArm.render(var7);
	 rightForeArm.render(var7);
	 leftArm.render(var7);
	 leftForeArm.render(var7);
	 crests.render(var7);
	 crest4.render(var7);
	 crest1.render(var7);
	 crest2.render(var7);
	 crest5.render(var7);
	 crest3.render(var7);
	 crest6.render(var7);
	 tail1.render(var7);
	 tail2.render(var7);
	 tail3.render(var7);
	 */
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  
  
  public void StandPose()
  {
      this.StandPose(20.0F);
  }

  public void StandPose(float var1)
  {
      if (upperBody.rotationPointZ < 2.0F)
      {
          upperBody.rotationPointZ += 2.0F / var1;
      }
      else
      {
          upperBody.rotationPointZ = 2.0F;
      }

      if (upperBody.rotateAngleX > -0.4068249F)
      {
          upperBody.rotateAngleX -= 0.4068249F / var1;
          neck.rotateAngleX += 0.4068249F / var1;
      }
      else
      {
          upperBody.rotateAngleX = -0.4068249F;
      }


      if (this.rightArm.rotationPointY > 10.0F)
      {
          this.rightArm.rotationPointY -= 4.0F / var1;
      }
      else
      {
          this.rightArm.rotationPointY = 10.0F;
      }

      if (this.leftArm.rotationPointY > 10.0F)
      {
          this.leftArm.rotationPointY -= 4.0F / var1;
      }
      else
      {
          this.leftArm.rotationPointY = 10.0F;
      }

      if (this.head.rotationPointY > 0.0F)
      {
          this.head.rotationPointY -= 7.0F / var1;
      }
      else
      {
          this.head.rotationPointY = 0.0F;
      }

      if (this.head.rotationPointZ < -8.0F)
      {
          this.head.rotationPointZ += 6.0F / var1;
      }
      else
      {
          this.head.rotationPointZ = -8.0F;
      }
  }
 
  
  
  protected void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, boolean var7)
  {
      if (!var7)
      {
          this.leftThigh.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
          this.leftCalf.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2 - 0.372F;
          this.leftFoot.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
          this.rightThigh.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.4F * var2;
          this.rightCalf.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.4F * var2 - 0.372F;
          this.rightFoot.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.4F * var2;
          
          
          if (Math.abs(this.rightFoot.rotateAngleX) >= 0.174532F)
          {
              this.StandPose();
          }
          else
          {
              this.StandPose();
          }
      }
  }

}
