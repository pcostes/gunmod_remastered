package gunmod.networking;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;

public class SenderMethods {
	public static void sendParticlePacketToAll(EnumParticleTypes particle, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount)
	{
		ModPacketHandler.INSTANCE.sendToAll(new MessageParticle(particle.getParticleID(), ignoreRange, x, y, z, xSpeed, ySpeed, zSpeed, amount));
	}
	
	public static void sendParticlePacketToPlayer(EnumParticleTypes particle, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount, EntityPlayerMP player)
	{
		ModPacketHandler.INSTANCE.sendTo(new MessageParticle(particle.getParticleID(), ignoreRange, x, y, z, xSpeed, ySpeed, zSpeed, amount), player);
	}
}
