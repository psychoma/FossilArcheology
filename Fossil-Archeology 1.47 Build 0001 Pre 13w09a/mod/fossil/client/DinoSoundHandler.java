package mod.fossil.client;

import java.io.File;
import java.net.URL;
import java.util.Random;

import mod.fossil.common.Fossil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class DinoSoundHandler
{
    @ForgeSubscribe
    //@SideOnly(Side.CLIENT)
    public void onSoundsLoaded(SoundLoadEvent var1)
    {//Fossil.class.getResource("dinoSounds/").getFile());
        //File folder = new File("E:/Tims Büro/Programmieren/Java/MC/FA/forge/mcp/eclipse/Minecraft/bin/mod/fossil/common/dinoSounds");
    	Minecraft mc = Minecraft.getMinecraft();
    	File folder =new File(mc.mcDataDir, "resources/mods/fossil/dinoSounds");
        
        /*if(var1.manager.soundPoolSounds.addSound("microjunk/animalsounds/common/sounds/frog.ogg", new File(mc.mcDataDir, "resources/mod/as/frog.ogg"))!=null)
        	System.out.println("SUCCESS");
        else
        	System.out.println("FAIL");*/
        	
        
    	if (!folder.exists())System.out.println("Could not find Sound Folder "+folder.getAbsolutePath());
    	if (folder.isFile())System.out.println("Sound folder is file "+folder.getAbsolutePath());
    	if (!folder.canRead())System.out.println("Sound folder not readable "+folder.getAbsolutePath());

        File[] Filelist = folder.listFiles();
        for (int i = 0; i < Filelist.length; ++i)
        {
            try
            {
        	String soundfile=Filelist[i].getName();
        	URL path = Filelist[i].toURI().toURL();
        	if(var1.manager.soundPoolSounds.addSound(soundfile, path)==null)
        		System.out.println("Could not Load Sound File "+path);
            }
            catch (Throwable var7)
            {
                System.err.println("Error loading sound: " + var7.getMessage());
            }
        }
    }
}
