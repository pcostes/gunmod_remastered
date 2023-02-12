package gunmod.networking;

import net.minecraft.util.EnumParticleTypes;

public class SenderMethods {
	public static void sendParticlePacket(EnumParticleTypes particle, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount)
	{
		ModPacketHandler.INSTANCE.sendToAll(new MessageParticle(particle.getParticleID(), ignoreRange, x, y, z, xSpeed, ySpeed, zSpeed, amount));
	}
}
