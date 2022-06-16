package gunmod.util.handlers;

import net.minecraft.network.NetworkManager;
import net.minecraftforge.common.network.ServerToClientConnectionEstablishedHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;

@EventBusSubscriber
public class PacketHandler {
	
	public static NetworkManager networkManager;
	// ServerConnectedFromClinet
	@SubscribeEvent
	public void onClientConnectedToServer(ClientConnectedToServerEvent event)
	{
		networkManager = event.getManager();
	}
}
