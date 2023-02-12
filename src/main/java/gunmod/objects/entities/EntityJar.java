package gunmod.objects.entities;

import gunmod.Main;
import gunmod.init.ItemInit;
import gunmod.networking.MessageParticle;
import gunmod.networking.ModPacketHandler;
import gunmod.util.handlers.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketParticles;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityJar extends EntityThrowable
{
	EntityLivingBase myThrower;
	boolean hasLeftPlayer = false;
	int damage = 4;
	
    public EntityJar(World worldIn)
    {
        super(worldIn);
    }

    public EntityJar(World worldIn, EntityLivingBase throwerIn)
    {	
    	super(worldIn, throwerIn);
    	myThrower = throwerIn;
    }
    

    public EntityJar(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    public int getChanceOfBreaking()
    {
    	return 80;
    }
    
	/*public void sendShatterPacket(EntityJar me)
	{
		if (!this.world.isRemote)
		{
			this.world.getMinecraftServer().addScheduledTask(new Runnable() {
				
				@Override
				public void run() {
					Main.instance.logger.info("The packet was at least sent");
					
					PacketHandler.networkManager.sendPacket(new SPacketParticles();
				}
			});		
		}
	}*/
    
    
    protected void onImpact(RayTraceResult result)
    {
    	//this.sendShatterPacket();
    	if (this.world.isRemote) {
    		//Main.logger.info("spawning particles on client side at " + this.posY);
            //this.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, this.posX, this.posY, this.posZ, 0, 0, 0, Item.getIdFromItem(Items.EGG));
    	}
    	else 
    	{
    		//Main.logger.info("it was on the server");
    	}
    	// If i hit a mob
    	if (result.entityHit != null)
        {
    		// If the mob is a bee then drop a bottled bee
    		if (result.entityHit instanceof EntityBee)
    		{
    			if (!this.world.isRemote)
            	{
    		    	EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.ITEM_BOTTLED_BEE, 1));
    		        entityItem.setPickupDelay(10);
    		        entityItem.motionY = 0;
    		        this.world.spawnEntity(entityItem);
    		        result.entityHit.setDead();
            	}
    		}
    		else
    		{
    			// Else do damage to the mob and shatter the jar
    			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
    			this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.7F, 0.8F);
    			if (this.hasLeftPlayer)
    			{
	    			/*for (int i = 0; i < 8; ++i)
	                {
	                    this.world.spawnParticle(EnumParticleTypes.SPIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
	                }*/
    			}
    		}
        
        }
    	// Since I hit the ground, have a chance of shattering
    	else
    	{
    		// Random chance that I break or not
    		//if (rand.nextInt(100) <= this.getChanceOfBreaking())
    		if (this.getPosition().getX() == 10)
    		{
    			//Main.logger.info("It breaks");
    			this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 0.7F, 0.8F);
    			/*for (int i = 0; i < 8; ++i)
                {
                    this.world.spawnParticle(EnumParticleTypes.SPIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                }*/
    		}
    		// If I don't shatter, then drop the item
    		else 
    		{
    			//Main.logger.info("It doesn't breaks");
    			if (!this.world.isRemote)
            	{
    		    	EntityItem entityItem = new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(ItemInit.ITEM_JAR, 1));
    		        entityItem.setPickupDelay(10);
    		        this.world.spawnEntity(entityItem);
            	}
    		}
    	}
    	
    	// Kill myself
    	if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
    }
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	if (this.hasLeftPlayer == false)
    	{
    		if (this.thrower == null)
    		{
    			this.hasLeftPlayer = true;
    			return;
    		}
    		if (this.thrower.getDistance(this) <= 1.2F)
    		{
    			this.hasLeftPlayer = true;
    		}
    	}
    }
    
    /*@SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                this.world.spawnParticle(EnumParticleTypes.SPIT, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }*/
}
