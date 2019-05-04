package lanse505.packlib.common.villages;

import lanse505.packlib.utils.PLConfigs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureStart;
import net.minecraft.world.gen.structure.StructureVillagePieces;

import java.util.List;
import java.util.Random;

public class VillageGenStart extends StructureStart {
    /** well ... thats what it does */
    private boolean hasMoreThanTwoComponents;

    public VillageGenStart() {}

    public VillageGenStart(World world, Random random, int x, int z, int size) {
        super(x, z);
        List<StructureVillagePieces.PieceWeight> list = StructureVillagePieces.getStructureVillageWeightedPieceList(random, size);

        for (StructureVillagePieces.PieceWeight piece : list) {
            if (!PLConfigs.PLConfig.villageValues.handler.get(piece.villagePieceClass.getName())) {
                list.remove(piece);
            }
        }

        StructureVillagePieces.Start structurevillagepieces$start = new StructureVillagePieces.Start(world.getBiomeProvider(), 0, random, (x << 4) + 2, (z << 4) + 2, list, size);
        this.components.add(structurevillagepieces$start);
        structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, random);
        List<StructureComponent> list1 = structurevillagepieces$start.pendingRoads;
        List<StructureComponent> list2 = structurevillagepieces$start.pendingHouses;

        while (!list1.isEmpty() || !list2.isEmpty()) {
            if (list1.isEmpty()) {
                int i = random.nextInt(list2.size());
                StructureComponent structurecomponent = list2.remove(i);
                structurecomponent.buildComponent(structurevillagepieces$start, this.components, random);
            } else {
                int j = random.nextInt(list1.size());
                StructureComponent structurecomponent2 = list1.remove(j);
                structurecomponent2.buildComponent(structurevillagepieces$start, this.components, random);
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
