package lanse505.packlib.utils;

import lanse505.packlib.PackLib;
import lanse505.packlib.proxy.CommonProxy;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PLConfigsNew {
    private static final String CATEGORY_VILLAGE = "village";

    // Village Values
    public static int distance = 32;
    public static int size = 1;
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
        Map<String, String> stringMap = new LinkedHashMap<>();
        for (Map.Entry<String, Boolean> entry : handler.entrySet()) {
            stringMap.put(entry.getKey(), entry.getValue().toString());
        }

        String[] keys = new String[stringMap.size()];
        String[] values = new String[stringMap.size()];
        String[] cfgValues = new String[stringMap.size()];

        for (int i = 0; i < stringMap.keySet().size(); i++) {
            keys[i] = (String) stringMap.keySet().toArray()[i];
        }

        for (int i = 0; i < stringMap.values().size(); i++) {
            Boolean val = (Boolean) stringMap.values().toArray()[i];
            values[i] = val.toString();
        }

        for (int i = 0; i < stringMap.size(); i++) {
            String builder = keys[i] + ":" + values[i];
            cfgValues[i] = builder;
        }

        handlerVals = cfg.getStringList("handler", CATEGORY_VILLAGE, cfgValues, "Village Component Values");
    }

    public static void whitelistPopulate() {
        List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(PackLib.random, PLConfigs.PLConfig.villageValues.size);
        for (StructureVillagePieces.PieceWeight weight : list) {
            PLConfigs.PLConfig.villageValues.handler.put(weight.villagePieceClass.getName(), true);
        }
    }

}
