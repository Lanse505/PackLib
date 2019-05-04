package lanse505.packlib.common.villages;

import lanse505.packlib.PackLib;
import lanse505.packlib.utils.PLConfigs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class VillageGenHandler {
    private static Map<String, String> villageValues = new HashMap<>();

    static {
        villageValues.put("size", String.valueOf(PLConfigs.PLConfig.villageValues.size));
        villageValues.put("distance", String.valueOf(PLConfigs.PLConfig.villageValues.distance));
    }

    MapGenVillage villageGenerator = new MapGenVillage(villageValues) {
        @Override
        protected StructureStart getStructureStart(int chunkX, int chunkZ) {
            return new VillageGenStart(this.world, this.rand, chunkX, chunkZ, PLConfigs.PLConfig.villageValues.size);
        }
    };

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
