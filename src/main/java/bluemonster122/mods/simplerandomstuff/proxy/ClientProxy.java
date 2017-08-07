package bluemonster122.mods.simplerandomstuff.proxy;

import bluemonster122.mods.simplerandomstuff.SimpleRandomStuff;
import bluemonster122.mods.simplerandomstuff.util.IFeatureRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static bluemonster122.mods.simplerandomstuff.SimpleRandomStuff.featureRegistries;

@SideOnly(Side.CLIENT)
public class ClientProxy implements IProxy {
    @Override
    public void preInit( ) {
        for (IFeatureRegistry fr : featureRegistries) {
            if (SimpleRandomStuff.shouldLoad(fr)) {
                fr.registerClientEvents();
                fr.registerRenders();
            }
        }
    }

    @Override
    public void init( ) {
        /* NO OPERATION */
    }
}
