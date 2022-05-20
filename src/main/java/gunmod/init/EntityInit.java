package gunmod.init;

import gunmod.Main;
import gunmod.objects.entities.EntityBullet;
import gunmod.objects.entities.EntityJar;
import gunmod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	public static void registerEntities()
	{
		registerEntity("bullet", EntityBullet.class, Reference.ENTITY_BULLET_ID, 50);
		registerProjectile("jar", Reference.ENTITY_JAR_ID, EntityJar.class, ItemInit.ITEM_JAR);
	}
	
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true);
	}
	
	private static void registerProjectile(String name, int id, Class<? extends Entity> entity, Item item)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(name), entity, name, id, Main.instance, 64, 10, true);
	}
}
