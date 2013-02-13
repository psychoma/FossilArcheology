package mod_Fossil;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class DinoSoundHandler
{
    private static final String DINOSOUND_LOC = "DinoSounds/";
    
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        File soundFileList = new File(this.getClass().getResource(DINOSOUND_LOC).getFile());
        String[] soundNameList = soundFileList.list();
        String nameTmp;

        try
        {

            for (int i = 0; i < soundNameList.length; i++)
            {

                nameTmp = soundFileList.getPath() + "/" + soundNameList[i];
//            	nameTmp = soundFileList.getPath() + soundNameList[i];
                mod_Fossil.DebugMessage("SOUND DEBUG1:" + nameTmp);
                event.manager.soundPoolSounds.addSound(soundNameList[i], new File(nameTmp));

                
            }
        }
            
        catch (Throwable e)
        {
        	System.err.println("Error loading sound: " + e.getMessage());
        }
    	mod_Fossil.DebugMessage("Loaded Sound Handler.");
    }

    /*public void onLoadSoundSettings(SoundManager soundManager) {
    	mainManager=soundManager;
    }

    public SoundPoolEntry onPlayBackgroundMusic(SoundManager soundManager,
    		SoundPoolEntry entry) {
    	// TODO Auto-generated method stub
    	return entry;
    }

    public SoundPoolEntry onPlayStreaming(SoundManager soundManager,
    		SoundPoolEntry entry, String soundName, float x, float y, float z) {
    	// TODO Auto-generated method stub
    	return entry;
    }

    public SoundPoolEntry onPlaySound(SoundManager soundManager,
    		SoundPoolEntry entry, String soundName, float x, float y, float z,
    		float volume, float pitch) {
    	// TODO Auto-generated method stub
    	return entry;
    }

    public SoundPoolEntry onPlaySoundEffect(SoundManager soundManager,
    		SoundPoolEntry entry, String soundName, float volume, float pitch) {
    	// TODO Auto-generated method stub
    	return entry;
    }

    public String onPlaySoundAtEntity(Entity entity, String soundName,
    		float volume, float pitch) {
    	/*if (mainManager==null || soundName==null || soundName.isEmpty()) return null;
    	final String WAV=".wav";
    	SoundPool pool=mainManager.getSoundsPool();
    	if (pool==null) return null;
    	SoundPoolEntry checker=pool.getRandomSoundFromSoundPool(soundName);
    	if (checker==null){
    		URL loc;
    		for (int i=0;true;i++){
    			loc=this.getClass().getResource(DINOSOUND_LOC +soundName + ((i==0)?"":String.valueOf(i))+WAV);
    			if (loc==null && i!=0) break;
    			String targetSoundName=soundName+((i==0)?"":String.valueOf(i))+WAV;
    			if (!targetSoundName.contains("."))return soundName;
    			pool.addSound(targetSoundName, this.getClass().getResource(DINOSOUND_LOC + soundName+ ((i==0)?"":String.valueOf(i))+WAV));
    		}
    	}
    	return soundName;
    }*/

}
