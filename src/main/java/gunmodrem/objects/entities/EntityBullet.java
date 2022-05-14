package gunmodrem.objects.entities;

import gunmodrem.objects.items.ItemAmmo;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityBullet extends EntityThrowable {
	public final ItemAmmo AMMO_TYPE;

	
	public EntityBullet(World world, EntityLivingBase entity, ItemAmmo ammoType)
    {
        super(world, entity);
        AMMO_TYPE = ammoType;
    }
	
	protected void onImpact(MovingObjectPosition pos)
    {
		// check if hit a block before setting air?
		this.worldObj.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
        if (pos.entityHit != null)
        {
            pos.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), AMMO_TYPE.getDamage()); // change to class 
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
}
