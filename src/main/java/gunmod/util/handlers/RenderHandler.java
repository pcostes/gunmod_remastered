package gunmod.util.handlers;

import gunmod.entity.render.RenderAngryBee;
import gunmod.entity.render.RenderBee;
import gunmod.entity.render.RenderBullet;
import gunmod.entity.render.RenderLaser;
import gunmod.init.ItemInit;
import gunmod.objects.entities.EntityAngryBee;
import gunmod.objects.entities.EntityBee;
import gunmod.objects.entities.EntityBullet;
import gunmod.objects.entities.EntityJar;
import gunmod.objects.entities.EntityLaser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new IRenderFactory<EntityBullet>()
		{
			@Override
			public Render<? super EntityBullet> createRenderFor(RenderManager manager) {
				return new RenderBullet(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new IRenderFactory<EntityLaser>()
		{
			@Override
			public Render<? super EntityLaser> createRenderFor(RenderManager manager) {
				return new RenderLaser(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBee.class, new IRenderFactory<EntityBee>()
		{
			@Override
			public Render<? super EntityBee> createRenderFor(RenderManager manager) {
				return new RenderBee(manager);
			}
		});
		
		RenderingRegistry.registerEntityRenderingHandler(EntityAngryBee.class, new IRenderFactory<EntityAngryBee>()
		{
			@Override
			public Render<? super EntityAngryBee> createRenderFor(RenderManager manager) {
				return new RenderAngryBee(manager);
			}
		});
		
		
	}
	
	public static void registerProjectileRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityJar.class, new RenderSnowball<EntityJar>(Minecraft.getMinecraft().getRenderManager(), ItemInit.ITEM_JAR, Minecraft.getMinecraft().getRenderItem()));
	}
}
