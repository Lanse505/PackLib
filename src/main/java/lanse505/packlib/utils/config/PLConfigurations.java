package lanse505.packlib.utils.config;

import lanse505.packlib.PackLib;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.config.Configuration;

import java.util.HashMap;
import java.util.Map;

public class PLConfigurations {
    private static final String CATEGORY_VILLAGE = "village";
    private static final String CATEGORY_JSON = "json";

    // Village Values
    public static boolean generateDefaults;
    public static int distance;
    public static int size;
    public static Map<String, Boolean> handler = new HashMap<>();

    public static void initVillageConfigPreInit(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_JSON, "JSON Controls");
        generateDefaults = cfg.getBoolean("generateDefaults", CATEGORY_JSON, true, "Regenerate Default Village Values");

        cfg.addCustomCategoryComment(CATEGORY_VILLAGE, "Village Config");
        distance = cfg.getInt("distance", CATEGORY_VILLAGE, 32, 1, Integer.MAX_VALUE, "Vanilla Default: 32");
        size = cfg.getInt("size", CATEGORY_VILLAGE, 1, 1, Integer.MAX_VALUE, "Vanilla Default Village Size: 1");
    }

    public static void initVillageConfigPost(Configuration cfg) {
        whitelistPopulate();
        if (generateDefaults) {
            for (Map.Entry<String, Boolean> entry : handler.entrySet()) {
                String category = "villageBooleans." + entry.getKey();
                cfg.getBoolean("enabled", category, entry.getValue(), "Is This Enabled:");
            }
        }
    }

    private static void whitelistPopulate() {
        for (StructureVillagePieces.PieceWeight weight : StructureVillagePieces.getStructureVillageWeightedPieceList(PackLib.random, size)) {
            handler.put(weight.villagePieceClass.getName(), true);
        }
    }
}
