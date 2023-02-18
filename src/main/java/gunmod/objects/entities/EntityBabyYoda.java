package gunmod.objects.entities;

import javax.annotation.Nullable;

import gunmod.Main;
import gunmod.entity.ai.EntityAITakeShinies;
import gunmod.entity.ai.EntityAIUseForce;
import gunmod.entity.enums.BYodaState;
import gunmod.util.handlers.LootTableHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityBabyYoda extends EntityTameable
{
	private static final DataParameter<Integer> STATE = EntityDataManager.<Integer>createKey(EntityBabyYoda.class, DataSerializers.VARINT);
	private static final DataParameter<ItemStack> HELD_ITEM = EntityDataManager.<ItemStack>createKey(EntityBabyYoda.class, DataSerializers.ITEM_STACK);

    public EntityBabyYoda(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 0.85F);
        this.setTamed(false);
    }

    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(2, new EntityAIUseForce(this));
        this.tasks.addTask(4, new EntityAITakeShinies(this));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));
    }

    protected void entityInit()
    {
        super.entityInit();
		this.dataManager.register(STATE, Integer.valueOf(0));
		this.getDataManager().register(HELD_ITEM, ItemStack.EMPTY);
    }
    
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_PARROT_AMBIENT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_PARROT_DEATH;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_PARROT_HURT;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
		
	}
    
    @Nullable
    protected ResourceLocation getLootTable()
    {
    	return LootTableHandler.BABY_YODA;
    }

    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }
    
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (this.isTamed())
        {
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack))
            {
            	// WOLF CODE
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget((EntityLivingBase)null);
                
                // BABY YODA CODE
    			if (this.isSitting())
    			{
    				this.dataManager.set(STATE, BYodaState.NORMAL.getId());	
    			}
    			else
    			{
    				this.dataManager.set(STATE, BYodaState.CLOSED.getId());
    			}
            }
        }
        else if (itemstack.getItem() == Items.DIAMOND)
        {
            if (!player.capabilities.isCreativeMode)
            {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote)
            {
                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }
    
    /*	DATA MANAGER GETTERS AND SETTERS	*/
    
    // Baby yoda state
	public BYodaState getState()
	{
		return BYodaState.getById(this.dataManager.get(STATE));
	}
	
	public void setState(BYodaState state)
	{
		this.dataManager.set(STATE, state.getId());
	}
	
	// Held item
	public ItemStack getHeldItemStack()
	{
		return this.dataManager.get(HELD_ITEM);
	}
	
	public void setHeldItemStack(ItemStack itemStack)
	{
		this.dataManager.set(HELD_ITEM, itemStack);
	}

	// BABY YODA CAN NOT HAVE CHILDREN
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
}
