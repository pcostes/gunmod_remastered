package gunmod.objects.items;

import gunmod.objects.entities.EntityLaser;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class GunMandalorian extends SemiAutoGun {

	public GunMandalorian(String name) {
		super(name);
		this.hasCooldown = true;
		this.cooldown = 2;
	}
	
	@Override
	protected void shootProjectile(World worldIn, EntityPlayer playerIn) {
		RayTraceResult result = playerIn.rayTrace(100, 1);
		EntityLaser laser = new EntityLaser(worldIn, playerIn);
		laser.shoot(result.getBlockPos(), 3.5F); // Speed is 4.5F
		worldIn.spawnEntity(laser);
	}
}
