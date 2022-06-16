package gunmod.objects.items;

import gunmod.init.ItemInit;
import net.minecraft.item.Item;

public class GunPistol extends SemiAutoGun {

	public GunPistol(String name) {
		super(name);
		this.hasCooldown = true;
	}

	@Override
	public Item getAmmoItem() {
		return ItemInit.AMMO_PISTOL;
	}
}
