package gunmod.init;

import java.util.ArrayList;
import java.util.List;

import gunmod.objects.blocks.BlockBeeBox;
import net.minecraft.block.Block;

public class BlockInit {
	public static List<Block> Blocks = new ArrayList<Block>();
	
	public static final Block BLOCK_BEE_BOX = new BlockBeeBox("block_bee_box");;
}
