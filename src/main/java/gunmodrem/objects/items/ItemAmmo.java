package gunmodrem.objects.items;

public class ItemAmmo extends ItemBase 
{
	private int damage;
	private static int instanceCount = 0;
	
	public ItemAmmo()
	{
		super("ItemAmmo" + instanceCount++);
		damage = 10;
		
	}
	
	// p_damage stands for parameter_damage
	public ItemAmmo(String name, int p_damage)
	{
		super(name);
		this.damage = p_damage;
	}
	
	public int getDamage()
	{
		return damage;
	}
}
