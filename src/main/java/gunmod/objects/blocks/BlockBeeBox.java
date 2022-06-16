package gunmod.objects.blocks;

import gunmod.init.ItemInit;
import gunmod.objects.entities.EntityBee;
import gunmod.util.block.BlockHelper;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBeeBox extends BlockBase {

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	//protected static final AxisAlignedBB X_AXIS_AABB = BlockHelper.getNewBoundingBoxFromSixteenths(5.25, 0, 6, 1, 1, 1);
	protected static final AxisAlignedBB X_AXIS_AABB = BlockHelper.getNewBoundingBoxFromSixteenths(4.5, 0, 6, 11.5, 4.3125, 10);
	protected static final AxisAlignedBB Z_AXIS_AABB = BlockHelper.getNewBoundingBoxFromSixteenths(6, 0, 4.5, 10, 4.3125, 11.5);
    
	
	public BlockBeeBox(String name) 
	{
		super(name, Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItemMainhand();
		if (stack.getItem() == ItemInit.ITEM_BOTTLED_BEE)
		{
			if (!playerIn.isCreative())
			{
				stack.shrink(1);
			}
			this.initiateExplosion(worldIn, pos);
		}
		return true;
	}
	
	public void initiateExplosion(World world, BlockPos pos)
	{
		EntityBee entitybee = new EntityBee(world);
		entitybee.setPosition(pos.getX() + this.RANDOM.nextDouble(), pos.getY(), pos.getZ() + this.RANDOM.nextDouble());
		if (!world.isRemote)
		{
			for (int i = 0; i < 10; i++)
			{
				world.spawnEntity(entitybee);
			}   
		}
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
        if (enumfacing.getAxis() == EnumFacing.Axis.X)
        {
        	return X_AXIS_AABB;
        } else
        {
        	return Z_AXIS_AABB;
        }
    }
	
	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) 
	{
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) 
	{
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) 
	{
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) 
	{
		EnumFacing facing = EnumFacing.getFront(meta);
		if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) 
	{
		return ((EnumFacing)state.getValue(FACING)).getIndex();
	}	
}
