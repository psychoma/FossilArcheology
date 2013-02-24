package mod.fossil.common;

import java.io.BufferedReader;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import mod.fossil.client.FossilCfgLoader;
import mod.fossil.client.FossilGuiHandler;
import mod.fossil.client.FossilMessageHandler;
import mod.fossil.client.FossilOptions;
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
import mod.fossil.common.fossilEnums.EnumDinoType;
import mod.fossil.common.fossilEnums.EnumEmbyos;
import mod.fossil.common.fossilEnums.EnumOrderType;
import mod.fossil.common.gens.FossilGenerator;
import mod.fossil.common.gens.WorldGenAcademy;
import mod.fossil.common.gens.WorldGenShipWreck;
import mod.fossil.common.gens.WorldGenWeaponShopA;
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
import mod.fossil.common.guiBlocks.TileEntityTimeMachine;
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
import net.minecraft.client.Minecraft;
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
import net.minecraftforge.common.Configuration;
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
	public static Object ToPedia;
	
	//private static final File Langdir = new File("/lang");
    //public static String LastLangSetting = "en_US";
	//private static File Langfile = new File(Langdir, LastLangSetting + ".lang");
	public static IChatListener messagerHandler = new FossilMessageHandler();
	
	/*
	 * If DebugMode = true
	 * HatchTime is set to 1
	 */
	public static boolean DebugMode = true;
	public static final double MESSAGE_DISTANCE = 25.0D;
	
    //private static int[] blockIDs = new int[] {1137, 1138, 1139, 1140, 1141, 1142, 1143, 1144, 1145, 1146, 1147, 1148, 1149, 1151, 1152, 1153};
    //private static String[] BlockName = new String[] {"block Fossil", "block Skull", "block SkullLantern", "block analyzerIdle", "block analyzerActive", "block cultivateIdle", "block cultivateActive", "block worktableIdle", "block worktableActive", "block fern", "block fernUpper", "block Drum", "block FeederIdle", "block FeederActive", "Block Permafrost", "Block IcedStone"};
    //private static int[] ItemIDs = new int[] {600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636};
    //private static String[] ItemName = new String[] {"Item biofossil", "Item relic", "Item stoneboard", "Item DNA", "Item Ancientegg", "Item AncientSword", "Item BrokenSword", "Item FernSeed", "Item AncientHelmet", "Item BrokenHelmet", "Item SkullStick", "Item Gen", "Item GenAxe", "Item GenPickaxe", "Item GenSword", "Item GenHoe", "Item GenShovel", "Item DinoPedia", "Item TRexTooth", "Item ToothDagger", "Item RawChickenSoup", "Item ChickenEss", "Item EmptyShell", "Item Magic conch", "Item SJL", "Item RawDinoMeat", "Item CookedDinoMeat", "Item EmbyoSyringe", "Item AnimalDNA", "Item IcedMeat", "Item WoodJavelin", "Item StonrJavelin", "Item IronJavelin", "Item GoldJavelin", "Item DiamondJavelin", "Item AncientJavelin", "Item Whip"};
    public static int[] fernPics = new int[] {38, 39, 40, 41, 42, 43, 26, 27, 71, 72, 73, 74, 58};
    //public static final int MultiCount = EnumDinoType.values().length * 3 + EnumAnimalType.values().length * 2 - 1 + 2;
    //public static ItemStack[] SingleTotalList;
    //public static ItemStack[] MultiTotalList = new ItemStack[MultiCount];
    
    public static CreativeTabs tabFBlocks = new TabFBlocks(CreativeTabs.getNextID(), "Fossil Blocks");
	public static CreativeTabs tabFItems = new TabFItems(CreativeTabs.getNextID(), "Fossil Items");
	public static CreativeTabs tabFFood = new TabFFood(CreativeTabs.getNextID(), "Fossil Food");
	public static CreativeTabs tabFCombat = new TabFCombat(CreativeTabs.getNextID(), "Fossil Combat");
	//public static CreativeTabs tabFArmor = new TabFArmor(CreativeTabs.getNextID(), "Fossil Armor");
	public static CreativeTabs tabFTools = new TabFTools(CreativeTabs.getNextID(), "Fossil Deco");
	public static CreativeTabs tabFMaterial = new TabFMaterial(CreativeTabs.getNextID(), "Fossil Material");
	
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
    public static Item emptyShell;
    public static Item magicConch;
    public static Item icedMeat;
    public static Item woodjavelin;
    public static Item stonejavelin;
    public static Item ironjavelin;
    public static Item goldjavelin;
    public static Item diamondjavelin;
    public static Item ancientJavelin;
    public static Item whip;
	
	//DNA
	public static Item dna;
	public static Item dnaTriceratops;
	public static Item dnaRaptor;
	public static Item dnaTRex;
	public static Item dnaPterosaur;
	public static Item dnaNautilus;
	public static Item dnaPlesiosaur;
	public static Item dnaMosasaurus;
	public static Item dnaStegosaurus;
	public static Item dnaUtahraptor;
	public static Item dnaBrachiosaurus;

	public static Item animalDNA;
	public static Item dnaPig;
	public static Item dnaSheep;
	public static Item dnaTCow;
	public static Item dnaChicken;
	public static Item dnaSaberCat;
	public static Item dnaMammoth;
	
	//Ancient Egg
	public static Item ancientegg;
	public static Item eggTriceratops;
	public static Item eggRaptor;
	public static Item eggTRex;
	public static Item eggPterosaur;
	public static Item shellNautilus;
	public static Item eggPlesiosaur;
	public static Item eggMosasaurus;
	public static Item eggStegosaurus;
	public static Item eggUtahraptor;
	public static Item eggBrachiosaurus;
	
	//Embryos
	public static Item embyoSyringe;
	public static Item embryoPig;
	public static Item embryoSheep;
	public static Item embryoCow;
	public static Item embryoSaberCat;
	public static Item embryoMammoth;
	
	//Item Food
	public static Item cookedChickenSoup;
	public static Item rawChickenSoup;
    public static Item chickenEss;
	public static Item sjl;
	public static Item rawDinoMeat;
	public static Item rawTriceratops;
	public static Item rawRaptor;
	public static Item rawTRex;
	public static Item rawPterosaur;
	public static Item rawNautilus;
	public static Item rawPlesiosaur;
	public static Item rawMosasaurus;
	public static Item rawStegosaurus;
	public static Item rawUtahraptor;
	public static Item rawBrachiosaurus;
	public static Item cookedDinoMeat;
	
	//Config ID INTs
	//Blocks
	public static int blockFossilID;
	public static int blockSkullID;
	public static int skullLanternID;
	public static int blockanalyzerIdleID;
	public static int blockanalyzerActiveID;
	public static int blockcultivateIdleID;
	public static int blockcultivateActiveID;
	public static int blockworktableIdleID;
	public static int blockworktableActiveID;
	public static int blockTimeMachineID;
	public static int fernsID;
	public static int fernUpperID;
	public static int drumID;
	public static int feederIdleID;
	public static int feederActiveID;
	public static int blockPermafrostID;
	public static int blockIcedStoneID;
	
    //Items
    public static int biofossilID;
    public static int relicID;
    public static int stoneboardID;
    public static int ancientSwordID;
    public static int brokenSwordID;
    public static int fernSeedID;
    public static int ancienthelmetID;
    public static int brokenhelmetID;
    public static int skullStickID;
    public static int genID;
    public static int genAxeID;
    public static int genPickaxeID;
    public static int genSwordID;
    public static int genHoeID;
    public static int genShovelID;
    public static int dinoPediaID;
    public static int emptyShellID;
    public static int magicConchID;
    public static int icedMeatID;
    public static int woodjavelinID;
    public static int stonejavelinID;
    public static int ironjavelinID;
    public static int goldjavelinID;
    public static int diamondjavelinID;
    public static int ancientJavelinID;
    public static int whipID;
	
	//DNA
	//public static int dnaID;
	public static int dnaTriceratopsID;
	public static int dnaRaptorID;
	public static int dnaTRexID;
	public static int dnaPterosaurID;
	public static int dnaNautilusID;
	public static int dnaPlesiosaurID;
	public static int dnaMosasaurusID;
	public static int dnaStegosaurusID;
	public static int dnaUtahraptorID;
	public static int dnaBrachiosaurusID;

	//public static int animalDNAID;
	public static int dnaPigID;
	public static int dnaSheepID;
	public static int dnaTCowID;
	public static int dnaChickenID;
	public static int dnaSaberCatID;
	public static int dnaMammothID;
	
	//Ancient Egg
	public static int ancienteggID;
	public static int eggTriceratopsID;
	public static int eggRaptorID;
	public static int eggTRexID;
	public static int eggPterosaurID;
	public static int shellNautilusID;
	public static int eggPlesiosaurID;
	public static int eggMosasaurusID;
	public static int eggStegosaurusID;
	public static int eggUtahraptorID;
	public static int eggBrachiosaurusID;
	
	//Embryos
	public static int embyoSyringeID;
	public static int embryoPigID;
	public static int embryoSheepID;
	public static int embryoCowID;
	public static int embryoSaberCatID;
	public static int embryoMammothID;
	
	//Food
	public static int cookedChickenSoupID;
	public static int rawChickenSoupID;
    public static int chickenEssID;
	public static int sjlID;
	public static int rawDinoMeatID;
	public static int rawTriceratopsID;
	public static int rawRaptorID;
	public static int rawTRexID;
	public static int rawPterosaurID;
	public static int rawNautilusID;
	public static int rawPlesiosaurID;
	public static int rawMosasaurusID;
	public static int rawStegosaurusID;
	public static int rawUtahraptorID;
	public static int rawBrachiosaurusID;
	public static int cookedDinoMeatID;
	
    @cpw.mods.fml.common.Mod.PreInit
	public void PreInit(FMLPreInitializationEvent event)
	{
		proxy.registerSounds();
		UpdateLangProp();
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		//Blocks
		blockFossilID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockFossil", 3000).getInt(3000);
		blockSkullID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockSkull", 3001).getInt(3001);
		skullLanternID = config.getBlock(Configuration.CATEGORY_BLOCK, "skullLantern", 3002).getInt(3002);
		blockanalyzerIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockanalyzerIdle", 3003).getInt(3003);
		blockanalyzerActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockanalyzerActive", 3004).getInt(3004);
		blockcultivateIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockcultivateIdle", 3005).getInt(3005);
		blockcultivateActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockcultivateActive", 3006).getInt(3006);
		blockworktableIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockworktableIdle", 3007).getInt(3007);
		blockworktableActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockworktableActive", 3008).getInt(3008);
		blockTimeMachineID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockTimeMachine", 3009).getInt(3009);
		fernsID = config.getBlock(Configuration.CATEGORY_BLOCK, "ferns", 3010).getInt(3010);
		fernUpperID = config.getBlock(Configuration.CATEGORY_BLOCK, "fernUpper", 3011).getInt(3011);
		drumID = config.getBlock(Configuration.CATEGORY_BLOCK, "drum", 3012).getInt(3012);
		feederIdleID = config.getBlock(Configuration.CATEGORY_BLOCK, "feederIdle", 3013).getInt(3013);
		feederActiveID = config.getBlock(Configuration.CATEGORY_BLOCK, "feederActive", 3014).getInt(3014);
		blockPermafrostID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockPermafrost", 3015).getInt(3015);
		blockIcedStoneID = config.getBlock(Configuration.CATEGORY_BLOCK, "blockIcedStone", 3016).getInt(3016);
	
		//Items
		biofossilID = config.getItem(Configuration.CATEGORY_ITEM, "biofossil", 12000).getInt(12000);
		relicID = config.getItem(Configuration.CATEGORY_ITEM, "relic", 12001).getInt(12001);
		stoneboardID = config.getItem(Configuration.CATEGORY_ITEM, "stoneboard", 12002).getInt(12002);
		ancientSwordID = config.getItem(Configuration.CATEGORY_ITEM, "ancientSword", 12003).getInt(12003);
		brokenSwordID = config.getItem(Configuration.CATEGORY_ITEM, "brokenSword", 12004).getInt(12004);
		fernSeedID = config.getItem(Configuration.CATEGORY_ITEM, "fernSeed", 12005).getInt(12005);
		ancienthelmetID = config.getItem(Configuration.CATEGORY_ITEM, "ancienthelmet", 12006).getInt(12006);
		brokenhelmetID = config.getItem(Configuration.CATEGORY_ITEM, "brokenhelmet", 12007).getInt(12007);
		skullStickID = config.getItem(Configuration.CATEGORY_ITEM, "skullStick", 12008).getInt(12008);
		genID = config.getItem(Configuration.CATEGORY_ITEM, "gen", 12009).getInt(12009);
		genAxeID = config.getItem(Configuration.CATEGORY_ITEM, "genAxe", 12010).getInt(12010);
		genPickaxeID = config.getItem(Configuration.CATEGORY_ITEM, "genPickaxe", 12011).getInt(12011);
		genSwordID = config.getItem(Configuration.CATEGORY_ITEM, "genSword", 12012).getInt(12012);
		genHoeID = config.getItem(Configuration.CATEGORY_ITEM, "genHoe", 12013).getInt(12013);
		genShovelID = config.getItem(Configuration.CATEGORY_ITEM, "genShovel", 12014).getInt(12014);
		dinoPediaID = config.getItem(Configuration.CATEGORY_ITEM, "dinoPedia", 12015).getInt(12015);
		emptyShellID = config.getItem(Configuration.CATEGORY_ITEM, "emptyShell", 12016).getInt(12016);
		magicConchID = config.getItem(Configuration.CATEGORY_ITEM, "magicConch", 12017).getInt(12017);
		icedMeatID = config.getItem(Configuration.CATEGORY_ITEM, "icedMeat", 12018).getInt(12018);
		woodjavelinID = config.getItem(Configuration.CATEGORY_ITEM, "woodjavelin", 12019).getInt(12019);
		stonejavelinID = config.getItem(Configuration.CATEGORY_ITEM, "stonejavelin", 12020).getInt(12020);
		ironjavelinID = config.getItem(Configuration.CATEGORY_ITEM, "ironjavelin", 12021).getInt(12021);
		goldjavelinID = config.getItem(Configuration.CATEGORY_ITEM, "goldjavelin", 12022).getInt(12022);
		diamondjavelinID = config.getItem(Configuration.CATEGORY_ITEM, "diamondjavelin", 12023).getInt(12023);
		ancientJavelinID = config.getItem(Configuration.CATEGORY_ITEM, "ancientJavelin", 12024).getInt(12024);
		whipID = config.getItem(Configuration.CATEGORY_ITEM, "whip", 12025).getInt(12025);
	
		//DNA
		//public static int dnaID;
		dnaTriceratopsID = config.getItem(Configuration.CATEGORY_ITEM, "dnaTriceratops", 12026).getInt(12026);
		dnaRaptorID = config.getItem(Configuration.CATEGORY_ITEM, "dnaRaptor", 12027).getInt(12027);
		dnaTRexID = config.getItem(Configuration.CATEGORY_ITEM, "dnaTRex", 12028).getInt(12028);
		dnaPterosaurID = config.getItem(Configuration.CATEGORY_ITEM, "dnaPterosaur", 12029).getInt(12029);
		dnaNautilusID = config.getItem(Configuration.CATEGORY_ITEM, "dnaNautilus", 12030).getInt(12030);
		dnaPlesiosaurID = config.getItem(Configuration.CATEGORY_ITEM, "dnaPlesiosaur", 12031).getInt(12031);
		dnaMosasaurusID = config.getItem(Configuration.CATEGORY_ITEM, "dnaMosasaurus", 12032).getInt(12032);
		dnaStegosaurusID = config.getItem(Configuration.CATEGORY_ITEM, "dnaStegosaurus", 12033).getInt(12033);
		dnaUtahraptorID = config.getItem(Configuration.CATEGORY_ITEM, "dnaUtahraptor", 12034).getInt(12034);
		dnaBrachiosaurusID = config.getItem(Configuration.CATEGORY_ITEM, "dnaBrachiosaurus", 12035).getInt(12035);

		//public static int animalDNAID;
		dnaPigID = config.getItem(Configuration.CATEGORY_ITEM, "dnaPig", 12036).getInt(12036);
		dnaSheepID = config.getItem(Configuration.CATEGORY_ITEM, "dnaSheep", 12037).getInt(12037);
		dnaTCowID = config.getItem(Configuration.CATEGORY_ITEM, "dnaTCow", 12038).getInt(12038);
		dnaChickenID = config.getItem(Configuration.CATEGORY_ITEM, "dnaChicken", 12039).getInt(12039);
		dnaSaberCatID = config.getItem(Configuration.CATEGORY_ITEM, "dnaSaberCat", 12040).getInt(12040);
		dnaMammothID = config.getItem(Configuration.CATEGORY_ITEM, "dnaMammoth", 12075).getInt(12075);
		
		//Ancient Egg
		ancienteggID = config.getItem(Configuration.CATEGORY_ITEM, "ancientegg", 12041).getInt(12041);
		eggTriceratopsID = config.getItem(Configuration.CATEGORY_ITEM, "eggTriceratops", 12042).getInt(12042);
		eggRaptorID = config.getItem(Configuration.CATEGORY_ITEM, "eggRaptor", 12043).getInt(12043);
		eggTRexID = config.getItem(Configuration.CATEGORY_ITEM, "eggTRex", 12044).getInt(12044);
		eggPterosaurID = config.getItem(Configuration.CATEGORY_ITEM, "eggPterosaur", 12045).getInt(12045);
		shellNautilusID = config.getItem(Configuration.CATEGORY_ITEM, "shellNautilus", 12047).getInt(12047);
		eggPlesiosaurID = config.getItem(Configuration.CATEGORY_ITEM, "eggPlesiosaur", 12048).getInt(12048);
		eggMosasaurusID = config.getItem(Configuration.CATEGORY_ITEM, "eggMosasaurus", 12049).getInt(12049);
		eggStegosaurusID = config.getItem(Configuration.CATEGORY_ITEM, "eggStegosaurus", 12050).getInt(12050);
		eggUtahraptorID = config.getItem(Configuration.CATEGORY_ITEM, "eggUtahraptor", 12051).getInt(12051);
		eggBrachiosaurusID = config.getItem(Configuration.CATEGORY_ITEM, "eggBrachiosaurus", 12052).getInt(12052);
	
		//Embryos
		embyoSyringeID = config.getItem(Configuration.CATEGORY_ITEM, "embyoSyringe", 12053).getInt(12053);
		embryoPigID = config.getItem(Configuration.CATEGORY_ITEM, "embryoPig", 12054).getInt(12054);
		embryoSheepID = config.getItem(Configuration.CATEGORY_ITEM, "embryoSheep", 12055).getInt(12055);
		embryoCowID = config.getItem(Configuration.CATEGORY_ITEM, "embryoCow", 12056).getInt(12056);
		embryoSaberCatID = config.getItem(Configuration.CATEGORY_ITEM, "embryoSaberCat", 12057).getInt(12057);
		embryoMammothID = config.getItem(Configuration.CATEGORY_ITEM, "embryoMammoth", 12058).getInt(12058);
	
		//Food
		cookedChickenSoupID = config.getItem(Configuration.CATEGORY_ITEM, "cookedChickenSoup", 12059).getInt(12059);
		rawChickenSoupID = config.getItem(Configuration.CATEGORY_ITEM, "rawChickenSoup", 12060).getInt(12060);
		chickenEssID = config.getItem(Configuration.CATEGORY_ITEM, "chickenEss", 12061).getInt(12061);
		sjlID = config.getItem(Configuration.CATEGORY_ITEM, "sjl", 12062).getInt(12062);
		rawDinoMeatID = config.getItem(Configuration.CATEGORY_ITEM, "rawDinoMeat", 12063).getInt(12063);
		rawTriceratopsID = config.getItem(Configuration.CATEGORY_ITEM, "rawTriceratops", 12064).getInt(12064);
		rawRaptorID = config.getItem(Configuration.CATEGORY_ITEM, "rawRaptor", 12065).getInt(12065);
		rawTRexID = config.getItem(Configuration.CATEGORY_ITEM, "rawTRex", 12066).getInt(12066);
		rawPterosaurID = config.getItem(Configuration.CATEGORY_ITEM, "rawPterosaur", 12067).getInt(12067);
		rawNautilusID = config.getItem(Configuration.CATEGORY_ITEM, "rawNautilus", 12068).getInt(12068);
		rawPlesiosaurID = config.getItem(Configuration.CATEGORY_ITEM, "rawPlesiosaur", 12069).getInt(12069);
		rawMosasaurusID = config.getItem(Configuration.CATEGORY_ITEM, "rawMosasaurus", 12070).getInt(12070);
		rawStegosaurusID = config.getItem(Configuration.CATEGORY_ITEM, "rawStegosaurus", 12071).getInt(12071);
		rawUtahraptorID = config.getItem(Configuration.CATEGORY_ITEM, "rawUtahraptor", 12072).getInt(12072);
		rawBrachiosaurusID = config.getItem(Configuration.CATEGORY_ITEM, "rawBrachiosaurus", 12073).getInt(12073);
		cookedDinoMeatID = config.getItem(Configuration.CATEGORY_ITEM, "cookedDinoMeat", 12074).getInt(12074);
	
		config.save();
		
	}
	
	@cpw.mods.fml.common.Mod.Init
	public void Init(FMLInitializationEvent event)
	{
		//fillCreativeList();
		this.forgeHarvestLevelSetUp();
		
		//Blocks
		skullLantern = new BlockFossilSkull(skullLanternID, 0, true).setHardness(1.0F).setLightValue(0.9375F).setStepSound(Block.soundStoneFootstep).setBlockName("SkullLantern").setCreativeTab(this.tabFBlocks);
        blockanalyzerIdle = new BlockAnalyzer(blockanalyzerIdleID, false).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setBlockName("analyzerIdle").setCreativeTab(this.tabFBlocks);
        blockanalyzerActive = new BlockAnalyzer(blockanalyzerActiveID, true).setLightValue(0.9375F).setHardness(3.0F).setStepSound(Block.soundMetalFootstep).setBlockName("analyzerActive");
        blockcultivateIdle = new BlockCultivate(blockcultivateIdleID, false).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("cultivateIdle").setCreativeTab(this.tabFBlocks);
        blockcultivateActive = new BlockCultivate(blockcultivateActiveID, true).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("cultivateActive");
        blockworktableIdle = new BlockWorktable(blockworktableIdleID, false).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setBlockName("worktableIdle").setCreativeTab(this.tabFBlocks);
        blockworktableActive = new BlockWorktable(blockworktableActiveID, true).setHardness(2.5F).setStepSound(Block.soundWoodFootstep).setBlockName("worktableActive");
        feederIdle = new BlockFeeder(feederIdleID, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("Feeder").setRequiresSelfNotify().setCreativeTab(this.tabFBlocks);
        feederActive = new BlockFeeder(feederActiveID, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("Feeder").setRequiresSelfNotify();
        blockTimeMachine = new BlockTimeMachine(blockTimeMachineID, 0, Material.glass).setLightValue(0.9375F).setHardness(0.3F).setStepSound(Block.soundGlassFootstep).setBlockName("BlockTimeMachine").setCreativeTab(this.tabFBlocks);
        ferns = new BlockFern(fernsID, 0, false).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setRequiresSelfNotify().setCreativeTab((CreativeTabs)null);
        fernUpper = new BlockFern(fernUpperID, 0, true).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setRequiresSelfNotify().setCreativeTab((CreativeTabs)null);
        drum = new BlockDrum(drumID).setHardness(0.8F).setBlockName("drum").setRequiresSelfNotify().setCreativeTab(this.tabFBlocks);
        blockPermafrost = new BlockPermafrost(blockPermafrostID, 5).setHardness(0.5F).setLightOpacity(3).setStepSound(Block.soundGrassFootstep).setBlockName("Permafrost").setRequiresSelfNotify().setCreativeTab(this.tabFBlocks);
        blockIcedStone = new BlockIcedStone(blockIcedStoneID, 6).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("IcedStone").setRequiresSelfNotify().setCreativeTab(this.tabFBlocks);
        blockFossil = new BlockFossil(blockFossilID, 1).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundStoneFootstep).setBlockName("fossil").setCreativeTab(this.tabFBlocks);
        blockSkull = new BlockFossilSkull(blockSkullID, 0, false).setHardness(1.0F).setStepSound(Block.soundStoneFootstep).setBlockName("Skull").setCreativeTab(this.tabFBlocks);
        
        //Items
		biofossil = new ItemBioFossil(biofossilID).setIconIndex(38).setItemName("biofossil").setCreativeTab(this.tabFItems);
		relic = new ItemRelic(relicID).setIconIndex(39).setItemName("relic").setCreativeTab(this.tabFItems);
		stoneboard = new ItemStoneBoard(stoneboardID).setIconIndex(44).setItemName("stoneboard").setCreativeTab(this.tabFItems);
		ancientSword = new ItemAncientsword(ancientSwordID).setIconIndex(1).setItemName("ancientsword").setCreativeTab(this.tabFCombat);
		brokenSword = new ItemBrokenSword(brokenSwordID).setIconIndex(0).setItemName("Brokensword").setCreativeTab(this.tabFMaterial);
		fernSeed = new ItemFernSeed(fernSeedID, ferns.blockID).setIconIndex(40).setItemName("FernSeed").setCreativeTab(this.tabFItems);
		ancienthelmet = new ForgeItemArmor(ancienthelmetID, EnumArmorMaterial.IRON, 3, 0).setItemName("ancientHelmet").setCreativeTab(CreativeTabs.tabCombat);
		brokenhelmet = new ItemBrokenHelmet(brokenhelmetID).setIconIndex(2).setItemName("BrokenHelmet").setCreativeTab(this.tabFMaterial);
		skullStick = new ForgeItem(skullStickID).setIconIndex(5).setItemName("SkullStick").setCreativeTab(this.tabFItems);
		gen = new ItemGen(genID).setIconIndex(4).setItemName("Gen").setCreativeTab(this.tabFItems);
		genAxe = new ForgeItemAxe(genAxeID, EnumToolMaterial.EMERALD).setIconIndex(19).setItemName("GenAxe").setCreativeTab(this.tabFTools);
		genPickaxe = new ForgeItemPickaxe(genPickaxeID, EnumToolMaterial.EMERALD).setIconIndex(18).setItemName("GenPickaxe").setCreativeTab(this.tabFTools);
		genSword = new ForgeItemSword(genSwordID, EnumToolMaterial.EMERALD).setIconIndex(16).setItemName("GenSword");
		genHoe = new ForgeItemHoe(genHoeID, EnumToolMaterial.EMERALD).setIconIndex(20).setItemName("GenHoe").setCreativeTab(this.tabFTools);
		genShovel = new ForgeItemSpade(genShovelID, EnumToolMaterial.EMERALD).setIconIndex(17).setItemName("GenShovel").setCreativeTab(this.tabFTools);
		dinoPedia = new ForgeItem(dinoPediaID).setIconIndex(53).setItemName("dinopedia").setCreativeTab(this.tabFItems);
		emptyShell = new ForgeItem(emptyShellID).setIconIndex(42).setItemName("EmptyShell").setCreativeTab(this.tabFItems);
		magicConch = new ItemMagicConch(magicConchID).setIconIndex(21).setItemName("MagicConch").setCreativeTab(this.tabFTools);
		icedMeat = new ItemIcedMeat(icedMeatID, EnumToolMaterial.EMERALD).setIconIndex(57).setItemName("IcedMeat").setCreativeTab(this.tabFCombat);
		woodjavelin = new ItemJavelin(woodjavelinID, EnumToolMaterial.WOOD).setIconIndex(0).setItemName("WoodJavelin").setCreativeTab(this.tabFCombat);
		stonejavelin = new ItemJavelin(stonejavelinID, EnumToolMaterial.STONE).setIconIndex(0).setItemName("StoneJavelin").setCreativeTab(this.tabFCombat);
		ironjavelin = new ItemJavelin(ironjavelinID, EnumToolMaterial.IRON).setIconIndex(0).setItemName("IronJavelin").setCreativeTab(this.tabFCombat);
		goldjavelin = new ItemJavelin(goldjavelinID, EnumToolMaterial.GOLD).setIconIndex(0).setItemName("GoldJavelin").setCreativeTab(this.tabFCombat);
		diamondjavelin = new ItemJavelin(diamondjavelinID, EnumToolMaterial.EMERALD).setIconIndex(0).setItemName("DiamondJavelin").setCreativeTab(this.tabFCombat);
		ancientJavelin = new ItemJavelin(ancientJavelinID, EnumToolMaterial.IRON).setItemName("AncientJavelin").setCreativeTab(this.tabFCombat);
		whip = new ItemWhip(whipID).setIconIndex(64).setItemName("FossilWhip").setCreativeTab(this.tabFTools);
        
		//Ancient Egg
		//ancientegg = new ItemAncientEgg(ancienteggID).setIconIndex(0).setItemName("TriceratopsEgg").setCreativeTab(CreativeTabs.tabMaterials);
		eggTriceratops = new ItemAncientEgg(eggTriceratopsID).setIconIndex(22).setItemName("eggTriceratops").setCreativeTab(this.tabFMaterial);
		eggRaptor = new ItemAncientEgg(eggRaptorID).setIconIndex(23).setItemName("eggRaptor").setCreativeTab(this.tabFMaterial);
		eggTRex = new ItemAncientEgg(eggTRexID).setIconIndex(24).setItemName("eggTRex").setCreativeTab(this.tabFMaterial);
		eggPterosaur = new ItemAncientEgg(eggPterosaurID).setIconIndex(25).setItemName("eggPterosaur").setCreativeTab(this.tabFMaterial);
		shellNautilus = new ItemAncientEgg(shellNautilusID).setIconIndex(26).setItemName("shellNautilus").setCreativeTab(this.tabFMaterial);
		eggPlesiosaur = new ItemAncientEgg(eggPlesiosaurID).setIconIndex(27).setItemName("eggPlesiosaur").setCreativeTab(this.tabFMaterial);
		eggMosasaurus = new ItemAncientEgg(eggMosasaurusID).setIconIndex(28).setItemName("eggMosasaurus").setCreativeTab(this.tabFMaterial);
		eggStegosaurus = new ItemAncientEgg(eggStegosaurusID).setIconIndex(29).setItemName("eggStegosaurus").setCreativeTab(this.tabFMaterial);
		eggUtahraptor = new ItemAncientEgg(eggUtahraptorID).setIconIndex(30).setItemName("eggUtahraptor").setCreativeTab(this.tabFMaterial);
		eggBrachiosaurus = new ItemAncientEgg(eggBrachiosaurusID).setIconIndex(31).setItemName("eggBrachiosaurus").setCreativeTab(this.tabFMaterial);
		
		//DNA
		//public static Item dna;
		dna = new ItemDNA(12076);
		dnaTriceratops = new ItemDNA(dnaTriceratopsID).setIconIndex(6).setItemName("dnaTriceratops").setCreativeTab(this.tabFMaterial);
		dnaRaptor = new ItemDNA(dnaRaptorID).setIconIndex(7).setItemName("dnaRaptor").setCreativeTab(this.tabFMaterial);
		dnaTRex = new ItemDNA(dnaTRexID).setIconIndex(8).setItemName("dnaTRex").setCreativeTab(this.tabFMaterial);
		dnaPterosaur = new ItemDNA(dnaPterosaurID).setIconIndex(9).setItemName("dnaPterosaur").setCreativeTab(this.tabFMaterial);
		dnaNautilus = new ItemDNA(dnaNautilusID).setIconIndex(10).setItemName("dnaNautilus").setCreativeTab(this.tabFMaterial);
		dnaPlesiosaur = new ItemDNA(dnaPlesiosaurID).setIconIndex(11).setItemName("dnaPlesiosaur").setCreativeTab(this.tabFMaterial);
		dnaMosasaurus = new ItemDNA(dnaMosasaurusID).setIconIndex(12).setItemName("dnaMosasaurus").setCreativeTab(this.tabFMaterial);
		dnaStegosaurus = new ItemDNA(dnaStegosaurusID).setIconIndex(13).setItemName("dnaStegosaurus").setCreativeTab(this.tabFMaterial);
		dnaUtahraptor = new ItemDNA(dnaUtahraptorID).setIconIndex(14).setItemName("dnaUtahraptor").setCreativeTab(this.tabFMaterial);
		dnaBrachiosaurus = new ItemDNA(dnaBrachiosaurusID).setIconIndex(15).setItemName("dnaBrachiosaurus").setCreativeTab(this.tabFMaterial);

		//public static Item animalDNA;
		animalDNA = new ItemNonDinoDNA(12077);
		dnaPig = new ItemNonDinoDNA(dnaPigID).setIconIndex(70).setItemName("dnaPig").setCreativeTab(this.tabFMaterial);
		dnaSheep = new ItemNonDinoDNA(dnaSheepID).setIconIndex(71).setItemName("dnaSheep").setCreativeTab(this.tabFMaterial);
		dnaTCow = new ItemNonDinoDNA(dnaTCowID).setIconIndex(72).setItemName("dnaTCow").setCreativeTab(this.tabFMaterial);
		dnaChicken = new ItemNonDinoDNA(dnaChickenID).setIconIndex(73).setItemName("dnaChicken").setCreativeTab(this.tabFMaterial);
		dnaSaberCat = new ItemNonDinoDNA(dnaSaberCatID).setIconIndex(74).setItemName("dnaSaberCat").setCreativeTab(this.tabFMaterial);
		dnaMammoth = new ItemNonDinoDNA(dnaMammothID).setIconIndex(75).setItemName("dnaMammoth").setCreativeTab(this.tabFMaterial);
		
		//Ebryos
		//embyoSyringe = new ItemEmbryoSyringe(embyoSyringeID).setIconIndex(0).setItemName("EmbryoSyringe").setCreativeTab(this.tabFItems);
		embryoPig = new ItemEmbryoSyringe(embryoPigID).setIconIndex(0).setItemName("embryoPig").setCreativeTab(this.tabFItems);
		embryoSheep = new ItemEmbryoSyringe(embryoSheepID).setIconIndex(1).setItemName("embryoSheep").setCreativeTab(this.tabFItems);
		embryoCow = new ItemEmbryoSyringe(embryoCowID).setIconIndex(2).setItemName("embryoCow").setCreativeTab(this.tabFItems);
		embryoSaberCat = new ItemEmbryoSyringe(embryoSaberCatID).setIconIndex(3).setItemName("embryoSaberCat").setCreativeTab(this.tabFItems);
		embryoMammoth = new ItemEmbryoSyringe(embryoMammothID).setIconIndex(4).setItemName("embryoMammoth").setCreativeTab(this.tabFItems);
		
		//Item Food
		rawDinoMeat = new ItemDinoMeat(12078, 3, 0.3F, true);
		rawTriceratops = new ItemDinoMeat(rawTriceratopsID, 3, 0.3F, true).setIconIndex(0).setItemName("Triceratops Meat").setCreativeTab(this.tabFFood);
		rawRaptor = new ItemDinoMeat(rawRaptorID, 3, 0.3F, true).setIconIndex(0).setItemName("Raptor Meat").setCreativeTab(this.tabFFood);
		rawTRex = new ItemDinoMeat(rawTRexID, 3, 0.3F, true).setIconIndex(0).setItemName("TRex Meat").setCreativeTab(this.tabFFood);
		rawPterosaur = new ItemDinoMeat(rawPterosaurID, 3, 0.3F, true).setIconIndex(0).setItemName("Pterosaur Meat").setCreativeTab(this.tabFFood);
		rawNautilus = new ItemDinoMeat(rawNautilusID, 3, 0.3F, true).setIconIndex(0).setItemName("Nautilus Meat").setCreativeTab(this.tabFFood);
		rawPlesiosaur = new ItemDinoMeat(rawPlesiosaurID, 3, 0.3F, true).setIconIndex(0).setItemName("Plesiosaur Meat").setCreativeTab(this.tabFFood);
		rawMosasaurus = new ItemDinoMeat(rawMosasaurusID, 3, 0.3F, true).setIconIndex(0).setItemName("Mosasaurus Meat").setCreativeTab(this.tabFFood);
		rawStegosaurus = new ItemDinoMeat(rawStegosaurusID, 3, 0.3F, true).setIconIndex(0).setItemName("Stegosaurus Meat").setCreativeTab(this.tabFFood);
		rawUtahraptor = new ItemDinoMeat(rawUtahraptorID, 3, 0.3F, true).setIconIndex(0).setItemName("Utahraptor Meat").setCreativeTab(this.tabFFood);
		rawBrachiosaurus = new ItemDinoMeat(rawBrachiosaurusID, 3, 0.3F, true).setIconIndex(0).setItemName("Brachiosaurus Meat").setCreativeTab(this.tabFFood);
		cookedDinoMeat = new ForgeItemFood(cookedDinoMeatID, 8, 0.8F, true).setIconIndex(56).setItemName("CookedDinoMeat").setCreativeTab(this.tabFFood);
		cookedChickenSoup = new ItemChickenSoup(cookedChickenSoupID).setIconIndex(58).setItemName("ChickenSoup").setMaxStackSize(1).setContainerItem(Item.bucketEmpty).setCreativeTab(this.tabFFood);
		rawChickenSoup = new ItemChickenSoup(rawChickenSoupID).setIconIndex(58).setItemName("RawChickenSoup").setMaxStackSize(1).setContainerItem(Item.bucketEmpty).setCreativeTab(this.tabFFood);
		chickenEss = new ForgeItemFood(chickenEssID, 10, 0.0F, false).setIconIndex(59).setItemName("ChickenEss").setContainerItem(Item.glassBottle).setCreativeTab(this.tabFFood);
		sjl = new ForgeItemFood(sjlID, 8, 2.0F, false).setIconIndex(43).setItemName("SioChiuLe").setCreativeTab(this.tabFFood);
        
		//Block Registry
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

		//Items & Block Names
        LanguageRegistry.addName(blockFossil, "Block Fossil");
        LanguageRegistry.addName(blockSkull, "Block Skull");
        LanguageRegistry.addName(skullLantern, "Skull Lantern");
        LanguageRegistry.addName(biofossil, "Bio Fossil");
        LanguageRegistry.addName(relic, "Relic");
        LanguageRegistry.addName(stoneboard, "Stone Board");
        LanguageRegistry.addName(blockanalyzerIdle, "Block Analyzer");
        LanguageRegistry.addName(blockcultivateIdle, "Block Cultivate");
        LanguageRegistry.addName(blockworktableIdle, "Block Worktable");
        LanguageRegistry.addName(brokenSword, "Broken Sword");
        LanguageRegistry.addName(ancientSword, "Ancient Sword");
        LanguageRegistry.addName(fernSeed, "Fern Seed");
        LanguageRegistry.addName(brokenhelmet, "Broken Helmet");
        LanguageRegistry.addName(ancienthelmet, "Ancient Helmet");
        LanguageRegistry.addName(skullStick, "Skull Stick");
        LanguageRegistry.addName(drum, "Drum");
        LanguageRegistry.addName(feederIdle, "Feeder");
        LanguageRegistry.addName(gen, "Gen");
        LanguageRegistry.addName(genAxe, "Gen Axe");
        LanguageRegistry.addName(genPickaxe, "Gen Pickaxe");
        LanguageRegistry.addName(genSword, "Gen Sword");
        LanguageRegistry.addName(genHoe, "Gen Hoe");
        LanguageRegistry.addName(genShovel, "Gen Shovel");
        LanguageRegistry.addName(dinoPedia, "Dino Pedia");
        LanguageRegistry.addName(emptyShell, "Empty Shell");
        LanguageRegistry.addName(magicConch, "Magic Conch");
        
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
		
		LanguageRegistry.addName(sjl, "SJL");
		LanguageRegistry.addName(chickenEss, "Chicken Ess");
		LanguageRegistry.addName(cookedDinoMeat, "Cooked Dino Meat");
		LanguageRegistry.addName(rawTriceratops, "Triceratops Meat");
        LanguageRegistry.addName(rawRaptor, "Raptor Meat");
        LanguageRegistry.addName(rawTRex, "TRex Meat");
        LanguageRegistry.addName(rawPterosaur, "Pterosaur Meat");
        LanguageRegistry.addName(rawNautilus, "Nautilus Meat");
        LanguageRegistry.addName(rawPlesiosaur, "Plesiosaur Meat");
        LanguageRegistry.addName(rawMosasaurus, "Mosasaurus Meat");
        LanguageRegistry.addName(rawStegosaurus, "Stegosaurus Meat");
        LanguageRegistry.addName(rawUtahraptor, "Utahraptor Meat");
        LanguageRegistry.addName(rawBrachiosaurus, "Brachiosaurus Meat");
        LanguageRegistry.addName(cookedChickenSoup, "Cooked Chicken Soup");
		LanguageRegistry.addName(rawChickenSoup, "Raw Chicken Soup");
		
		//Ancient Egg
		LanguageRegistry.addName(eggTriceratops, "Egg Triceratops");
		LanguageRegistry.addName(eggRaptor, "Egg Raptor");
		LanguageRegistry.addName(eggTRex, "Egg TRex");
		LanguageRegistry.addName(eggPterosaur, "Egg Pterosaur");
		LanguageRegistry.addName(shellNautilus, "Shell Nautilus");
		LanguageRegistry.addName(eggPlesiosaur, "Egg Plesiosaur");
		LanguageRegistry.addName(eggMosasaurus, "Egg Mosasaurus");
		LanguageRegistry.addName(eggStegosaurus, "Egg Stegosaurus");
		LanguageRegistry.addName(eggUtahraptor, "Egg Utahraptor");
		LanguageRegistry.addName(eggBrachiosaurus, "Egg Brachiosaurus");
		
		//DNA
		LanguageRegistry.addName(dnaTriceratops, "DNA Triceratops");
		LanguageRegistry.addName(dnaTRex, "DNA TRex");
		LanguageRegistry.addName(dnaRaptor, "DNA Raptor");
		LanguageRegistry.addName(dnaPterosaur, "DNA Pterosaur");
		LanguageRegistry.addName(dnaNautilus, "DNA Nautilus");
		LanguageRegistry.addName(dnaPlesiosaur, "DNA Plesiosaur");
		LanguageRegistry.addName(dnaMosasaurus, "DNA Mosasaurus");
		LanguageRegistry.addName(dnaStegosaurus, "DNA Stegosaurus");
		LanguageRegistry.addName(dnaUtahraptor, "DNA Utahraptor");
		LanguageRegistry.addName(dnaBrachiosaurus, "DNA Brachiosaurus");
		LanguageRegistry.addName(dnaPig, "DNA Pig");
		LanguageRegistry.addName(dnaSheep, "DNA Sheep");
		LanguageRegistry.addName(dnaTCow, "DNA TCow");
		LanguageRegistry.addName(dnaChicken, "DNA Chicken");
		LanguageRegistry.addName(dnaSaberCat, "DNA SaberCat");
		LanguageRegistry.addName(dnaMammoth, "DNA Mammoth");
		
		//Embryo
		LanguageRegistry.addName(embryoPig, "embryoPig");
		LanguageRegistry.addName(embryoSheep, "embryoSheep");
		LanguageRegistry.addName(embryoCow, "embryoCow");
		LanguageRegistry.addName(embryoSaberCat, "embryoSaberCat");
		LanguageRegistry.addName(embryoMammoth, "embryoMammoth");

		GameRegistry.addRecipe(new ItemStack(skullLantern, 1), new Object[] {"X", "Y", 'X', blockSkull, 'Y', Block.torchWood});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 5, 15), new Object[] {"X", 'X', blockSkull});
		GameRegistry.addRecipe(new ItemStack(Item.dyePowder, 5, 15), new Object[] {"X", 'X', skullLantern});
		GameRegistry.addRecipe(new ItemStack(blockcultivateIdle, 1), new Object[] {"XYX", "XWX", "ZZZ", 'X', Block.glass, 'Y', new ItemStack(Item.dyePowder, 1, 2), 'W', Item.bucketWater, 'Z', Item.ingotIron});
		GameRegistry.addRecipe(new ItemStack(blockanalyzerIdle, 1), new Object[] {"XYX", "XWX", 'X', Item.ingotIron, 'Y', relic, 'W', biofossil});
		GameRegistry.addRecipe(new ItemStack(blockworktableIdle, 1), new Object[] {"X", "Y", 'X', Item.paper, 'Y', Block.workbench});
		/*GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(Item.cake, 1), new Object[] {"AAA", "BEB", "CCC", 'A', Item.bucketMilk, 'B', Item.sugar, 'C', Item.wheat, 'E', new ItemStack(ancientegg, 1, 8)});*/
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
		//GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 0)});
		/*GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 2)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 6)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(dinoPedia), new Object[] {Item.book, new ItemStack(dna, 1, 8)});*/
		//GameRegistry.addShapelessRecipe(new ItemStack(chickenSoup, 1, 0), new Object[] {Item.bucketEmpty, Item.chickenRaw});
		//GameRegistry.addShapelessRecipe(new ItemStack(magicConch, 1, 1), new Object[] {new ItemStack(magicConch, 1, 0)});
		//GameRegistry.addShapelessRecipe(new ItemStack(magicConch, 1, 2), new Object[] {new ItemStack(magicConch, 1, 1)});
		//GameRegistry.addShapelessRecipe(new ItemStack(magicConch, 1, 0), new Object[] {new ItemStack(magicConch, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(chickenEss, 8), new Object[] {"XXX", "XYX", "XXX", 'X', Item.glassBottle, 'Y', new ItemStack(rawChickenSoup, 1, 1)});
		
        //GameRegistry.addSmelting(chickenSoup.itemID, new ItemStack(chickenSoup, 1, 1), 3.0F);
        //GameRegistry.addSmelting(rawDinoMeat.itemID, new ItemStack(cookedDinoMeat), 3.0F);
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
		GameRegistry.registerWorldGenerator(new WorldGenAcademy());
		//GameRegistry.registerWorldGenerator(new WorldGenShipWreck());
		//GameRegistry.registerWorldGenerator(new WorldGenWeaponShopA());

		NetworkRegistry.instance().registerChatListener(messagerHandler);
		NetworkRegistry.instance().registerGuiHandler(this, GH);

		GameRegistry.registerTileEntity(TileEntityCultivate.class, "Cultivate");
		GameRegistry.registerTileEntity(TileEntityAnalyzer.class, "Analyzer");
		GameRegistry.registerTileEntity(TileEntityWorktable.class, "Worktable");
		GameRegistry.registerTileEntity(TileEntityDrum.class, "Drum");
		GameRegistry.registerTileEntity(TileEntityFeeder.class, "Feeder");
		GameRegistry.registerTileEntity(TileEntityTimeMachine.class, "TimeMachine");
		
		proxy.registerTileEntitySpecialRenderer();
        proxy.registerRenderThings();
        
	}
	
	/*public static void fillCreativeList()
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
    }*/
	
    private void forgeHarvestLevelSetUp()
    {
        MinecraftForge.setBlockHarvestLevel(blockFossil, 0, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(blockPermafrost, 0, "shovel", 2);
        MinecraftForge.setBlockHarvestLevel(blockIcedStone, 0, "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(blockIcedStone, 1, "pickaxe", 1);
    }
	
	public static String GetLangTextByKey(String var0)
	{
		String var1 = LangProps.getProperty(var0, "");
		if(var1=="" && DebugMode)
			System.err.println("Error finding language key entry: " + var0);
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

	/*public static int EnumToInt(EnumOrderType var0)
	{// NOT NEEDED BY ANYONE==>USE .ordinal
			return var0.ToInt();
	}*/

	public static void UpdateLangProp()
	{
    		//Langfile = new File(Fossil.class.getResource("/Fossillang/" + LastLangSetting + ".lang").getFile());
				
			//UTF8Reader(LangProps);
			//System.out.println("LANGUAGE: "+Minecraft.getMinecraft().gameSettings.language);
			String file="E:/Tims Büro/Programmieren/Java/MC/FA/forge/mcp/eclipse/Minecraft/bin/mod/fossil/common/Fossillang/en_US.lang";

			try
			{
				//InputStream i0=Fossil.class.getResourceAsStream(file);
				//BufferedReader var2 = new BufferedReader(new InputStreamReader(Fossil.class.getResourceAsStream(file), "UTF-8"));
				BufferedReader var2 = new BufferedReader(new FileReader(file));
				for (String var3 = var2.readLine(); var3 != null; var3 = var2.readLine())//"/Fossillang/" + var1 + ".lang"
				{
		    		var3 = var3.trim();
		
		    		if (!var3.startsWith("#"))
		    		{
		        		String[] var4 = var3.split("=");
		        		if (var4 != null && var4.length == 2)
		        		{
		        			LangProps.setProperty(var4[0], var4[1]);
		        			if(Fossil.DebugMode)
		        				System.out.println("SUCCESS: "+var4[0]+" added");
		        		}
		    		}
				}
			}
			catch(IOException e)
			{
				System.err.println("Error loading language file: " + e.getMessage());
			}
	}

	/*private static void UTF8Reader(Properties var0)
	{
		System.out.println("LANGUAGE: "+Minecraft.getMinecraft().gameSettings.language);
		String file="E:/Tims Büro/Programmieren/Java/MC/FA/forge/mcp/eclipse/Minecraft/bin/mod/fossil/common/Fossillang/en_US.lang";

		try
		{
			//InputStream i0=Fossil.class.getResourceAsStream(file);
			//BufferedReader var2 = new BufferedReader(new InputStreamReader(Fossil.class.getResourceAsStream(file), "UTF-8"));
			BufferedReader var2 = new BufferedReader(new FileReader(file));
			for (String var3 = var2.readLine(); var3 != null; var3 = var2.readLine())//"/Fossillang/" + var1 + ".lang"
			{
	    		var3 = var3.trim();
	
	    		if (!var3.startsWith("#"))
	    		{
	        		String[] var4 = var3.split("=");
	        		if (var4 != null && var4.length == 2)
	        		{
	        			var0.setProperty(var4[0], var4[1]);
	        			if(Fossil.DebugMode)
	        				System.out.println("SUCCESS: "+var4[0]+" added");
	        		}
	    		}
			}
		}
		catch(IOException e)
		{
			System.err.println("Error loading language file: " + e.getMessage());
		}
	}*/

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
