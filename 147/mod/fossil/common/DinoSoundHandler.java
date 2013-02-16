package mod.fossil.common;

import java.io.File;
import net.minecraft.client.audio.SoundManager;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class DinoSoundHandler
{
//    private static final String DINOSOUND_LOC = "dinoSounds/";

    @ForgeSubscribe
    public void onSoundsLoaded(SoundLoadEvent var1)
    {
        SoundManager var2 = var1.manager;

        try
        {
            File var3 = new File(this.getClass().getResource("dinoSounds/").getFile());
            String[] var4 = var3.list();

            for (int var6 = 0; var6 < var4.length; ++var6)
            {
                String var5 = var3.getPath() + "/" + var4[var6];
                var1.manager.soundPoolSounds.addSound(var4[var6], new File(var5));
            }
        }
        catch (Throwable var7)
        {
            System.err.println("Error loading sound: " + var7.getMessage());
        }
    }
}
