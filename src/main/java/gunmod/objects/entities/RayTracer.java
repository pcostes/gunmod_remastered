package gunmod.objects.entities;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RayTracer extends Entity {

	public int maxTicks = 100;
	
	public RayTracer(World worldIn) {
		super(worldIn);
	}

	public RayTracer(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
	}
	
	public RayTracer(World worldIn, double x, double y, double z, int maxTicks)
	{
		this(worldIn, x, y, z);
		this.maxTicks = maxTicks;
	}
	
	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		
	}

	public void shootTo(double destX, double destY, double destZ, float speed)
	{
		Vec3d difference = new Vec3d(destX - this.posX, destY - this.posY, destZ - this.posZ);
		Vec3d normalized = difference.normalize();
		this.motionX = normalized.x;
		this.motionY = normalized.y;
		this.motionZ = normalized.z;
	}
	
	@Override
	public final void onUpdate() {
		if (this.ticksExisted >= this.maxTicks)
		{
			this.setDead();
		}
		this.tracerUpdate();
	}
	
	public void tracerUpdate()
	{
		
	}
}
