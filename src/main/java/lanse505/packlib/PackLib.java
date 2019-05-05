package lanse505.packlib;

import lanse505.packlib.common.villages.VillageGenHandler;
import lanse505.packlib.proxy.CommonProxy;
import lanse505.packlib.utils.PLConfigs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = PackLib.MODID, name = PackLib.NAME, version = PackLib.VERSION)
public class PackLib
{
    public static final String MODID = "packlib";
    public static final String NAME = "PackLib";
    public static final String VERSION = "1.0.0";

    public static Logger logger;
    public static Random random;

    @SidedProxy(clientSide = "lanse505.packlib.proxy.ClientProxy", serverSide = "lanse505.packlib.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        random = new Random();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
