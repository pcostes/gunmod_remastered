package gunmod.init;

import java.util.ArrayList;
import java.util.List;

import gunmod.objects.entities.EntityBullet;
import gunmod.objects.items.AmmoBase;
import gunmod.objects.items.GunPistol;
import gunmod.objects.items.ItemBase;
import gunmod.objects.items.ItemGun;
import gunmod.objects.items.ItemJar;
import net.minecraft.item.Item;

public class ItemInit {
	public static List<Item> Items = new ArrayList<Item>();
	
	/*------------------ GUN STUFF -------------------*/
	
	// ITEMS
	public static final Item AMMO_PISTOL = new ItemBase("ammo_pistol");
	public static final Item GUN_PISTOL = new GunPistol("gun_pistol");
	
	/*------------------ BEE STUFF -------------------*/
	
	// ITEMS
	public static final Item ITEM_JAR = new ItemJar("item_jar");
}
