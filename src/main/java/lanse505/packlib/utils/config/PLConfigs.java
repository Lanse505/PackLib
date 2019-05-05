package lanse505.packlib.utils.config;

import lanse505.packlib.PackLib;
import lanse505.packlib.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class PLConfigs {
    public static void readConfigPreInit() {
        Configuration packlib = CommonProxy.general;
        Configuration village = CommonProxy.village;
        try {
            packlib.load();
            village.load();
            PLConfigurations.initVillageConfigPreInit(village);
        } catch (Exception error) {
            PackLib.logger.log(Level.ERROR, "Problem Loading Config!", error);
        } finally {
            if (packlib.hasChanged() || village.hasChanged()) {
                packlib.save();
                village.save();
            }
        }
    }

    public static void readConfigInit() {
        Configuration packlib = CommonProxy.general;
        Configuration village = CommonProxy.village;
        try {
            packlib.load();
            village.load();
        } catch (Exception error) {
            PackLib.logger.log(Level.ERROR, "Problem Loading Config!", error);
        } finally {
            if (packlib.hasChanged() || village.hasChanged()) {
                packlib.save();
                village.save();
            }
        }
    }

    public static void readConfigPostInit() {
        Configuration packlib = CommonProxy.general;
        Configuration village = CommonProxy.village;
        try {
            packlib.load();
            village.load();
            PLConfigurations.initVillageConfigPost(village);
        } catch (Exception error) {
            PackLib.logger.log(Level.ERROR, "Problem Loading Config!", error);
        } finally {
            if (packlib.hasChanged() || village.hasChanged()) {
                packlib.save();
                village.save();
            }
        }
    }
}
