package lanse505.packlib.utils;

import lanse505.packlib.PackLib;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.config.Config;

import java.util.*;

public class PLConfigs {


    public static void whitelistPopulate() {
        List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(PackLib.random, PLConfig.villageValues.size);
        for (StructureVillagePieces.PieceWeight weight : list) {
            PLConfig.villageValues.handler.put(weight.villagePieceClass.getName(), true);
        }
    }

    @Config(modid = PackLib.MODID, name = PackLib.NAME, type = Config.Type.INSTANCE)
    public static class PLConfig {
        public static VillageValues villageValues = new VillageValues();
    }

    public static class VillageValues {
        @Config.RangeInt(min = 1)
        @Config.Comment("Vanilla Default: 32")
        public int distance = 32;

        @Config.RangeInt(min = 1)
        @Config.Comment("Vanilla Default Village Size: 1")
        public int size = 1;

        @Config.Comment("Village Structure Handler")
        public Map<String, Boolean> handler = new HashMap<>();
    }
}
