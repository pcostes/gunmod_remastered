package gunmod.util;

import net.minecraft.util.math.Vec3d;

public class VectorMath {
	
	public static double[] vectorToDegrees(Vec3d normalizedVector)
	{
		double yaw = 0;
		double pitch = 0;
		
		if (normalizedVector.z > 0) 
		{
			yaw = -Math.toDegrees(Math.atan(normalizedVector.x / normalizedVector.z));
		}
		else if (normalizedVector.z < 0) 
		{
			yaw = 180 - Math.toDegrees(Math.atan(normalizedVector.x / normalizedVector.z));
		}
		else 
		{
			if (normalizedVector.x == 0)
			{
				yaw = 0;
			}
			else if (normalizedVector.x > 0)
			{
				yaw = -90;
			}
			else
			{
				yaw = 90;
			}
		}
		
		double radius = Math.hypot(normalizedVector.x, normalizedVector.z);
		if (normalizedVector.y == 0 && radius == 0)
			pitch = 0;
		else if (radius == 0)
			pitch = 90 * normalizedVector.y / Math.abs(normalizedVector.y);
		else
			pitch = Math.toDegrees(Math.atan(normalizedVector.y / radius));
		
		return new double[] {yaw, pitch};
	}
	
}
