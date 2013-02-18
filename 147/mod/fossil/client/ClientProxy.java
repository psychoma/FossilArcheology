package mod.fossil.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import mod.fossil.common.CommonProxy;
import mod.fossil.common.entity.EntityAncientJavelin;
import mod.fossil.common.entity.EntityDinoEgg;
import mod.fossil.common.entity.EntityJavelin;
import mod.fossil.common.entity.EntityStoneboard;
import mod.fossil.common.entity.RenderDinoEgg;
import mod.fossil.common.entity.RenderJavelin;
import mod.fossil.common.entity.RenderStoneboard;
import mod.fossil.common.entity.mob.EntityBones;
import mod.fossil.common.entity.mob.EntityBrachiosaurus;
import mod.fossil.common.entity.mob.EntityFailuresaurus;
import mod.fossil.common.entity.mob.EntityFriendlyPigZombie;
import mod.fossil.common.entity.mob.EntityMammoth;
import mod.fossil.common.entity.mob.EntityMosasaurus;
import mod.fossil.common.entity.mob.EntityNautilus;
import mod.fossil.common.entity.mob.EntityPigBoss;
import mod.fossil.common.entity.mob.EntityPlesiosaur;
import mod.fossil.common.entity.mob.EntityPregnantPig;
import mod.fossil.common.entity.mob.EntityPterosaur;
import mod.fossil.common.entity.mob.EntityRaptor;
import mod.fossil.common.entity.mob.EntitySaberCat;
import mod.fossil.common.entity.mob.EntityStegosaurus;
import mod.fossil.common.entity.mob.EntityTRex;
import mod.fossil.common.entity.mob.EntityTriceratops;
import mod.fossil.common.entity.mob.Entitydil;
import mod.fossil.common.entity.mob.ModelFailuresaurus;
import mod.fossil.common.entity.mob.ModelMammoth;
import mod.fossil.common.entity.mob.ModelNautilus;
import mod.fossil.common.entity.mob.ModelPigBoss;
import mod.fossil.common.entity.mob.ModelPterosaurGround;
import mod.fossil.common.entity.mob.ModelRaptor;
import mod.fossil.common.entity.mob.ModelSaberCat;
import mod.fossil.common.entity.mob.ModelStegosaurus;
import mod.fossil.common.entity.mob.ModelTRex;
import mod.fossil.common.entity.mob.ModelTriceratops;
import mod.fossil.common.entity.mob.Modeldil;
import mod.fossil.common.entity.mob.RenderBrachiosaurus;
import mod.fossil.common.entity.mob.RenderFailuresaurus;
import mod.fossil.common.entity.mob.RenderMammoth;
import mod.fossil.common.entity.mob.RenderMosasaurus;
import mod.fossil.common.entity.mob.RenderNautilus;
import mod.fossil.common.entity.mob.RenderPigBoss;
import mod.fossil.common.entity.mob.RenderPlesiosaur;
import mod.fossil.common.entity.mob.RenderPterosaur;
import mod.fossil.common.entity.mob.RenderRaptor;
import mod.fossil.common.entity.mob.RenderSaberCat;
import mod.fossil.common.entity.mob.RenderStegosaurus;
import mod.fossil.common.entity.mob.RenderTRex;
import mod.fossil.common.entity.mob.RenderTriceratops;
import mod.fossil.common.entity.mob.Renderdil;
import mod.fossil.common.guiBlocks.RenderTNClock;
import mod.fossil.common.guiBlocks.TileEntityTimeMachine;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderThings()
	{
		
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Fos_terrian.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Fos_items.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/AncientJavelin.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Brachiosaurus.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/DiamondJavelin.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/dinoegg.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/DinoModelBrachiosaurus.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/DinoModelPlesiosaur.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/DinoModelPterosaur.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/DinoModelTriceratops.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/DNA4.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture1.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture2.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture3.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture4.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture5.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture6.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture7.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture8.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/eggTexture9.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Failuresaurus.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/GoldJavelin.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/IronJavelin.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/MammothAdult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/MammothFur.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/MammothFurless.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/MammothYoung.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Mosasaurus.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Nautilus.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/PigBoss_Charging.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/PigBoss_r.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/PigBoss.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/PigBossCharged_r.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Plesiosaur_adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/pr1.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Pterosaur.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Raptor_Adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Raptor_Baby.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/raptor_blue_adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/raptor_blue_Baby.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/raptor_green_adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/raptor_green_Baby.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Raptor_Tamed.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/RenderPassMosasaurus.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/SaberCat_Adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/SaberCat_Young.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Stegosaurus_Adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Stegosaurus_Baby.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/StoneJavelin.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/T_mac_o.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/TextureAncientHalmet.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/TRex.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/TRex_Adult.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/TRexWeak.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Tri_Hurt.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Adult_1.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Adult_2.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Adult_3.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Baby_1.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Baby_2.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Baby_3.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Tamed.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Teen_1.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Teen_2.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/Triceratops_Teen_3.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/UIAnalyzer.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/UICultivate.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/UIFeeder.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/UIPazzle.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/UtaAttack.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/UtaCalm.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/WoodenJavelin.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/TMGui.png");
		MinecraftForgeClient.preloadTexture("/mod/fossil/common/textures/TNClock.png");
		
		
		
		RenderingRegistry.registerEntityRenderingHandler(EntityStoneboard.class, new RenderStoneboard());
		RenderingRegistry.registerEntityRenderingHandler(EntityTriceratops.class, new RenderTriceratops(new ModelTriceratops(), new ModelTriceratops(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRaptor.class, new RenderRaptor(new ModelRaptor(), 0.5F));
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
		RenderingRegistry.registerEntityRenderingHandler(Entitydil.class, new Renderdil(new Modeldil(), 0.5F));
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
