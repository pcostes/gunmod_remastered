package gunmod.objects.items;

import gunmod.Main;
import gunmod.init.ItemInit;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.legacydev.MainClient;

public class ItemGun extends ItemBase {
	protected boolean canShoot;
	protected float bulletSpeed = 4.0F; // speed the bullet travels

	public ItemGun(String name) {
		super(name);
		this.canShoot = true;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack gunStack = new ItemStack(this, 1);
		//if (this.canShoot)
		//{
			Main.logger.info("On item right click made it");
			playerIn.setActiveHand(handIn);
			this.canShoot = false;
			ItemStack ammoStack = ItemStack.EMPTY; // new ammoStack that we will fill with ammo by searching
	        
	        // If player is not in creative mode, require ammo and fail if not found
	        if (!playerIn.capabilities.isCreativeMode)
	        {
	        	ammoStack = findAmmo(playerIn);
	        	if (ammoStack.isEmpty())
	        	{
	        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, gunStack);
	        	}
	            ammoStack.shrink(1); // eventually change to durability hit?
	            
	        }

	        this.playMySoundEffect(worldIn, playerIn);

	        if (!worldIn.isRemote)
	        {
	        	this.shootProjectile(worldIn, playerIn);
	        }

	        playerIn.addStat(StatList.getObjectUseStats(this)); // what is the purpose of this line
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, gunStack);
		//}
	        
		//return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, gunStack);
		
		// ammoStack is where ammo is found in the player's inventory, and gunStack is the itemstack that is put into the player's hand after he shoots
        
    }
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		this.canShoot = true;
		Main.logger.info("Player stopped using");
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 72000;
    }
	
	// Can be overriden to customize sound
	public void playMySoundEffect(World worldIn, EntityPlayer playerIn)
	{
		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	}
	
	public boolean itemIsAmmo(Item ammo)
	{
		if (ammo instanceof ItemSnowball)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canShoot()
	{
		return true;
	}
	
	protected void shootProjectile(World worldIn, EntityPlayer playerIn) {
		EntitySnowball entitysnowball = new EntitySnowball(worldIn, playerIn);
		entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
        worldIn.spawnEntity(entitysnowball);
	}

	protected ItemStack findAmmo(EntityPlayer player)
    {
        if (this.itemIsAmmo(player.getHeldItem(EnumHand.MAIN_HAND).getItem()))
        {
            return player.getHeldItem(EnumHand.MAIN_HAND);
        }
        else if (this.itemIsAmmo(player.getHeldItem(EnumHand.OFF_HAND).getItem()))
        {
            return player.getHeldItem(EnumHand.OFF_HAND);
        }
        else
        {
            for (int i = 0; i < player.inventory.getSizeInventory(); ++i)
            {
                ItemStack itemstack = player.inventory.getStackInSlot(i);

                if (this.itemIsAmmo(itemstack.getItem()))
                {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }
	
    protected boolean isArrow(ItemStack stack)
    {
        return stack.getItem() instanceof ItemArrow;
    }
}
