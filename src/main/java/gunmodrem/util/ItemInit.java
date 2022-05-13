package gunmodrem.util;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import gunmodrem.objects.items.ItemBase;
import net.minecraft.item.Item;

public class ItemInit 
{
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	
	public static Item testItem = new ItemBase("TestItem");
	
	
	public static void registerAll()
	{
		for (Item i : itemList)
		{
			GameRegistry.registerItem(i,  i.getUnlocalizedName().substring(5), null);
		}
	}
}
