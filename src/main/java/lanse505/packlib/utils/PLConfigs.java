package lanse505.packlib.utils;

import lanse505.packlib.PackLib;
import net.minecraftforge.common.config.Config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PLConfigs {

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

        @Config.Comment("Village Structure Blacklist")
        public List<String> blacklist = new ArrayList<>();
    }
}
