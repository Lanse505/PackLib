package lanse505.packlib.proxy;

import lanse505.packlib.utils.config.PLConfigs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {
    public static File packlib;
    public static File villageFolder;
    public static Configuration general;
    public static Configuration village;

    public void preInit(FMLPreInitializationEvent event) {
        packlib = new File(event.getModConfigurationDirectory().getPath(), "packlib");
        villageFolder = new File(packlib.getPath(), "village");
        general = new Configuration(new File(packlib.getPath(), "PackLib.cfg"));
        village = new Configuration(new File(villageFolder.getPath(), "village.cfg"));

        PLConfigs.readConfigPreInit();
    }

    public void init(FMLInitializationEvent event) {
        PLConfigs.readConfigInit();
    }

    public void postInit(FMLPostInitializationEvent event) {
        PLConfigs.readConfigPostInit();
    }
}
