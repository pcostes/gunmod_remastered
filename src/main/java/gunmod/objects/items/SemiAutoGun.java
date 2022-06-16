package gunmod.objects.items;

import gunmod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class SemiAutoGun extends ItemGun {

	int ticksSinceShot = -1;
	int cooldown = 20;
	boolean hasCooldown = false;
	
	
	// Constructor
	public SemiAutoGun(String name) {
		super(name);
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!worldIn.isRemote)
		{
			this.ticksSinceShot++;
		}
	}
	
	@Override
	protected void postShot(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (!worldIn.isRemote)
		{
			this.ticksSinceShot = 0;
		}
		playerIn.setActiveHand(handIn);
	}
	
	// Called before other things happen on right click of the gun. The shot will fail if this returns false.
	@Override
	protected boolean preShot(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if (this.hasCooldown)
		{
			if (this.ticksSinceShot >= cooldown || this.ticksSinceShot == -1)
			{
				return true;
			}
			return false;
		}
		return true;
	}
}
