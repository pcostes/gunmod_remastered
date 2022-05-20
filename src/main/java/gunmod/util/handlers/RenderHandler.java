package gunmod.util.handlers;

import gunmod.entity.renders.RenderBullet;
import gunmod.init.ItemInit;
import gunmod.objects.entities.EntityBullet;
import gunmod.objects.entities.EntityJar;
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
		
		RenderingRegistry.registerEntityRenderingHandler(EntityJar.class, new RenderSnowball<EntityJar>(Minecraft.getMinecraft().getRenderManager(), ItemInit.ITEM_JAR, Minecraft.getMinecraft().getRenderItem()));
	}
}
