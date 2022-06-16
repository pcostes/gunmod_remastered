package gunmod.init;

import gunmod.Main;
import gunmod.objects.entities.EntityAngryBee;
import gunmod.objects.entities.EntityBee;
import gunmod.objects.entities.EntityBullet;
import gunmod.objects.entities.EntityJar;
import gunmod.objects.entities.EntityLaser;
import gunmod.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
	
	// Register all entities
	public static void registerEntities()
	{
		registerEntity("bullet", EntityBullet.class, Reference.ENTITY_BULLET_ID, 50);
		registerEntityWithEgg("bee", EntityBee.class, Reference.ENTITY_BEE_ID, 50, 8619279, 2039583);
		registerEntityWithEgg("angry_bee", EntityAngryBee.class, Reference.ENTITY_ANGRY_BEE_ID, 50, 8619279, 12525335);
		registerProjectile("jar", Reference.ENTITY_JAR_ID, EntityJar.class, ItemInit.ITEM_JAR);
		registerEntity("laser", EntityLaser.class, Reference.ENTITY_LASER_ID, 50);
	}
	
	
	// For mobs without spawn eggs
	private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true);
	}
	
	// For mobs with spawn eggs
	private static void registerEntityWithEgg(String name, Class<? extends Entity> entity, int id, int range, int mainEggColor, int secondEggColor)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, mainEggColor, secondEggColor);
	}
	
	// For projectiles
	private static void registerProjectile(String name, int id, Class<? extends Entity> entity, Item item)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(name), entity, name, id, Main.instance, 64, 10, true);
	}
}
