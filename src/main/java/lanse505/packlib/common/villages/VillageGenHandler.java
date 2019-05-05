package lanse505.packlib.common.villages;

import lanse505.packlib.PackLib;
import lanse505.packlib.utils.config.PLConfigs;
import lanse505.packlib.utils.config.PLConfigurations;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class VillageGenHandler {
    private static Map<String, String> villageValues = new HashMap<>();
    private static MapGenVillage villageGenerator = new MapGenVillage(villageValues) {
        @Override
        protected StructureStart getStructureStart(int chunkX, int chunkZ) {
            return new VillageGenStart(this.world, this.rand, chunkX, chunkZ, PLConfigurations.size);
        }
    };

    static {
        villageValues.put("size", String.valueOf(PLConfigurations.size));
        villageValues.put("distance", String.valueOf(PLConfigurations.distance));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onVillageGen(InitMapGenEvent event) {
        if (event.getType() == InitMapGenEvent.EventType.VILLAGE) {
            if (event.getOriginalGen() != event.getNewGen()) {
                PackLib.logger.warn("Village Generation Event's New Generation has been Overwritten by PackLib's Generation");
                PackLib.logger.warn("This is not a bug or issue, but is printed as a message for other developers in-case they have a question");
            }
            event.setNewGen(villageGenerator);
        }
    }
}
