package gunmod.util.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;

public class InventoryHelper {

	public static ItemStack getItemStack(EntityPlayer player, Item item) {
		if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() == item) {
			return player.getHeldItem(EnumHand.MAIN_HAND);
		} else if (player.getHeldItem(EnumHand.OFF_HAND).getItem() == item) {
			return player.getHeldItem(EnumHand.OFF_HAND);
		} else {
			for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
				ItemStack itemstack = player.inventory.getStackInSlot(i);

				if (itemstack.getItem() == item) {
					return itemstack;
				}
			}

		}
		return ItemStack.EMPTY;
	}

}
