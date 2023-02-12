package gunmod.init;

import java.util.ArrayList;
import java.util.List;

import gunmod.objects.items.GunMandalorian;
import gunmod.objects.items.GunPistol;
import gunmod.objects.items.GunWBirdLauncher;
import gunmod.objects.items.ItemBase;
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
	public static final Item ITEM_BOTTLED_BEE = new ItemBase("item_bottled_bee").setMaxStackSize(16);
	
	/*----------------- MANDO STUFF ------------------*/
	public static final Item GUN_MANDALORIAN = new GunMandalorian("gun_mandalorian");
	public static final Item W_BIRD_LAUNCHER = new GunWBirdLauncher("w_bird_launcher");
}
