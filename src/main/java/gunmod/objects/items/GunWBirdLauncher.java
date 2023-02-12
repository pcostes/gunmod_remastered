package gunmod.objects.items;

import java.util.List;

import gunmod.objects.entities.EntityWBird;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class GunWBirdLauncher extends SemiAutoGun {

	public GunWBirdLauncher(String name) {
		super(name);
		this.hasCooldown = true;
		this.cooldown = 20;
	}

	@Override
	protected void shootProjectile(World worldIn, EntityPlayer player) {
		final int bbRadius = 5;
		List<EntityLivingBase> entities = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, player, new AxisAlignedBB(player.posX - bbRadius, player.posY - 1, player.posZ - bbRadius, player.posX + bbRadius, player.posY + bbRadius, player.posZ + bbRadius));
		EntityWBird wBird = new EntityWBird(worldIn);
		wBird.setPosition(player.posX, player.posY, player.posZ);
		worldIn.spawnEntity(wBird);
	}
}
