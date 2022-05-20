package gunmod.objects.items;

import gunmod.Main;
import gunmod.init.ItemInit;
import gunmod.objects.entities.EntityBullet;
import gunmod.util.IHasModel;
import net.minecraft.item.Item;

public class AmmoBase extends ItemBase {
	EntityBullet entityToSpawn;
	
	public AmmoBase(String name, EntityBullet bulletEntity) {
		super(name, false);
		this.entityToSpawn = bulletEntity;
	}
	
	public EntityBullet getEntityToSpawn()
	{
		return entityToSpawn;
	}
}
