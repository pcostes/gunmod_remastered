package gunmod.entity.ai;

import java.util.List;
import java.util.Random;

import gunmod.entity.enums.BYodaState;
import gunmod.networking.SenderMethods;
import gunmod.objects.entities.EntityBabyYoda;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class EntityAITakeShinies extends EntityAIBase {
	
	EntityBabyYoda babyYoda;
	int ticksExecuting = 0;
	public static final Item LAPIS = new ItemStack(Items.DYE, 1, EnumDyeColor.BLUE.getDyeDamage()).getItem();
	private Random rand = new Random();
	
	public EntityAITakeShinies(EntityBabyYoda babyYoda)
	{
		this.babyYoda = babyYoda;
	}
	
	@Override
	public boolean shouldExecute() {
		if (!this.babyYoda.isTamed() || this.babyYoda.getOwner() == null || this.babyYoda.getState() != BYodaState.NORMAL)
		{
			return false;
		}
		
		List<EntityItem> items = this.babyYoda.world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(this.babyYoda.posX - 0.5, this.babyYoda.posY, this.babyYoda.posZ - 0.5, this.babyYoda.posX + 0.5,  this.babyYoda.posY + 0.5, this.babyYoda.posZ + 0.5));
		if (items.isEmpty())
		{
			return false;
		}
		
		for (EntityItem entity : items)
		{
			Item item = entity.getItem().getItem();
			if (item == Items.COAL || item == Items.IRON_INGOT || item == Items.GOLD_INGOT || item == LAPIS || item == Items.REDSTONE || item == Items.EMERALD || item == Items.DIAMOND)
			{
				this.babyYoda.setHeldItemStack(new ItemStack(entity.getItem().getItem()));
				entity.getItem().shrink(1);
				return true;
			}
		}
		return false;
	}

	@Override
	public void startExecuting() {
		this.babyYoda.setState(BYodaState.HAPPY);
	}
	
	@Override
	public void updateTask() {
		this.ticksExecuting++;
		
		if (this.ticksExecuting == 30)
		{
			if (this.babyYoda.getHeldItemStack().getItem() == Items.COAL)
			{
				EntityItem entityItem = new EntityItem(this.babyYoda.world, this.babyYoda.posX, this.babyYoda.posY, this.babyYoda.posZ, new ItemStack(Items.COAL)) {
					@Override
					public void onUpdate()
					{
						super.onUpdate();
						if (!this.world.isRemote)
						{
							SenderMethods.sendParticlePacket(EnumParticleTypes.SMOKE_NORMAL, false, this.posX, this.posY, this.posZ, (this.rand.nextDouble() * 2) - 0.5, this.rand.nextDouble(), (this.rand.nextDouble() * 2) - 0.5, 1);
						}
					}
				};
				this.babyYoda.world.playSound(this.babyYoda.posX, this.babyYoda.posY, this.babyYoda.posZ, SoundEvents.ENTITY_GHAST_DEATH, SoundCategory.MUSIC, 10, 1, false);
				entityItem.motionY = 1;
				entityItem.setPickupDelay(60);
				this.babyYoda.world.spawnEntity(entityItem);
				this.babyYoda.setHeldItemStack(ItemStack.EMPTY);
			}
			else
			{
				this.giveEffect();
			}
		}
	}
	
	@Override
	public boolean shouldContinueExecuting() {
		if (this.babyYoda.getState() != BYodaState.HAPPY)
		{
			return false;
		}
		
		if (this.ticksExecuting >= 160)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public void resetTask() {
		this.ticksExecuting = 0;
		
		if (this.babyYoda.getState() == BYodaState.HAPPY)
		{
			this.babyYoda.setState(BYodaState.NORMAL);
		}
	}
	
	protected void giveEffect()
	{
		List<EntityPlayer> players = this.babyYoda.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(this.babyYoda.posX - 5, this.babyYoda.posY, this.babyYoda.posZ - 5, this.babyYoda.posX + 5,  this.babyYoda.posY + 5, this.babyYoda.posZ + 5));
		for (EntityPlayer player : players)
		{
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 200, 2));
		}
	}
}
