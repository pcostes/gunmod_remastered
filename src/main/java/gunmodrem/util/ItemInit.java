package gunmodrem.util;

import java.util.ArrayList;

import cpw.mods.fml.common.registry.GameRegistry;
import gunmodrem.objects.items.ItemGun;
import gunmodrem.objects.items.ItemBase;
import net.minecraft.item.Item;

public class ItemInit 
{
	// Master item list
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	
	// Ammo
	public static final Item AMMO_PISTOL = new ItemBase("ammo_pistol");
	
	// Guns
	public static final Item GUN_PISTOL = new ItemGun("gun_pistol", AMMO_PISTOL);
	
	// Other
	public static Item testItem = new ItemBase("TestItem");
	
	
	// Register all items
	public static void registerAll()
	{
		for (Item i : itemList)
		{
			System.out.println(Reference.MODID + ": Registering " + i.getUnlocalizedName());
			GameRegistry.registerItem(i,  i.getUnlocalizedName(), null);//GameRegistry.registerItem(i,  i.getUnlocalizedName().substring(5), null);
		}
	}
}
