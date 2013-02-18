package mod.fossil.common;

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import mod.fossil.client.FossilCfgLoader;
import mod.fossil.client.FossilGuiHandler;
import mod.fossil.client.FossilMessageHandler;
import mod.fossil.client.FossilOptions;
import mod.fossil.common.*;
import mod.fossil.common.blocks.BlockFern;
import mod.fossil.common.blocks.BlockFossil;
import mod.fossil.common.blocks.BlockFossilSkull;
import mod.fossil.common.blocks.BlockIcedStone;
import mod.fossil.common.blocks.BlockPermafrost;
import mod.fossil.common.entity.EntityAncientJavelin;
import mod.fossil.common.entity.EntityDinoEgg;
import mod.fossil.common.entity.EntityJavelin;
import mod.fossil.common.entity.EntityMLighting;
import mod.fossil.common.entity.EntityStoneboard;
import mod.fossil.common.entity.mob.EntityBones;
import mod.fossil.common.entity.mob.EntityBrachiosaurus;
import mod.fossil.common.entity.mob.EntityDinosaurce;
import mod.fossil.common.entity.mob.EntityFailuresaurus;
import mod.fossil.common.entity.mob.EntityFriendlyPigZombie;
import mod.fossil.common.entity.mob.EntityMammoth;
import mod.fossil.common.entity.mob.EntityMosasaurus;
import mod.fossil.common.entity.mob.EntityNautilus;
import mod.fossil.common.entity.mob.EntityPigBoss;
import mod.fossil.common.entity.mob.EntityPlesiosaur;
import mod.fossil.common.entity.mob.EntityPregnantCow;
import mod.fossil.common.entity.mob.EntityPregnantPig;
import mod.fossil.common.entity.mob.EntityPregnantSheep;
import mod.fossil.common.entity.mob.EntityPterosaur;
import mod.fossil.common.entity.mob.EntityRaptor;
import mod.fossil.common.entity.mob.EntitySaberCat;
import mod.fossil.common.entity.mob.EntityStegosaurus;
import mod.fossil.common.entity.mob.EntityTRex;
import mod.fossil.common.entity.mob.EntityTriceratops;
import mod.fossil.common.entity.mob.Entitydil;
import mod.fossil.common.fossilEnums.EnumAnimalType;
import mod.fossil.common.fossilEnums.EnumEmbyos;
import mod.fossil.common.fossilEnums.EnumDinoType;
import mod.fossil.common.fossilEnums.EnumOrderType;
import mod.fossil.common.gens.FossilGenerator;
import mod.fossil.common.gens.WorldGenShipWreck;
import mod.fossil.common.guiBlocks.BlockAnalyzer;
import mod.fossil.common.guiBlocks.BlockCultivate;
import mod.fossil.common.guiBlocks.BlockDrum;
import mod.fossil.common.guiBlocks.BlockFeeder;
import mod.fossil.common.guiBlocks.BlockWorktable;
import mod.fossil.common.guiBlocks.BlockTimeMachine;
import mod.fossil.common.guiBlocks.TileEntityAnalyzer;
import mod.fossil.common.guiBlocks.TileEntityCultivate;
import mod.fossil.common.guiBlocks.TileEntityDrum;
import mod.fossil.common.guiBlocks.TileEntityFeeder;
import mod.fossil.common.guiBlocks.TileEntityWorktable;
import mod.fossil.common.items.ItemAncientEgg;
import mod.fossil.common.items.ItemAncientsword;
import mod.fossil.common.items.ItemBioFossil;
import mod.fossil.common.items.ItemBrokenHelmet;
import mod.fossil.common.items.ItemBrokenSword;
import mod.fossil.common.items.ItemChickenSoup;
import mod.fossil.common.items.ItemDNA;
import mod.fossil.common.items.ItemDinoMeat;
import mod.fossil.common.items.ItemEmbryoSyringe;
import mod.fossil.common.items.ItemFernSeed;
import mod.fossil.common.items.ItemGen;
import mod.fossil.common.items.ItemIcedMeat;
import mod.fossil.common.items.ItemJavelin;
import mod.fossil.common.items.ItemMagicConch;
import mod.fossil.common.items.ItemNonDinoDNA;
import mod.fossil.common.items.ItemRelic;
import mod.fossil.common.items.ItemStoneBoard;
import mod.fossil.common.items.ItemWhip;
import mod.fossil.common.items.forgeItems.ForgeItem;
import mod.fossil.common.items.forgeItems.ForgeItemAxe;
import mod.fossil.common.items.forgeItems.ForgeItemArmor;
import mod.fossil.common.items.forgeItems.ForgeItemFood;
import mod.fossil.common.items.forgeItems.ForgeItemHoe;
import mod.fossil.common.items.forgeItems.ForgeItemPickaxe;
import mod.fossil.common.items.forgeItems.ForgeItemSpade;
import mod.fossil.common.items.forgeItems.ForgeItemSword;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.stats.Achievement;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IChatListener;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(
        modid = "Fossil",
        name = "Fossil/Archeology",
        version = "v7.2.0a Unofficial"
)
@NetworkMod(
        clientSideRequired = true,
        serverSideRequired = false
)

