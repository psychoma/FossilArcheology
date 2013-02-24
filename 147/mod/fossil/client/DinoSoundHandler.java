package mod.fossil.client;

import java.io.File;
import java.net.URL;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class DinoSoundHandler
{
    @ForgeSubscribe
    public void onSoundsLoaded(SoundLoadEvent var1)
    {
        /*"/mod/fossil/common/dinoSounds/"*/
        
        File folder = new File("E:/Tims Büro/Programmieren/Java/MC/FA/forge/mcp/eclipse/Minecraft/bin/mod/fossil/common/dinoSounds");
        
    	if (!folder.exists())System.out.println("Could not find Sound Folder");
    	if (folder.isFile())System.out.println("Sound folder is file");
    	if (!folder.canRead())System.out.println("Sound folder not readable");

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
