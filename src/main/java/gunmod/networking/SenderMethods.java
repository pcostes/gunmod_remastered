package gunmod.networking;

import net.minecraft.util.EnumParticleTypes;

public class SenderMethods {
	public static void sendParticlePacket(int particleID, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount)
	{
		//ModPacketHandler.INSTANCE.sendToAll(new MessageShatterJar(particleID, ignoreRange, x, y, z, xSpeed, ySpeed, zSpeed, amount));
	}
}
