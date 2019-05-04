package lanse505.packlib.compats.oreexcavation;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oreexcavation.events.EventExcavate;
import oreexcavation.handlers.MiningAgent;

public class ExcavatorHandler {
    @SubscribeEvent
    public void onExcavation(EventExcavate.Pre event) {
        MiningAgent agent = event.getAgent();
        EntityPlayer player = agent.player;
    }
}