public class Fossil 
{
	@SidedProxy(clientSide = "mod.fossil.client.ClientProxy", serverSide = "mod.fossil.common.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance("Fossil")
	public static Fossil instance;
	
	public static FossilGuiHandler GH = new FossilGuiHandler();
	public static Properties LangProps = new Properties();
	public static int blockRendererID = 0;
	
	private static final File Langdir = new File("/Fossillang/");
    public static final String DEFAULT_LANG = "en_US";
    public static String LastLangSetting = "en_US";
	private static File Langfile = new File(Langdir, LastLangSetting + ".lang");
	public static IChatListener messagerHandler = new FossilMessageHandler();
	
	/*
	 * If DebugMode = true
	 * HatchTime is set to 1
	 */
	public static boolean DebugMode = false;
	public static final double MESSAGE_DISTANCE = 25.0D;
	
    private static int[] blockIDs = new int[] {1137, 1138, 1139, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1151, 1152, 1153};
    private static String[] BlockName = new String[] {"block Fossil", "block Skull", "block SkullLantern", "block analyzerIdle", "block analyzerActive", "block cultivateIdle", "block cultivateActive", "block worktableIdle", "block worktableActive", "block fern", "block fernUpper", "block Drum", "block FeederIdle", "block FeederActive", "Block Permafrost", "Block IcedStone"};
    private static int[] ItemIDs = new int[] {600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636};
    private static String[] ItemName = new String[] {"Item biofossil", "Item relic", "Item stoneboard", "Item DNA", "Item Ancientegg", "Item AncientSword", "Item BrokenSword", "Item FernSeed", "Item AncientHelmet", "Item BrokenHelmet", "Item SkullStick", "Item Gen", "Item GenAxe", "Item GenPickaxe", "Item GenSword", "Item GenHoe", "Item GenShovel", "Item DinoPedia", "Item TRexTooth", "Item ToothDagger", "Item RawChickenSoup", "Item ChickenEss", "Item EmptyShell", "Item Magic conch", "Item SJL", "Item RawDinoMeat", "Item CookedDinoMeat", "Item EmbyoSyringe", "Item AnimalDNA", "Item IcedMeat", "Item WoodJavelin", "Item StonrJavelin", "Item IronJavelin", "Item GoldJavelin", "Item DiamondJavelin", "Item AncientJavelin", "Item Whip"};
    public static int[] fernPics = new int[] {38, 39, 40, 41, 42, 43, 26, 27, 71, 72, 73, 74, 58};
    public static final int MultiCount = EnumDinoType.values().length * 3 + EnumAnimalType.values().length * 2 - 1 + 2;
    public static ItemStack[] SingleTotalList;
    public static ItemStack[] MultiTotalList = new ItemStack[MultiCount];
    
    public static Achievement pigbossOnEarth = (new Achievement(18000, "PigbossOnEarth", 0, 0, new ItemStack(Item.dyePowder, 1, 4), (Achievement)null)).registerAchievement();
    public static AchievementPage selfArcPage = new AchievementPage("FOSSIL / ARCHEOLOGY", new Achievement[] {pigbossOnEarth});
    
	//Blocks
	public static Block blockFossil;
	public static Block blockSkull;
	public static Block skullLantern;
	public static Block blockanalyzerIdle;
	public static Block blockanalyzerActive;
	public static Block blockcultivateIdle;
	public static Block blockcultivateActive;
	public static Block blockworktableIdle;
	public static Block blockworktableActive;
	public static Block blockTimeMachine;
	public static Block ferns;
	public static Block fernUpper;
	public static Block drum;
	public static Block feederIdle;
	public static Block feederActive;
	public static Block blockPermafrost;
	public static Block blockIcedStone;
	
    //Items
    public static Item biofossil;
    public static Item relic;
    public static Item stoneboard;
    public static Item dna;
    public static Item ancientegg;
    public static Item ancientSword;
    public static Item brokenSword;
    public static Item fernSeed;
    public static Item ancienthelmet;
    public static Item brokenhelmet;
    public static Item skullStick;
    public static Item gen;
    public static Item genAxe;
    public static Item genPickaxe;
    public static Item genSword;
    public static Item genHoe;
    public static Item genShovel;
    public static Item dinoPedia;
    public static Item chickenSoup;
    public static Item chickenEss;
    public static Item emptyShell;
    public static Item sjl;
    public static Item magicConch;
    public static Item rawDinoMeat;
    public static Item cookedDinoMeat;
    public static Item embyoSyringe;
    public static Item animalDNA;
    public static Item icedMeat;
    public static Item woodjavelin;
    public static Item stonejavelin;
    public static Item ironjavelin;
    public static Item goldjavelin;
    public static Item diamondjavelin;
    public static Item ancientJavelin;
    public static Item whip;
	
	
    @cpw.mods.fml.common.Mod.PreInit
	public void PreInit(FMLPreInitializationEvent event)
	{
		proxy.registerSounds();
	}
	
	@cpw.mods.fml.common.Mod.Init
	public void Init(FMLInitializationEvent event)
	{
		//fillCreativeList();
		this.forgeHarvestLevelSetUp();
		
		//Blocks
		skullLantern = new BlockFossilSkull(3000, 0, true).setHardness(1.0F).setLightValue(0.9375F).setStepSound(Block.soundStoneFootstep).setBlockName("SkullLantern").setCreativeTab(CreativeTabs.tabBlock);
        blockanalyzerIdle = new BlockAnalyzer(3001, false).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setBlockName("analyzerIdle").setCreativeTab(CreativeTabs.tabDecorations);
        blockanalyzerActive = new BlockAnalyzer(3002, true).setLightValue(0.9375F).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setBlockName("analyzerActive");
        blockcultivateIdle = new BlockCultivate(3003, false).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("cultivateIdle").setCreativeTab(CreativeTabs.tabDecorations);
        blockcultivateActive = new BlockCultivate(3005, true).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("cultivateActive");
        blockworktableIdle = new BlockWorktable(3006, false).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setBlockName("worktableIdle").setCreativeTab(CreativeTabs.tabDecorations);
        blockworktableActive = new BlockWorktable(3007, true).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setBlockName("worktableActive");
        feederIdle = new BlockFeeder(3008, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("Feeder").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
        feederActive = new BlockFeeder(3009, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("Feeder").setRequiresSelfNotify();
        ferns = new BlockFern(3010, 0, false).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setRequiresSelfNotify().setCreativeTab((CreativeTabs)null);
        fernUpper = new BlockFern(3011, 0, true).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setRequiresSelfNotify().setCreativeTab((CreativeTabs)null);
        drum = new BlockDrum(3012).setHardness(0.8F).setBlockName("drum").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
        blockPermafrost = new BlockPermafrost(3013, 5).setHardness(0.5F).setLightOpacity(3).setStepSound(Block.soundGrassFootstep).setBlockName("Permafrost").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabBlock);
        blockIcedStone = new BlockIcedStone(3014, 6).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("IcedStone").setRequiresSelfNotify();
        blockFossil = new BlockFossil(3015, 1).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("fossil").setCreativeTab(CreativeTabs.tabBlock);
        blockSkull = new BlockFossilSkull(3016, 0, false).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setBlockName("Skull").setCreativeTab(CreativeTabs.tabBlock);
        blockTimeMachine = new BlockTimeMachine(3017, 0, Material.glass).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("BlockTimeMachine").setCreativeTab(CreativeTabs.tabDecorations);
        
      //Items
		biofossil = new ItemBioFossil(12000).setIconIndex(38).setItemName("biofossil").setCreativeTab(CreativeTabs.tabMisc);
		relic = new ItemRelic(12001).setIconIndex(39).setItemName("relic").setCreativeTab(CreativeTabs.tabMisc);
		stoneboard = new ItemStoneBoard(12002).setIconIndex(44).setItemName("stoneboard").setCreativeTab(CreativeTabs.tabDecorations);
		dna = new ItemDNA(12003).setIconIndex(0).setItemName("DNA").setCreativeTab(CreativeTabs.tabMaterials);
		ancientegg = new ItemAncientEgg(12004).setIconIndex(0).setItemName("TriceratopsEgg").setCreativeTab(CreativeTabs.tabMaterials);
		ancientSword = new ItemAncientsword(12005).setIconIndex(1).setItemName("ancientsword").setCreativeTab(CreativeTabs.tabCombat);
		brokenSword = new ItemBrokenSword(12006).setIconIndex(0).setItemName("Brokensword").setCreativeTab(CreativeTabs.tabMaterials);
		fernSeed = new ItemFernSeed(12007, ferns.blockID).setIconIndex(40).setItemName("FernSeed").setCreativeTab(CreativeTabs.tabMaterials);
		
		ancienthelmet = new ForgeItemArmor(12034, EnumArmorMaterial.IRON, 3, 0).setItemName("ancientHelmet").setCreativeTab(CreativeTabs.tabCombat);

		brokenhelmet = new ItemBrokenHelmet(12008).setIconIndex(2).setItemName("BrokenHelmet").setCreativeTab(CreativeTabs.tabMaterials);
		skullStick = new ForgeItem(12009).setIconIndex(5).setItemName("SkullStick").setCreativeTab(CreativeTabs.tabMisc);
		gen = new ItemGen(12010).setIconIndex(4).setItemName("Gen").setCreativeTab(CreativeTabs.tabMisc);
		genAxe = new ForgeItemAxe(12011, EnumToolMaterial.EMERALD).setIconIndex(19).setItemName("GenAxe");
		genPickaxe = new ForgeItemPickaxe(12012, EnumToolMaterial.EMERALD).setIconIndex(18).setItemName("GenPickaxe");
		genSword = new ForgeItemSword(12013, EnumToolMaterial.EMERALD).setIconIndex(16).setItemName("GenSword");
		genHoe = new ForgeItemHoe(12014, EnumToolMaterial.EMERALD).setIconIndex(20).setItemName("GenHoe");
		genShovel = new ForgeItemSpade(12015, EnumToolMaterial.EMERALD).setIconIndex(17).setItemName("GenShovel");
		dinoPedia = new ForgeItem(12016).setIconIndex(53).setItemName("dinopedia").setCreativeTab(CreativeTabs.tabTools);
		chickenSoup = new ItemChickenSoup(12017).setIconIndex(58).setItemName("ChickenSoup").setMaxStackSize(1).setContainerItem(Item.bucketEmpty).setCreativeTab(CreativeTabs.tabFood);
		chickenEss = new ForgeItemFood(12018, 10, 0.0F, false).setIconIndex(59).setItemName("ChickenEss").setContainerItem(Item.glassBottle).setCreativeTab(CreativeTabs.tabFood);
		emptyShell = new ForgeItem(12019).setIconIndex(42).setItemName("EmptyShell").setCreativeTab(CreativeTabs.tabMisc);
		sjl = new ForgeItemFood(12020, 8, 2.0F, false).setIconIndex(43).setItemName("SioChiuLe").setCreativeTab(CreativeTabs.tabFood);
		magicConch = new ItemMagicConch(12021).setIconIndex(21).setItemName("MagicConch").setCreativeTab(CreativeTabs.tabTools);
		rawDinoMeat = new ItemDinoMeat(12022, 3, 0.3F, true).setIconIndex(0).setItemName("DinoMeat").setCreativeTab(CreativeTabs.tabFood);
		cookedDinoMeat = new ForgeItemFood(12023, 8, 0.8F, true).setIconIndex(56).setItemName("CookedDinoMeat").setCreativeTab(CreativeTabs.tabFood);
		embyoSyringe = new ItemEmbryoSyringe(12024).setIconIndex(0).setItemName("EmbryoSyringe").setCreativeTab(CreativeTabs.tabMisc);
		animalDNA = new ItemNonDinoDNA(12025).setIconIndex(0).setItemName("AnimalDNA").setCreativeTab(CreativeTabs.tabMisc);
		icedMeat = new ItemIcedMeat(12026, EnumToolMaterial.EMERALD).setIconIndex(57).setItemName("IcedMeat").setCreativeTab(CreativeTabs.tabCombat);
		woodjavelin = new ItemJavelin(12027, EnumToolMaterial.WOOD).setIconIndex(0).setItemName("WoodJavelin").setCreativeTab(CreativeTabs.tabCombat);
		stonejavelin = new ItemJavelin(12028, EnumToolMaterial.STONE).setIconIndex(0).setItemName("StoneJavelin").setCreativeTab(CreativeTabs.tabCombat);
		ironjavelin = new ItemJavelin(12029, EnumToolMaterial.IRON).setIconIndex(0).setItemName("IronJavelin").setCreativeTab(CreativeTabs.tabCombat);
		goldjavelin = new ItemJavelin(12030, EnumToolMaterial.GOLD).setIconIndex(0).setItemName("GoldJavelin").setCreativeTab(CreativeTabs.tabCombat);
		diamondjavelin = new ItemJavelin(12031, EnumToolMaterial.EMERALD).setIconIndex(0).setItemName("DiamondJavelin").setCreativeTab(CreativeTabs.tabCombat);
		ancientJavelin = new ItemJavelin(12032, EnumToolMaterial.IRON).setItemName("AncientJavelin").setCreativeTab(CreativeTabs.tabCombat);
		whip = new ItemWhip(12033).setIconIndex(0).setItemName("FossilWhip").setCreativeTab(CreativeTabs.tabTools);
        
		
		GameRegistry.registerBlock(blockFossil, "fossil_blockFossil");
		GameRegistry.registerBlock(blockSkull, "fossil_blockSkull");
	 	GameRegistry.registerBlock(skullLantern, "fossil_SkullLantern");
		GameRegistry.registerBlock(blockanalyzerIdle, "fossil_blockanalyzerIdle");
		GameRegistry.registerBlock(blockanalyzerActive, "fossil_blockanalyzerActive");
		GameRegistry.registerBlock(blockcultivateIdle, "fossil_blockcultivateIdle");
		GameRegistry.registerBlock(blockcultivateActive, "fossil_blockcultivateActive");
		GameRegistry.registerBlock(blockworktableIdle, "fossil_blockworktableIdle");
		GameRegistry.registerBlock(blockworktableActive, "fossil_blockworktableActive");
		GameRegistry.registerBlock(ferns, "fossil_Ferns");
		GameRegistry.registerBlock(fernUpper, "fossil_FernUpper");
		GameRegistry.registerBlock(drum, "fossil_Dump");
		GameRegistry.registerBlock(feederIdle, "fossil_FeederIdle");
		GameRegistry.registerBlock(feederActive, "fossil_FeederActive");
		GameRegistry.registerBlock(blockPermafrost, "fossil_blockPermafrost");
		GameRegistry.registerBlock(blockIcedStone, "fossil_blockIcedStone");
		GameRegistry.registerBlock(blockTimeMachine, "blockTimeMachine");

        LanguageRegistry.addName(blockFossil, "blockFossil");
        LanguageRegistry.addName(blockSkull, "blockSkull");
        LanguageRegistry.addName(skullLantern, "skullLantern");
        LanguageRegistry.addName(biofossil, "biofossil");
        LanguageRegistry.addName(relic, "relic");
        LanguageRegistry.addName(stoneboard, "stoneboard");
        LanguageRegistry.addName(blockanalyzerIdle, "blockanalyzerIdle");
        LanguageRegistry.addName(blockcultivateIdle, "blockcultivateIdle");
        LanguageRegistry.addName(blockworktableIdle, "blockworktableIdle");
        LanguageRegistry.addName(brokenSword, "brokenSword");
        LanguageRegistry.addName(ancientSword, "ancientSword");
        LanguageRegistry.addName(fernSeed, "fernSeed");
        LanguageRegistry.addName(brokenhelmet, "brokenhelmet");
        LanguageRegistry.addName(ancienthelmet, "ancienthelmet");
        LanguageRegistry.addName(skullStick, "skullStick");
        LanguageRegistry.addName(drum, "drum");
        LanguageRegistry.addName(feederIdle, "feederIdle");
        LanguageRegistry.addName(gen, "gen");
        LanguageRegistry.addName(genAxe, "genAxe");
        LanguageRegistry.addName(genPickaxe, "genPickaxe");
        LanguageRegistry.addName(genSword, "genSword");
        LanguageRegistry.addName(genHoe, "genHoe");
        LanguageRegistry.addName(genShovel, "genShovel");
        LanguageRegistry.addName(dinoPedia, "dinoPedia");
        LanguageRegistry.addName(new ItemStack(chickenSoup, 1, 0), "chickenSoup");
        LanguageRegistry.addName(new ItemStack(chickenSoup, 1, 1), "chickenSoup");
        LanguageRegistry.addName(chickenEss, "chickenEss");
        LanguageRegistry.addName(emptyShell, "emptyShell");
        LanguageRegistry.addName(sjl, "sjl");
        LanguageRegistry.addName(blockPermafrost, "blockPermafrost");
        LanguageRegistry.addName(blockIcedStone, "blockIcedStone");
        LanguageRegistry.addName(icedMeat, "icedMeat");
        LanguageRegistry.addName(woodjavelin, "woodjavelin");
        LanguageRegistry.addName(stonejavelin, "stonejavelin");
        LanguageRegistry.addName(ironjavelin, "ironjavelin");
        LanguageRegistry.addName(diamondjavelin, "diamondjavelin");
        LanguageRegistry.addName(goldjavelin, "goldjavelin");
        LanguageRegistry.addName(ancientJavelin, "ancientJavelin");
        LanguageRegistry.addName(whip, "whip");
        LanguageRegistry.addName(blockTimeMachine, "Time Machine");
        

		GameRegistry.addRecipe(new ItemStack(skullLantern, 1), new Object[] {"X", "Y", 'X', blockSkull, 'Y', Block.torchWood});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 5, 15), new Object[] {"X", 'X', blockSkull});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 5, 15), new Object[] {"X", 'X', skullLantern});
		GameRegistry.addRecipe(new ItemStack(blockcultivateIdle, 1), new Object[] {"XYX", "XWX", "ZZZ", 'X', Block.glass, 'Y', new ItemStack(Item.dyePowder, 1, 2), 'W', Item.bucketWater, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(blockanalyzerIdle, 1), new Object[] {"XYX", "XWX", 'X', Item.ingotIron, 'Y', relic, 'W', biofossil});
		GameRegistry.addRecipe(new ItemStack(blockworktableIdle, 1), new Object[] {"X", "Y", 'X', Item.paper, 'Y', Block.workbench});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 8)});
		GameRegistry.addRecipe(new ItemStack(skullStick, 1), new Object[] {"X", "Y", 'X', blockSkull, 'Y', Item.stick});
		GameRegistry.addRecipe(new ItemStack(drum, 1), new Object[] {"ZZZ", "XYX", "XXX", 'X', Block.planks, 'Y', Item.redstone, 'Z', Item.leather});
		GameRegistry.addRecipe(new ItemStack(feederIdle, 1), new Object[] {"XYX", "ZAB", "BBB", 'X', Item.ingotIron, 'Y', Block.glass, 'Z', Block.stoneButton, 'A', Item.bucketEmpty, 'B', Block.stone});
		GameRegistry.addShapelessRecipe(new ItemStack(genAxe), new Object[] {Item.axeWood, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genAxe), new Object[] {Item.axeStone, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genAxe), new Object[] {Item.axeSteel, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genAxe), new Object[] {Item.axeGold, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genAxe), new Object[] {Item.axeDiamond, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genPickaxe), new Object[] {Item.pickaxeWood, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genPickaxe), new Object[] {Item.pickaxeStone, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genPickaxe), new Object[] {Item.pickaxeSteel, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genPickaxe), new Object[] {Item.pickaxeGold, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genPickaxe), new Object[] {Item.pickaxeDiamond, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genHoe), new Object[] {Item.hoeWood, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genHoe), new Object[] {Item.hoeStone, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genHoe), new Object[] {Item.hoeSteel, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genHoe), new Object[] {Item.hoeGold, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genHoe), new Object[] {Item.hoeDiamond, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genSword), new Object[] {Item.swordWood, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genSword), new Object[] {Item.swordStone, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genSword), new Object[] {Item.swordSteel, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genSword), new Object[] {Item.swordGold, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genSword), new Object[] {Item.swordDiamond, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genShovel), new Object[] {Item.shovelWood, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genShovel), new Object[] {Item.shovelStone, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genShovel), new Object[] {Item.shovelSteel, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genShovel), new Object[] {Item.shovelGold, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(genShovel), new Object[] {Item.shovelDiamond, gen});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 2)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 6)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 8)});
		GameRegistry.addShapelessRecipe(new ItemStack(chickenSoup, 1, 0), new Object[] {Item.bucketEmpty, Item.chickenRaw});
		GameRegistry.addShapelessRecipe(new ItemStack(magicConch, 1, 1), new Object[] {new ItemStack(magicConch, 1, 0)});
		GameRegistry.addShapelessRecipe(new ItemStack(magicConch, 1, 2), new Object[] {new ItemStack(magicConch, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(magicConch, 1, 0), new Object[] {new ItemStack(magicConch, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(chickenEss, 8), new Object[] {"XXX", "XYX", "XXX", 'X', Item.glassBottle, 'Y', new ItemStack(chickenSoup, 1, 1)});
		
        GameRegistry.addSmelting(chickenSoup.itemID, new ItemStack(chickenSoup, 1, 1), 3.0F);
        GameRegistry.addSmelting(rawDinoMeat.itemID, new ItemStack(cookedDinoMeat), 3.0F);
        GameRegistry.addSmelting(icedMeat.itemID, new ItemStack(Item.beefCooked), 3.0F);
		
		EntityRegistry.registerModEntity(EntityStoneboard.class, "StoneBoard", 1, this, 250, 5, false);
		EntityRegistry.registerModEntity(EntityJavelin.class, "Javelin", 2, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityAncientJavelin.class, "AncientJavelin", 3, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityMLighting.class, "FriendlyLighting", 4, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityFailuresaurus.class, "Failuresaurus", 5, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityBones.class, "Bones", 6, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityNautilus.class, "Nautilus", 7, this, 250, 5, true);
		EntityRegistry.addSpawn(EntityNautilus.class, 5, 4, 14, EnumCreatureType.waterCreature, new BiomeGenBase[] {BiomeGenBase.beach});
		EntityRegistry.registerModEntity(EntityDinoEgg.class, "DinoEgg", 8, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityRaptor.class, "Raptor", 9, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityTriceratops.class, "Triceratops", 10, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityTRex.class, "Tyrannosaurus", 11, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityFriendlyPigZombie.class, "FriendlyPigZombie", 12, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPigBoss.class, "PigBoss", 13, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPterosaur.class, "Pterosaur", 14, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPlesiosaur.class, "Plesiosaur", 15, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityMosasaurus.class, "Mosasaurus", 16, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityStegosaurus.class, "Stegosaurus", 17, this, 250, 5, true);
		EntityRegistry.registerModEntity(Entitydil.class, "Utahraptor", 18, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPregnantSheep.class, "PregnantSheep", 19, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPregnantCow.class, "PregnantCow", 20, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityPregnantPig.class, "PregnantPig", 21, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntitySaberCat.class, "SaberCat", 22, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityBrachiosaurus.class, "Brachiosaurus", 23, this, 250, 5, true);
		EntityRegistry.registerModEntity(EntityMammoth.class, "Mammoth", 24, this, 250, 5, true);

		GameRegistry.registerWorldGenerator(new FossilGenerator());
		//GameRegistry.registerWorldGenerator(new WorldGenAcademy());
		GameRegistry.registerWorldGenerator(new WorldGenShipWreck());
		//GameRegistry.registerWorldGenerator(new WorldGenWeaponShopA());

		NetworkRegistry.instance().registerChatListener(messagerHandler);
		NetworkRegistry.instance().registerGuiHandler(this, GH);

		GameRegistry.registerTileEntity(TileEntityCultivate.class, "Cultivate");
		GameRegistry.registerTileEntity(TileEntityAnalyzer.class, "Analyzer");
		GameRegistry.registerTileEntity(TileEntityWorktable.class, "Worktable");
		GameRegistry.registerTileEntity(TileEntityDrum.class, "Drum");
		GameRegistry.registerTileEntity(TileEntityFeeder.class, "Feeder");
		
		proxy.registerTileEntitySpecialRenderer();
        proxy.registerRenderThings();
        
	}
	
	public static void fillCreativeList()
    {
        int var2 = 0;
        int var0;

        for (var0 = 0; var0 < EnumDinoType.values().length; ++var0)
        {
            MultiTotalList[var2++] = new ItemStack(dna, 1, var0);
        }

        for (var0 = 0; var0 < EnumDinoType.values().length; ++var0)
        {
            MultiTotalList[var2++] = new ItemStack(ancientegg, 1, var0);
        }

        for (var0 = 0; var0 < EnumDinoType.values().length; ++var0)
        {
            MultiTotalList[var2++] = new ItemStack(rawDinoMeat, 1, var0);
        }

        int var1;

        for (var1 = 0; var1 < EnumAnimalType.values().length; ++var1)
        {
            MultiTotalList[var2++] = new ItemStack(animalDNA, 1, var1);
        }

        for (var1 = 0; var1 < EnumEmbyos.values().length; ++var1)
        {
            MultiTotalList[var2++] = new ItemStack(embyoSyringe, 1, var1);
        }

        for (var1 = 0; var1 < 2; ++var1)
        {
            MultiTotalList[var2++] = new ItemStack(chickenSoup, 1, var1);
        }

        SingleTotalList = new ItemStack[] {new ItemStack(blockFossil, 1, 0), new ItemStack(blockSkull, 1, 0), new ItemStack(skullLantern, 1, 0), new ItemStack(blockanalyzerIdle, 1, 0), new ItemStack(blockcultivateIdle, 1, 0), new ItemStack(blockworktableIdle, 1, 0), new ItemStack(ferns, 1, 0), new ItemStack(drum, 1, 0), new ItemStack(feederIdle, 1, 0), new ItemStack(blockPermafrost, 1, 0), new ItemStack(blockIcedStone, 1, 0)};
    }
	
    private void forgeHarvestLevelSetUp()
    {
        MinecraftForge.setBlockHarvestLevel(blockFossil, 0, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockPermafrost, 0, "shovel", 2);
        MinecraftForge.setBlockHarvestLevel(blockIcedStone, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockIcedStone, 1, "pickaxe", 1);
    }
	
	public static String GetLangTextByKey(String var0)
	{
			String var1 = LangProps.getProperty(var0, " ");
			return var1;
	}

	public static void ShowMessage(String var0, EntityPlayer var1)
	{
			if (var1 != null)
			{
    			var1.addChatMessage(var0);
			}
	}

	public static void DebugMessage(String var0)
	{
			 if (DebugMode)
		{
    			System.out.println(var0);
		}
	}

	public static int EnumToInt(EnumOrderType var0)
	{
			return var0.ToInt();
	}

	public static void UpdateLangProp() throws IOException
	{
			String var0 = LastLangSetting;

		try
		{
    			Langfile = new File(Fossil.class.getResource("/Fossillang/" + LastLangSetting + ".lang").getFile());
		}
		catch (Throwable var5)
		{
    			var0 = "en_US";
	 	}
		finally
		{
    			UTF8Reader(LangProps, var0);
		}
	}

	private static void UTF8Reader(Properties var0, String var1) throws IOException
	{
		BufferedReader var2 = new BufferedReader(new InputStreamReader(Fossil.class.getResourceAsStream("/Fossillang/" + var1 + ".lang"), "UTF-8"));

		for (String var3 = var2.readLine(); var3 != null; var3 = var2.readLine())
		{
    			var3 = var3.trim();

    			if (!var3.startsWith("#"))
    		{
        			String[] var4 = var3.split("=");

        			if (var4 != null && var4.length == 2)
        		{
           	 var0.setProperty(var4[0], var4[1]);
        		}
    		}
		}
	}

	private void SetupOptions()
	{
		boolean var1 = false;

		try
		{
			Properties var2 = FossilCfgLoader.loadOptionConfig();
			Properties var3 = new Properties();

			if (var2 != null)
			{
				String var4;

				if (var2.containsKey("AnuSpawn") && !var1)
				{
					var4 = var2.getProperty("AnuSpawn");

					if (FossilOptions.isNegtiveWord(var4))
					{
						FossilOptions.ShouldAnuSpawn = false;
					}
				}

				if (var2.containsKey("SpawnShipWrecks") && !var1)
				{
					var4 = var2.getProperty("SpawnShipWrecks");

					if (FossilOptions.isNegtiveWord(var4))
					{
						FossilOptions.SpawnShipwrecks = false;
					}
				}

        if (var2.containsKey("SpawnWeaponShop") && !var1)
        {
            var4 = var2.getProperty("SpawnWeaponShop");

            if (FossilOptions.isNegtiveWord(var4))
            {
                FossilOptions.SpawnWeaponShop = false;
            }
        }

        if (var2.containsKey("SpawnAcademy") && !var1)
        {
            var4 = var2.getProperty("SpawnAcademy");

            if (FossilOptions.isNegtiveWord(var4))
            {
                FossilOptions.SpawnAcademy = false;
            }
        }

        if (var2.containsKey("DinoGrows") && !var1)
        {
            var4 = var2.getProperty("DinoGrows");

            if (FossilOptions.isNegtiveWord(var4))
            {
                FossilOptions.DinoGrows = false;
            }
        }

        if (var2.containsKey("DinoHunger") && !var1)
        {
            var4 = var2.getProperty("DinoHunger");

            if (FossilOptions.isNegtiveWord(var4))
            {
                FossilOptions.DinoHunger = false;
            }
        }

        if (var2.containsKey("TRexBreakingBlocks") && !var1)
        {
            var4 = var2.getProperty("TRexBreakingBlocks");

            if (FossilOptions.isNegtiveWord(var4))
            {
                FossilOptions.TRexBreakingBlocks = false;
            }
        }

        if (var2.containsKey("BraBreakingBlocks") && !var1)
        {
            var4 = var2.getProperty("BraBreakingBlocks");

            if (FossilOptions.isNegtiveWord(var4))
            {
                FossilOptions.BraBreakingBlocks = false;
            }
        }
    }

    var3.setProperty("AnuSpawn", FossilOptions.ShouldAnuSpawn ? "true" : "false");
    var3.setProperty("SpawnShipWrecks", FossilOptions.SpawnShipwrecks ? "true" : "false");
    var3.setProperty("SpawnWeaponShop", FossilOptions.SpawnWeaponShop ? "true" : "false");
    var3.setProperty("SpawnAcademy", FossilOptions.SpawnAcademy ? "true" : "false");
    var3.setProperty("DinoGrows", FossilOptions.DinoGrows ? "true" : "false");
    var3.setProperty("DinoHunger", FossilOptions.DinoHunger ? "true" : "false");
    var3.setProperty("TRexBreakingBlocks", FossilOptions.TRexBreakingBlocks ? "true" : "false");
    var3.setProperty("BraBreakingBlocks", FossilOptions.BraBreakingBlocks ? "true" : "false");
    FossilCfgLoader.saveOptionConfig(var3);
    
		}
		catch (IOException var5)
		{
			;
		}
	}

	public static String GetEmbyoName(EnumEmbyos var0)
	{
		String var1 = "";

		switch (var0.ordinal())
		{
        	case 1:
        	case 2:
        	case 3:
            var1 = " " + StringTranslate.getInstance().translateNamedKey("entity." + var0.toString()).toString();
            break;

        	default:
            var1 = GetLangTextByKey("AnimalType." + var0.toString());
		}

		return var1;
	}
	
	@cpw.mods.fml.common.Mod.PostInit
	public void PostInit(FMLPostInitializationEvent event)
	{
		
	}
	
}
