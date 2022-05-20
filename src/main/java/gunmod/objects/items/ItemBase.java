package gunmod.objects.items;

import gunmod.Main;
import gunmod.init.ItemInit;
import gunmod.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
	
	// Normal constructor for items
	public ItemBase(String name)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(CreativeTabs.MATERIALS);
		
		ItemInit.Items.add(this);
	}
	
	// Constructor for other bases (ammobase, foodbase etc..)
	protected ItemBase(String name, boolean isInCreativeTab)
	{
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		
		if (isInCreativeTab)
		{
			this.setCreativeTab(CreativeTabs.MATERIALS);
		}
		
		ItemInit.Items.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
