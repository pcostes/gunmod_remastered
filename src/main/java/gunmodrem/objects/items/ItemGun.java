package gunmodrem.objects.items;

import gunmodrem.objects.entities.EntityBullet;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGun extends ItemBase {
	//private static final String __OBFID = "CL_00000069"; -- Don't know what this does
	public final Item AMMO_TYPE;

    public ItemGun(String name, Item ammoType)
    {
    	super(name);
        this.maxStackSize = 1;
        this.AMMO_TYPE = ammoType;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode || player.inventory.hasItem(this.AMMO_TYPE))
        {
        	if (!player.capabilities.isCreativeMode)
        	{
        		player.inventory.consumeInventoryItem(this.AMMO_TYPE);
        	}
        	

            if (!world.isRemote) // if server world
            {
                world.spawnEntityInWorld(new EntityBullet(world, player));
            }
        }

        return itemStack;
    }
}
