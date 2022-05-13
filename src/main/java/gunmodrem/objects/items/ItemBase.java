package gunmodrem.objects.items;

import cpw.mods.fml.common.registry.GameRegistry;
import gunmodrem.util.ItemInit;
import net.minecraft.item.Item;

public class ItemBase extends Item 
{
	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		ItemInit.itemList.add(this);
	}
}
