package gunmod.objects.items;

import gunmod.Main;
import gunmod.objects.entities.EntityJar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemJar extends ItemBase {

	public ItemJar(String name) {
		super(name);
		this.maxStackSize = 16;
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		
        if (!playerIn.capabilities.isCreativeMode)
        {
        	itemStack.shrink(1);
        }

        worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!worldIn.isRemote)
        {
            EntityJar entityJar = new EntityJar(worldIn, playerIn);
            entityJar.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 1.0F); // Velocity is 1.5 before. TEMP COMMENT
            worldIn.spawnEntity(entityJar);
        }
        
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }
}
