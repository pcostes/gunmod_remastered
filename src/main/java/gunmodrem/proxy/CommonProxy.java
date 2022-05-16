package gunmodrem.proxy;

import gunmodrem.Main;
import gunmodrem.client.renders.RenderEntityBullet;
import gunmodrem.objects.entities.EntityBullet;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import gunmodrem.util.ItemInit;

public class CommonProxy {

	
	public void preInit(FMLPreInitializationEvent event)
    {
    	// init and register
		
    	ItemInit.registerAll();
    	EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", 0, Main.instance, 64, 10, true); // 0 = entity ID
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RenderEntityBullet());
    }
	
	public void init(FMLInitializationEvent event)
    {
    	
    }
	
	public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
