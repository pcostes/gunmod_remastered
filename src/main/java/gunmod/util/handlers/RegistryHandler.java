package gunmod.util.handlers;

import gunmod.init.BlockInit;
import gunmod.init.EntityInit;
import gunmod.init.ItemInit;
import gunmod.networking.ModPacketHandler;
import gunmod.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ItemInit.Items.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(BlockInit.Blocks.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for (Item item : ItemInit.Items)
		{
			if (item instanceof IHasModel)
			{
				((IHasModel) item).registerModels();
			}
		}
		
		for (Block block : BlockInit.Blocks)
		{
			if (block instanceof IHasModel)
			{
				((IHasModel) block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		EntityInit.registerEntities();
		RenderHandler.registerEntityRenders();
		ModPacketHandler.registerMessages();
	}
	
	public static void initRegistries()
	{
		RenderHandler.registerProjectileRenders();
	}
	
	@SubscribeEvent
	public void onRenderPlayer(RenderPlayerEvent.Pre event)
	{
		event.getEntityPlayer().swingArm(EnumHand.MAIN_HAND);
	}
}
