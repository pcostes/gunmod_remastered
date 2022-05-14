package gunmodrem.objects.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable implements IProjectile {

	public EntityBullet(World world)
	{
		super(world);
	}
	
	
	public EntityBullet(World world, EntityLivingBase entity)
    {
        super(world, entity);
        this.rotationPitch = entity.rotationPitch;
        this.rotationYaw = entity.rotationYaw;
        
        
    }
	
	public EntityBullet(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}
	
	protected void onImpact(MovingObjectPosition pos)
    {
		// check if hit a block before setting air?
		this.worldObj.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
        if (pos.entityHit != null)
        {
            pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10); // change to class 
        }

        for (int i = 0; i < 8; i++)
        {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        
        // set dead after impact (if server)
        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
	
	public void setThrowableHeading(double p_70186_1_, double p_70186_3_, double p_70186_5_, float p_70186_7_, float p_70186_8_)
    {
        float f2 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_3_ * p_70186_3_ + p_70186_5_ * p_70186_5_);
        p_70186_1_ /= (double)f2;
        p_70186_3_ /= (double)f2;
        p_70186_5_ /= (double)f2;
        p_70186_1_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_3_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_5_ += this.rand.nextGaussian() * (double)(this.rand.nextBoolean() ? -1 : 1) * 0.007499999832361937D * (double)p_70186_8_;
        p_70186_1_ *= (double)p_70186_7_;
        p_70186_3_ *= (double)p_70186_7_;
        p_70186_5_ *= (double)p_70186_7_;
        this.motionX = p_70186_1_;
        this.motionY = p_70186_3_;
        this.motionZ = p_70186_5_;
        float f3 = MathHelper.sqrt_double(p_70186_1_ * p_70186_1_ + p_70186_5_ * p_70186_5_);
        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_70186_1_, p_70186_5_) * 180.0D / Math.PI);
        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_70186_3_, (double)f3) * 180.0D / Math.PI);
    }
}
