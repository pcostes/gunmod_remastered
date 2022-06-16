package gunmod.util.block;

import net.minecraft.util.math.AxisAlignedBB;

public class BlockHelper {
	public static AxisAlignedBB getNewBoundingBoxFromSixteenths(double x1, double y1, double z1, double x2, double y2, double z2)
	{
		return new AxisAlignedBB(x1 * 0.0625, y1 * 0.0625, z1 * 0.0625, x2 * 0.0625, y2 * 0.0625, z2 * 0.0625);
	}
}
