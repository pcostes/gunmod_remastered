package gunmod.networking;

import gunmod.networking.MessageShatterJar.MessageShatterJarHandler;
import gunmod.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ModPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("gm");
	
	public static void registerMessages()
	{
		ModPacketHandler.INSTANCE.registerMessage(MessageShatterJarHandler.class, MessageShatterJar.class, Reference.packetId + 1, Side.CLIENT);
	}
}
