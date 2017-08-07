package bluemonster122.mods.simplerandomstuff.overlayoverhaul;

import bluemonster122.mods.simplerandomstuff.reference.Names;
import bluemonster122.mods.simplerandomstuff.util.IFeatureRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FROverlays implements IFeatureRegistry {
    public static final FROverlays INSTANCE = new FROverlays();
    public static boolean useCustom = false;
    public static boolean showLevelUp = false;

    @Override
    public void registerBlocks( ) {

    }

    @Override
    public void registerItems( ) {

    }

    @Override
    public void registerRecipes( ) {

    }

    @Override
    public void registerTileEntities( ) {

    }

    @Override
    public void loadConfigs(Configuration configuration) {
        useCustom = configuration.getBoolean(Names.Features.Configs.OVERLAYS_USE_CUSTOM_OVERLAYS, Names.Features.OVERLAYS, true, "Set to false to disable custom overlay.");
        showLevelUp = configuration.getBoolean(Names.Features.Configs.OVERLAYS_SHOW_LVLUPMSG, Names.Features.OVERLAYS, true, "Set to false to disable level up message.");
    }

    @Override
    public void registerEvents( ) {
    }

    @Override
    public void registerOreDict( ) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerRenders( ) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerClientEvents( ) {
        MinecraftForge.EVENT_BUS.register(new OverlayOverriders());
    }

    @Override
    public String getName( ) {
        return Names.Features.OVERLAYS;
    }

    private FROverlays( ) {
    }
}
