package gunmod.objects.entities;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityAngryBee extends EntityZombie {

	int temp = 1;
	
	public EntityAngryBee(World worldIn) {
		super(worldIn);
		this.setSize(0.1F, 0.1F);
	}
	
	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}
	
	@Override
	public float getEyeHeight() {
		return 0.1F;
	}

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.45);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    }
    
	@Override
	protected SoundEvent getAmbientSound()
    { 	
        return SoundEvents.ENTITY_ENDERDRAGON_GROWL;
    }
	
	@Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_BLAZE_HURT;
    }

	@Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_CAT_DEATH;
    }
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		this.playSound(SoundEvents.ENTITY_ENDERDRAGON_FLAP, 0.15F, 1.0F);
	}
	
	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (temp == 200)
		{
			temp = 1;
		}
		else
		{
			temp ++;
		}
		/*if (world.isBlockFullCube(new BlockPos(this.posX, this.posY - 2, this.posZ)))
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
		}*/
	}
}
