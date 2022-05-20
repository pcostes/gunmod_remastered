package gunmodrem.objects.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gunmodrem.util.ItemInit;
import gunmodrem.util.Reference;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class ItemBase extends Item 
{
	
	public ItemBase(String name)
	{
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName(name);
		setTextureName(Reference.MODID + ":" + this.getUnlocalizedName().substring(5));
		ItemInit.itemList.add(this);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		this.itemIcon = register.registerIcon(Reference.MODID + ":" + this.getUnlocalizedName().substring(5));
	}
}
