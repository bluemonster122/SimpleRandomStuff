package bluemonster122.mods.simplerandomstuff.handler;

import bluemonster122.mods.simplerandomstuff.SimpleRandomStuff;
import bluemonster122.mods.simplerandomstuff.core.FRCore;
import bluemonster122.mods.simplerandomstuff.reference.ModInfo;
import bluemonster122.mods.simplerandomstuff.util.IFeatureRegistry;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;
import java.util.HashMap;

@Mod.EventBusSubscriber
public class ConfigurationHandler {
    public static final ConfigurationHandler INSTANCE = new ConfigurationHandler();
    public Configuration configuration;

    public void init(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }

    private void loadConfiguration( ) {
        for (IFeatureRegistry fr : SimpleRandomStuff.featureRegistries) {
            if (fr instanceof FRCore) FeatureLoad.put(fr, true);
            FeatureLoad.put(fr, configuration.getBoolean("Enable", fr.getName(), true, "Set to false to disable this part of the mod."));
            if (FeatureLoad.get(fr)) {
                fr.loadConfigs(configuration);
            }
        }
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(ModInfo.MOD_ID)) {
            loadConfiguration();
        }
    }

    private ConfigurationHandler( ) {
    }

    public static HashMap<IFeatureRegistry, Boolean> FeatureLoad = new HashMap<>();
}
