package lanse505.packlib.compats.oreexcavation.filters;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import oreexcavation.events.IExcavateFilter;
import oreexcavation.handlers.MiningAgent;

public class ExcavationFilter implements IExcavateFilter {
    private final IBlockState target;

    public ExcavationFilter(IBlockState target) {
        this.target = target;
    }

    @Override
    public boolean canHarvest(EntityPlayerMP entityPlayerMP, MiningAgent miningAgent, BlockPos blockPos) {
        return false;
    }

}
