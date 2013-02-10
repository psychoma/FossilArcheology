package mod_Fossil;

import net.minecraftforge.client.MinecraftForgeClient;

public class FossilClientProxy extends FossilCommonProxy{
	
	
    @Override
    public void registerRenderers() {
            MinecraftForgeClient.preloadTexture(FOS_TERRIAN_PNG);
            MinecraftForgeClient.preloadTexture(FOS_ITEMS_PNG);
            MinecraftForgeClient.preloadTexture(NEEDLE_PNG);
    }

}
