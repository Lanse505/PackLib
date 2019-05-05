package lanse505.packlib.proxy;

import lanse505.packlib.utils.PLConfigs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent event) {
        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "PackLib.cfg"));
    }

    public void init(FMLInitializationEvent event) {
        PLConfigs.readConfigInit();
    }

    public void postInit(FMLPostInitializationEvent event) {
        PLConfigs.readConfigPostInit();
    }
}
