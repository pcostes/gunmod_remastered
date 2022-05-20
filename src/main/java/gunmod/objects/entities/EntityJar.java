package gunmod.objects.entities;

import java.util.Random;

import gunmod.init.ItemInit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityJar extends EntityThrowable
{
	int damage = 4;
    public EntityJar(World worldIn)
    {
        super(worldIn);
    }

    public EntityJar(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityJar(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    protected void onImpact(RayTraceResult result)
    {
    	// Play sound
    	this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.7F, 0.8F);
    	
    	// If it hit an entity  damage that entity
        if (result.entityHit != null)
        {
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }
        else
        {
        	if (!this.world.isRemote)
        	{
		    	EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.ITEM_JAR, 1));
		        entityItem.setPickupDelay(10);
		        this.world.spawnEntity(entityItem);
        	}
        }
        
        // Kill myself
        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SPIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
