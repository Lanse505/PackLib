package lanse505.packlib.proxy;

import lanse505.packlib.utils.PLConfigsNew;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Config;
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
        PLConfigsNew.readConfigInit();
    }

    public void postInit(FMLPostInitializationEvent event) {
        PLConfigsNew.readConfigPostInit();
    }
}
