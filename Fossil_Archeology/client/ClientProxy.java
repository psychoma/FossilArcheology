package mods.Fossil_Archeology.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import mods.Fossil_Archeology.CommonProxy;
import mods.Fossil_Archeology.entity.EntityAncientJavelin;
import mods.Fossil_Archeology.entity.EntityDinoEgg;
import mods.Fossil_Archeology.entity.EntityJavelin;
import mods.Fossil_Archeology.entity.EntityStoneboard;
import mods.Fossil_Archeology.entity.RenderDinoEgg;
import mods.Fossil_Archeology.entity.RenderJavelin;
import mods.Fossil_Archeology.entity.RenderStoneboard;
import mods.Fossil_Archeology.entity.mob.EntityBones;
import mods.Fossil_Archeology.entity.mob.EntityBrachiosaurus;
import mods.Fossil_Archeology.entity.mob.EntityFailuresaurus;
import mods.Fossil_Archeology.entity.mob.EntityFriendlyPigZombie;
import mods.Fossil_Archeology.entity.mob.EntityMammoth;
import mods.Fossil_Archeology.entity.mob.EntityMosasaurus;
import mods.Fossil_Archeology.entity.mob.EntityNautilus;
import mods.Fossil_Archeology.entity.mob.EntityPigBoss;
import mods.Fossil_Archeology.entity.mob.EntityPlesiosaur;
import mods.Fossil_Archeology.entity.mob.EntityPregnantPig;
import mods.Fossil_Archeology.entity.mob.EntityPterosaur;
import mods.Fossil_Archeology.entity.mob.EntityVelociraptor;
import mods.Fossil_Archeology.entity.mob.EntitySaberCat;
import mods.Fossil_Archeology.entity.mob.EntityStegosaurus;
import mods.Fossil_Archeology.entity.mob.EntityTRex;
import mods.Fossil_Archeology.entity.mob.EntityTriceratops;
import mods.Fossil_Archeology.entity.mob.EntityDilophosaurus;
import mods.Fossil_Archeology.entity.mob.ModelFailuresaurus;
import mods.Fossil_Archeology.entity.mob.ModelMammoth;
import mods.Fossil_Archeology.entity.mob.ModelNautilus;
import mods.Fossil_Archeology.entity.mob.ModelPigBoss;
import mods.Fossil_Archeology.entity.mob.ModelPterosaurGround;
import mods.Fossil_Archeology.entity.mob.ModelVelociraptor;
import mods.Fossil_Archeology.entity.mob.ModelSaberCat;
import mods.Fossil_Archeology.entity.mob.ModelStegosaurus;
import mods.Fossil_Archeology.entity.mob.ModelTRex;
import mods.Fossil_Archeology.entity.mob.ModelTriceratops;
import mods.Fossil_Archeology.entity.mob.ModelDilophosaurus;
import mods.Fossil_Archeology.entity.mob.RenderBrachiosaurus;
import mods.Fossil_Archeology.entity.mob.RenderFailuresaurus;
import mods.Fossil_Archeology.entity.mob.RenderMammoth;
import mods.Fossil_Archeology.entity.mob.RenderMosasaurus;
import mods.Fossil_Archeology.entity.mob.RenderNautilus;
import mods.Fossil_Archeology.entity.mob.RenderPigBoss;
import mods.Fossil_Archeology.entity.mob.RenderPlesiosaur;
import mods.Fossil_Archeology.entity.mob.RenderPterosaur;
import mods.Fossil_Archeology.entity.mob.RenderVelociraptor;
import mods.Fossil_Archeology.entity.mob.RenderSaberCat;
import mods.Fossil_Archeology.entity.mob.RenderStegosaurus;
import mods.Fossil_Archeology.entity.mob.RenderTRex;
import mods.Fossil_Archeology.entity.mob.RenderTriceratops;
import mods.Fossil_Archeology.entity.mob.RenderDilophosaurus;
import mods.Fossil_Archeology.guiBlocks.RenderTNClock;
import mods.Fossil_Archeology.guiBlocks.TileEntityTimeMachine;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderThings()
	{
		
		//MinecraftForgeClient.preloadTexture("/fossil/textures/Fos_terrian.png");
		//MinecraftForgeClient.preloadTexture("/fossil/textures/Fos_items.png");
		/*MinecraftForgeClient.preloadTexture("/fossil/textures/AncientJavelin.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Brachiosaurus.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/DiamondJavelin.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/dinoegg.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/DinoModelBrachiosaurus.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/DinoModelPlesiosaur.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/DinoModelPterosaur.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/DinoModelTriceratops.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/DNA4.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture1.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture2.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture3.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture4.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture5.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture6.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture7.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture8.png");
		MinecraftForgeClient.preloadTexture("Fossil_Archeology/textures/mob/eggTexture9.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/Failuresaurus.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/GoldJavelin.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/IronJavelin.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/MammothAdult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/MammothFur.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/MammothFurless.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/MammothYoung.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Mosasaurus.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Nautilus.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/PigBoss_Charging.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/PigBoss_r.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/PigBoss.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/PigBossCharged_r.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/Plesiosaur_adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/pr1.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Pterosaur.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Raptor_Adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Raptor_Baby.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/raptor_blue_adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/raptor_blue_Baby.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/raptor_green_adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/raptor_green_Baby.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Raptor_Tamed.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/RenderPassMosasaurus.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/SaberCat_Adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/SaberCat_Young.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Stegosaurus_Adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures//mobStegosaurus_Baby.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/StoneJavelin.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/T_mac_o.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/TextureAncientHalmet.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/TRex.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/TRex_Adult.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/TRexWeak.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/Tri_Hurt.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Adult_1.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Adult_2.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Adult_3.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Baby_1.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Baby_2.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Baby_3.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Tamed.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Teen_1.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Teen_2.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/Triceratops_Teen_3.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/UIAnalyzer.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/UICultivate.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/UIFeeder.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/UIPazzle.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/UtaAttack.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/mob/UtaCalm.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/WoodenJavelin.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/TMGui.png");
		MinecraftForgeClient.preloadTexture("/fossil/textures/TNClock.png");*/
		
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneboard.class, new RenderStoneboard());
		RenderingRegistry.registerEntityRenderingHandler(EntityTriceratops.class, new RenderTriceratops(new ModelTriceratops(), new ModelTriceratops(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVelociraptor.class, new RenderVelociraptor(new ModelVelociraptor(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTRex.class, new RenderTRex(new ModelTRex(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFailuresaurus.class, new RenderFailuresaurus(new ModelFailuresaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPigBoss.class, new RenderPigBoss(new ModelPigBoss(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFriendlyPigZombie.class, new RenderBiped(new ModelZombie(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPterosaur.class, new RenderPterosaur(new ModelPterosaurGround(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityNautilus.class, new RenderNautilus(new ModelNautilus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlesiosaur.class, new RenderPlesiosaur(0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMosasaurus.class, new RenderMosasaurus(0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStegosaurus.class, new RenderStegosaurus(new ModelStegosaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDinoEgg.class, new RenderDinoEgg(1.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPregnantPig.class, new RenderPig(new ModelPig(), new ModelPig(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityDilophosaurus.class, new RenderDilophosaurus(new ModelDilophosaurus(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySaberCat.class, new RenderSaberCat(new ModelSaberCat(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJavelin.class, new RenderJavelin());
		RenderingRegistry.registerEntityRenderingHandler(EntityAncientJavelin.class, new RenderJavelin());
		RenderingRegistry.registerEntityRenderingHandler(EntityBones.class, new RenderBiped(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBrachiosaurus.class, new RenderBrachiosaurus(0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMammoth.class, new RenderMammoth(new ModelMammoth(), 0.5F));

		RenderingRegistry.registerBlockHandler(new FossilBlockRenderHandler());
		
	}
	
	@Override
	public void registerTileEntitySpecialRenderer()
	{
    	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTimeMachine.class, new RenderTNClock());
	}
	
	@Override
	public void registerSounds()
	{
		MinecraftForge.EVENT_BUS.register(new DinoSoundHandler());
	}
	
}
