package lanse505.packlib.event;

import lanse505.packlib.PackLib;
import lanse505.packlib.utils.PLConfigs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

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
            return new Start(this.world, this.rand, chunkX, chunkZ, PLConfigs.PLConfig.villageValues.size);
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

    public static class Start extends StructureStart {
        /** well ... thats what it does */
        private boolean hasMoreThanTwoComponents;

        public Start() {
        }

        public Start(World worldIn, Random rand, int x, int z, int size) {
            super(x, z);
            List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(rand, size);
            for (StructureVillagePieces.PieceWeight piece : list) {
                if (piece.villagePieceClass.)
            }
            StructureVillagePieces.Start structurevillagepieces$start = new StructureVillagePieces.Start(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
            this.components.add(structurevillagepieces$start);
            structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
            List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
            List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;

            while (!list1.isEmpty() || !list2.isEmpty()) {
                if (list1.isEmpty()) {
                    int i = rand.nextInt(list2.size());
                    StructureComponent structurecomponent = list2.remove(i);
                    structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
                } else {
                    int j = rand.nextInt(list1.size());
                    StructureComponent structurecomponent2 = list1.remove(j);
                    structurecomponent2.buildComponent(structurevillagepieces$start, this.components, rand);
                }
            }

            this.updateBoundingBox();
            int k = 0;

            for (StructureComponent structurecomponent1 : this.components) {
                if (!(structurecomponent1 instanceof StructureVillagePieces.Road)) {
                    ++k;
                }
            }

            this.hasMoreThanTwoComponents = k > 2;
        }

        /**
         * currently only defined for Villages, returns true if Village has more than 2 non-road components
         */
        public boolean isSizeableStructure() {
            return this.hasMoreThanTwoComponents;
        }

        public void writeToNBT(NBTTagCompound tagCompound) {
            super.writeToNBT(tagCompound);
            tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
        }

        public void readFromNBT(NBTTagCompound tagCompound) {
            super.readFromNBT(tagCompound);
            this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
        }
    }
}
