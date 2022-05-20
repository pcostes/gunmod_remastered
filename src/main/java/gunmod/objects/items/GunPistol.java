package gunmod.objects.items;

import gunmod.Main;
import gunmod.init.ItemInit;
import gunmod.objects.entities.EntityBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class GunPistol extends ItemGun {

	// Constructor
	public GunPistol(String name) {
		super(name);
	}
	
	// Controls which projectile is shot out of gun and how that bullet is shot
	@Override
	public void shootProjectile(World worldIn, EntityPlayer playerIn)
	{
		EntityBullet bullet = new EntityBullet(worldIn, playerIn);
		bullet.posY += 1.5;
		bullet.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
        worldIn.spawnEntity(bullet);
	}

	// Controls which ammo is used
	@Override
	public boolean itemIsAmmo(Item ammo)
	{
		// Not sure if 'ammo == ItemInit.AMMO_PISTOL' works. I have seen 'instanceof' used in minecraft src, but i can't use that here. I do not know exacly what '==' does in Java in this case.
		if (ammo == ItemInit.AMMO_PISTOL)
		{
			return true;
		}
		
		return false;
	}
	
	// Called if the player can shoot (even if he doesn't have ammo), before the shot happens
	@Override
	public boolean canShoot()
	{
		if (this.canShoot)
		{
			return true;
		}
		return false;
	}
	
	

}
