package lanse505.packlib.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {
    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }

    @Override
    public void registerEvents() {
        super.registerEvents();
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public void syncJEI(EntityPlayer player) {
        super.syncJEI(player);

    }
}
