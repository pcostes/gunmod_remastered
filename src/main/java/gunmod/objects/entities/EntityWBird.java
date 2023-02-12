package gunmod.objects.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityWBird extends EntityProjectile {

	EntityLivingBase target;
	
	public EntityWBird(World world)
	{
		super(world);
	}
	
	public EntityWBird(World worldIn, EntityLivingBase target) {
		super(worldIn);
		this.target = target;
		this.setSize(0.0875F, 0.02625F);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0, 0, 0);
	}
}
