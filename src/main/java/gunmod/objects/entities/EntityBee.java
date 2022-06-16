package gunmod.objects.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.reflect.internal.util.Position;
import scala.tools.nsc.typechecker.SuperAccessors.SuperAccTransformer;

public class EntityBee extends EntityCow
{
	public EntityBee(World worldIn) {
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}
	
	@Override
	public float getEyeHeight() {
		return 0.1F;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
	}
	
	@Override
	public EntityCow createChild(EntityAgeable ageable) {
		return new EntityBee(world);
	}
	
	@Override
	protected SoundEvent getAmbientSound()
    { 	
        return SoundEvents.ENTITY_PARROT_AMBIENT;
    }
	
	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_PARROT_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_ENDERDRAGON_GROWL;
    }
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_ENDERMEN_HURT, 0.15F, 1.0F);
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (world.isBlockFullCube(new BlockPos(this.posX, this.posY - 2, this.posZ)))
		{
			this.motionY = 0.5F;
		}
		else if (!world.isBlockFullCube(new BlockPos(this.posX, this.posY - 3, this.posZ)))
		{
			this.motionY = -0.2F;
		}
		else
		{
			this.motionY = 0;
		}
		
		
	}
}

