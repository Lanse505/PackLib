package lanse505.packlib.compats.oreexcavation.filters;

import net.minecraft.advancements.Advancement;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import oreexcavation.events.IExcavateFilter;
import oreexcavation.handlers.MiningAgent;

public class AdvancementFilter implements IExcavateFilter {
    private final Advancement advancement;
    private final IBlockState target;

    public AdvancementFilter(Advancement advancement, IBlockState target) {
        this.advancement = advancement;
        this.target = target;
    }

    @Override
    public boolean canHarvest(EntityPlayerMP entityPlayerMP, MiningAgent miningAgent, BlockPos blockPos) {
        World world = entityPlayerMP.getEntityWorld();
        IBlockState state = world.getBlockState(blockPos);
        return state.equals(target) && entityPlayerMP.getAdvancements().getProgress(advancement).isDone();
    }
}
