package gunmod.objects.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {

	public EntityBullet(World worldIn)
    {
        super(worldIn);
    }

    public EntityBullet(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityBullet(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    @Override
    protected void onImpact(RayTraceResult result) {
    	if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 10);
        }
    	else // can't destroy anything harder than an ender chest
		{
    		float hardness = world.getBlockState(result.getBlockPos()).getBlockHardness(world, result.getBlockPos());
    		if (hardness == -1 || hardness < 20F)
    			this.world.setBlockToAir(result.getBlockPos());
		}
    	
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3); // does nothing?
            this.setDead();
        }
    }
}
