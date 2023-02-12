package gunmod.util.handlers;

import gunmod.util.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class LootTableHandler {
	public static final ResourceLocation BABY_YODA = LootTableList.register(new ResourceLocation(Reference.MODID, "baby_yoda"));
}
