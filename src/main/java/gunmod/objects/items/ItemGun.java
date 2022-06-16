package gunmod.objects.items;

import gunmod.util.item.InventoryHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemGun extends ItemBase {

	public ItemGun(String name) {
		super(name);
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed.
	 * Args: itemStack, world, entityPlayer
	 */

	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (this.preShot(worldIn, playerIn, handIn) == false)
		{
			return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
		}
		
		ItemStack ammoStack = ItemStack.EMPTY;
		// If player is not in creative mode, require ammo
		if (!playerIn.capabilities.isCreativeMode) {
			ammoStack = InventoryHelper.getItemStack(playerIn, this.getAmmoItem());
			// If they don't have ammo then fail
			if (ammoStack.isEmpty()) {
				return new ActionResult(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
			}
			// Take one ammo away
			ammoStack.shrink(1);
		}

		this.playShootingSound(worldIn, playerIn);
		
		if (!worldIn.isRemote) {
			this.shootProjectile(worldIn, playerIn);
		}
		this.postShot(worldIn, playerIn, handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
	
	protected boolean preShot(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		return true;
	}
	
	protected void postShot(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	// Can be overridden to customize sound
	public void playShootingSound(World worldIn, EntityPlayer playerIn) {
		worldIn.playSound((EntityPlayer) null, playerIn.posX, playerIn.posY, playerIn.posZ,
				SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F,
				0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
	}

	public Item getAmmoItem()
	{
		return Items.SNOWBALL;
	}

	protected void shootProjectile(World worldIn, EntityPlayer playerIn) {
		EntitySnowball entitysnowball = new EntitySnowball(worldIn, playerIn);
		//entitysnowball.posY += playerIn.eyeHeight;
		entitysnowball.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
		worldIn.spawnEntity(entitysnowball);
	}

	
}
