package gunmod.objects.entities;

import java.util.List;

import javax.annotation.Nullable;

import gunmod.Main;
import gunmod.util.entity.EntityHelper;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityLaser extends Entity implements IProjectile {

	public final float BOUNDING_BOX_RADIUS = 3;
	
	double destX;
	double destY;
	double destZ;
	public double shooterEyeHeight;
	public EntityPlayer shooter;
	
	// Constructors
	public EntityLaser(World worldIn) {
		super(worldIn);
		this.setSize(0.25F, 0.25F);
		this.rotationYaw = 20;
	}
	
	public EntityLaser(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		this.setPosition(x, y, z);
	}
	
	public EntityLaser(World worldIn, EntityPlayer thrower)
	{
		this(worldIn, thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ);
		this.shooter = thrower;
		
		this.shooterEyeHeight = thrower.getEyeHeight();
	}


	@Override
	public void shoot(double destX, double destY, double destZ, float speed, float inaccuracy) {
		
		Vec3d destination = this.determineDestination(new Vec3d(destX, destY, destZ), 200);
		if (!(destination == null)) {
			this.shootTo(destination, speed);
			return;
		}
		this.shootTo(new Vec3d(destX, destY, destZ), speed);
	}
	
	@Nullable
	public Vec3d determineDestination(Vec3d playerLooking, int targetDistance)
	{
		Vec3d difference = new Vec3d(playerLooking.x - this.posX, playerLooking.y - this.posY, playerLooking.z - this.posZ);
		Vec3d normalized = difference.normalize();
		double posX = this.posX;
		double posY = this.posY;
		double posZ = this.posZ;
		while(true)
		{
			// Move down the path
			posX += normalized.x;
			posY += normalized.y;
			posZ += normalized.z;
			
			// Check for entities
			List<Entity> entities = this.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(posX - BOUNDING_BOX_RADIUS, posY - BOUNDING_BOX_RADIUS, posZ - BOUNDING_BOX_RADIUS, posX + BOUNDING_BOX_RADIUS, posY + BOUNDING_BOX_RADIUS, posZ + BOUNDING_BOX_RADIUS));
				
			// If entity found in box, then get the closest one and break
			if (!entities.isEmpty())
			{
				Entity closestEntity = EntityHelper.getClosestEntity(new Vec3d(posX, posY, posZ), entities);
				if (closestEntity != null && closestEntity instanceof EntityLivingBase)
				{
					EntityLivingBase closestLivingEntity = (EntityLivingBase) closestEntity;
					if (closestLivingEntity != this.shooter && closestLivingEntity.getHealth() > 0)
					{	
						closestEntity.attackEntityFrom(DamageSource.FALL, 10F);
						return closestEntity.getPositionVector();
					}
					
				}
				
			}
			
			// If we've gone to far from the player, return null
			if (this.getDistance(posX, posY, posZ) > targetDistance)
			{
				return null;
			}
		}
	}
	
	public void shootTo(Vec3d destination, float speed)
	{
		Vec3d difference = new Vec3d(destination.x - this.posX, destination.y - this.posY, destination.z - this.posZ);
		Vec3d normalized = difference.normalize();
		this.motionX = normalized.x * speed;
		this.motionY = normalized.y * speed;
		this.motionZ = normalized.z * speed;
	}
	// Don't need anything here
	@Override
	protected void entityInit() {
		
	}

	// TODO Implement these methods once variables are made
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		
	}
	
	public void onImpact(Entity entity)
	{
		
		if (entity != null)
		{
			entity.attackEntityFrom(DamageSource.FALL, 1);
			//entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.world.getPlayerEntityByName(this.shooterName)), 10);
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox() {
		
		return this.getEntityBoundingBox();
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		double particleX = this.posX;
		double particleY = this.posY;
		double particleZ = this.posZ;
		this.world.spawnParticle(EnumParticleTypes.DRIP_LAVA, this.posX, this.posY, this.posZ, 0, 0, 0);
		/**for (int i = 0; i < 7; i++)
		{
			particleX -= this.motionX / 8;
			particleY -= this.motionY / 8;
			particleZ -= this.motionZ / 8;
			this.world.spawnParticle(EnumParticleTypes.DRIP_LAVA, particleX, particleY, particleZ, 0, 0, 0);
		}**/
		

		this.updateMotion();

		
		// Kill myself if its been 8 seconds
		if (this.ticksExisted > 160)
		{
			this.setDead();
		}
	}

	public void updateMotion()	{
		
		// Update position, motion and acceleration
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		// Update bounding box (because it doesn't do it automatically)
 		this.setEntityBoundingBox(this.getEntityBoundingBox().offset(this.motionX, this.motionY, this.motionZ));
	}
	
	public void shoot(BlockPos blockPos, float speed) {
		this.shoot(blockPos.getX(), blockPos.getY(), blockPos.getZ(), speed, 1);
	}

	public void checkCollision()
	{
		List<Entity> entities = this.world.getEntitiesWithinAABB(Entity.class, this.getCollisionBoundingBox());
		if (!entities.isEmpty())
		{
			entities.remove(this);
			for (Entity entity : entities)
			{
				Main.logger.info(entity.getClass().getName());
			}
			Entity entity = EntityHelper.getClosestEntity(this.getPositionVector(), entities);
			//if (!(entity.getName().equals(this.shooterName)));
			//{
				this.onImpact(entity);
			//}
		}
	}
}
