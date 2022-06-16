package gunmod.util.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class EntityHelper {
	
	@Nullable
	public static Entity getClosestEntity(Vec3d pos, List<Entity> entities)
	{
		Double closestDistance = null;
		Entity closestEntity = null;
		for (Entity entity : entities)
		{
			if (closestDistance == null || entity.getDistance(pos.x, pos.y, pos.z) < closestDistance.doubleValue())
			{
				closestDistance = new Double(entity.getDistance(pos.x, pos.y, pos.z));
				closestEntity = entity;
				continue;
			}
		}
		if (!(closestEntity == null))
		{
			return closestEntity;
		}
		return null;
	}
}
