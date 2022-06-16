package gunmod.util.debugging;

import net.minecraftforge.fml.relauncher.Side;

public class ClientAndServer {

	public static String stringFromSide(boolean isClient)
	{
		if (isClient)
		{
			return "client";
		}
		else 
		{
			return "server";
		}
	}
}
