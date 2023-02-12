package gunmod.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageParticle implements IMessage {
	
	int particleID;
	boolean ignoreRange;
	private double x;
	private double y;
	private double z;
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	int amount;
	
	// A default constructor is always required
	public MessageParticle() 
	{

	}

	

	public MessageParticle(int particleID, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int amount) {
		this.particleID = particleID;
		this.ignoreRange = ignoreRange;
		this.x = x;
		this.y = y;
		this.z = z;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.zSpeed = zSpeed;
		this.amount = amount;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// Writes the int into the buf
		buf.writeInt(this.particleID);
		buf.writeBoolean(this.ignoreRange);
		buf.writeDouble(this.x);
		buf.writeDouble(this.y);
		buf.writeDouble(this.z);
		buf.writeDouble(this.xSpeed);
		buf.writeDouble(this.ySpeed);
		buf.writeDouble(this.zSpeed);
		buf.writeInt(this.amount);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// Reads the int back from the buf. Note that if you have multiple values, you
		// must read in the same order you wrote.
		buf.readInt();
		buf.readBoolean();
		buf.readDouble();
		buf.readDouble();
		buf.readDouble();
		buf.readDouble();
		buf.readDouble();
		buf.readDouble();
		buf.readInt();
	}

	// The params of the IMessageHandler are <REQ, REPLY>
	// second is the packet you are returning.
	// The returned packet can be used as a "response" from a sent packet.
	public static class MessageParticleHandler implements IMessageHandler<MessageParticle, IMessage> {
		// Do note that the default constructor is required, but implicitly defined in
		// this case

		@Override
		public IMessage onMessage(MessageParticle message, MessageContext ctx) {
			int particleID = message.particleID;
			boolean ignoreRange = message.ignoreRange;
			double x = message.x;
			double y = message.y;
			double z = message.z;
			double xSpeed = message.xSpeed;
			double ySpeed = message.ySpeed;
			double zSpeed = message.zSpeed;
			int amount = message.amount;
			EnumParticleTypes particleType = EnumParticleTypes.getParticleFromId(particleID);
			
			Minecraft.getMinecraft().addScheduledTask(new Runnable() { 
				@Override
				public void run() {
					WorldClient minecraft = Minecraft.getMinecraft().world;
					
					minecraft.spawnParticle(particleType, ignoreRange, (float) x, (float) y, (float) z, xSpeed, ySpeed, zSpeed);
				}
			});
			
			//ctx.getServerHandler().player.getServerWorld().spawnParticle(EnumParticleTypes.SPIT, true, player.posX, player.posY, player.posZ, 0, 0, 0, 0, 20, null);

			// The value that was sent

			// No response packet
			return null;
		}
	}
}
