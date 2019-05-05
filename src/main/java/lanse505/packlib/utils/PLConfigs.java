package lanse505.packlib.utils;

import lanse505.packlib.PackLib;
import lanse505.packlib.proxy.CommonProxy;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;

public class PLConfigs {
    private static final String CATEGORY_VILLAGE = "village";

    // Village Values
    public static int distance;
    public static int size;
    public static Map<String, Boolean> handler = new HashMap<>();
    public static String[] handlerVals;

    public static void readConfigInit() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initVillageConfigInit(cfg);
        } catch (Exception error) {
            PackLib.logger.log(Level.ERROR, "Problem Loading Config!", error);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    public static void readConfigPostInit() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initVillageConfigPost(cfg);
        } catch (Exception error) {
            PackLib.logger.log(Level.ERROR, "Problem Loading Config!", error);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initVillageConfigInit(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_VILLAGE, "Village Config");
        distance = cfg.getInt("distance", CATEGORY_VILLAGE, 32, 1, Integer.MAX_VALUE, "Vanilla Default: 32");
        size = cfg.getInt("size", CATEGORY_VILLAGE, 1, 1, Integer.MAX_VALUE, "Vanilla Default Village Size: 1");
    }

    private static void initVillageConfigPost(Configuration cfg) {
        whitelistPopulate();
        Property generateDefaults = cfg.get("general", "generateDefaults", true, "Regenerate Default Village Values");
        if (generateDefaults.getBoolean()) {
            for (Map.Entry<String, Boolean> entry : handler.entrySet()) {
                String category = "villageBooleans." + entry.getKey();
                cfg.getBoolean("enabled", category, entry.getValue(), "Is This Enabled:");
            }
        }
    }

    public static void whitelistPopulate() {
        for (StructureVillagePieces.PieceWeight weight : StructureVillagePieces.getStructureVillageWeightedPieceList(PackLib.random, PLConfigs.size)) {
            PLConfigs.handler.put(weight.villagePieceClass.getName(), true);
        }
    }

}
