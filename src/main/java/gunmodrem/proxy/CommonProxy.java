package gunmodrem.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import gunmodrem.Main;
import gunmodrem.objects.items.ItemBase;
import gunmodrem.util.ItemInit;
import net.minecraft.item.Item;

public class CommonProxy {

	
	public void preInit(FMLPreInitializationEvent event)
    {
    	// init and register
    	ItemInit.registerAll();
    }
	
	public void init(FMLInitializationEvent event)
    {
    	
    }
	
	public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}
