package gunmod.entity.ai;

import java.util.List;
import java.util.Random;

import gunmod.entity.enums.BYodaState;
import gunmod.objects.entities.EntityBabyYoda;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class EntityAIUseForce extends EntityAIBase {

	public static final int FORCE_COOLDOWN = 60;
	
	protected Random rand = new Random();
	public int timeExecuting = 0;
	public int forceTimer = 0;
	EntityBabyYoda babyYoda;
	List<EntityMob> targets;
	byte fireBallRounds = 0;
	boolean doingFireballAttack = false;
	
	public EntityAIUseForce(EntityBabyYoda babyYoda)
	{
		this.babyYoda = babyYoda;
	}
	
	@Override
	public void updateTask() {
		this.timeExecuting++;
		
		// If fireball attack is on, then do fireball attack, but only every tenth tick
		if (this.doingFireballAttack && this.timeExecuting % 10 == 0)
		{
			for (EntityMob target : this.targets)
			{
				Vec3d acceleration = new Vec3d(target.posX - this.babyYoda.posX, target.posY - (this.babyYoda.posY + 5), target.posZ - this.babyYoda.posZ).normalize();
				EntitySmallFireball fireball = new EntitySmallFireball(this.babyYoda.world, this.babyYoda.posX, this.babyYoda.posY + 5, this.babyYoda.posZ, acceleration.x, acceleration.y, acceleration.z);
				this.babyYoda.world.spawnEntity(fireball);
			}
			this.fireBallRounds++;
		}
		
		if (this.timeExecuting == 38)
		{
			for (EntityMob target : this.targets)				
			{
				target.removeActivePotionEffect(MobEffects.LEVITATION);
				target.motionY = -2;
			}
		}
	}
	
	@Override
	public boolean shouldExecute() {
		if (!this.babyYoda.isTamed() && this.babyYoda.getState() == BYodaState.NORMAL)
		{
			return false;
		}
		
		EntityLivingBase owner = this.babyYoda.getOwner();
		
		if (owner == null)
		{
			return false;
		}
		
		Vec3d pos = this.babyYoda.getPositionVector();
		
		if (owner.getHealth() / owner.getMaxHealth() < 2 && !this.babyYoda.world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.x - 5, pos.y - 1, pos.z - 5, pos.x + 5, pos.y + 5, pos.z + 5)).isEmpty())// && this.babyYoda.getRNG().nextInt(0) == 3)
		{
			if (!(this.forceTimer >= FORCE_COOLDOWN))
			{
				forceTimer ++;
				return false;
			}
			
			forceTimer = 0;
			return true;
		}
		return false;
	}

	
	@Override
	public void startExecuting() {
		this.babyYoda.setState(BYodaState.ATTACK);
		this.babyYoda.setSitting(false);
		Vec3d pos = this.babyYoda.getPositionVector();
		this.targets = this.babyYoda.world.getEntitiesWithinAABB(EntityMob.class, new AxisAlignedBB(pos.x - 5, pos.y - 1, pos.z - 5, pos.x + 5, pos.y + 5, pos.z + 5));
		this.doRandomAbility();		
	}
	
	@Override
	public void resetTask() {
		if (this.babyYoda.getState() == BYodaState.ATTACK)
		{
			this.babyYoda.setState(BYodaState.NORMAL);
		}
		this.babyYoda.setEntityInvulnerable(false);
		this.timeExecuting = 0;
		this.fireBallRounds = 0;
		this.doingFireballAttack = false;
		for (EntityMob entity : this.targets)
		{
			entity.removeActivePotionEffect(MobEffects.LEVITATION);
		}
		this.targets.clear();
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		if (this.babyYoda.getState() != BYodaState.ATTACK)
		{
			return false;
		}
		
		if (this.doingFireballAttack && this.fireBallRounds != 5)
		{
			return true;
		}
		
		if (this.timeExecuting > 40)
		{
			return false;
		}

		return true;
	}
	
	protected void doRandomAbility()
	{
		this.babyYoda.setEntityInvulnerable(true);
		this.babyYoda.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 140, 1, false, false));
		int randInt = rand.nextInt(3);
		if (randInt == 0)
		{
			for (EntityMob entity : this.targets)
			{
				EntityLightningBolt lightning = new EntityLightningBolt(entity.world, entity.posX, entity.posY, entity.posZ, true);
				entity.world.addWeatherEffect(lightning);
				
				entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 15);
			}
		}
		else if (randInt == 1)
		{
			for (EntityMob entity : this.targets)
			{
				entity.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200, 10));
			}
		}
		else
		{
			this.doingFireballAttack = true;
		}	
	}
}
