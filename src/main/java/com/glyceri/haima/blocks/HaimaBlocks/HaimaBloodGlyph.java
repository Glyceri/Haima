package com.glyceri.haima.blocks.HaimaBlocks;

import com.glyceri.haima.blocks.HaimaBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

// HEHEHHEHEHE:
// https://github.com/klikli-dev/occultism/blob/version/1.21/src/main/java/com/klikli_dev/occultism/common/block/ChalkGlyphBlock.java#L49

// No I mean it, thanks to all the modders that did stuff like this before me that I can now yoink from. You all make my life amazing!

public class HaimaBloodGlyph extends HaimaBlock
{
	public static final String BLOCK_ID = "blood_glyph";
	
	public static final int MAX_BLOOD_TYPES = 5;															// AKA there are 5 blood types
	public static final IntegerProperty BLOOD_SHAPE = IntegerProperty.create("blood_type", 0, MAX_BLOOD_TYPES); 	// This just shows that depending on where in the world it falls it'll become a certain blood typ
	
	static final VoxelShape SHAPE = Block.box(0, 0, 0, 15, 0.01, 15);										// Thin c:
	
	// The colour will be a random dark-ish shade of red on top of the different glyph styles!
	protected int colour;
	 
	public HaimaBloodGlyph(Properties properties, int colour)
	{
		super(properties
				.sound(SoundType.WOOL)
	            .pushReaction(PushReaction.DESTROY)
	            .replaceable()
	            .noCollission()
	            .noLootTable()
	            .strength(5f, 30)
	            .randomTicks());
		
		this.colour = colour;
	}
	
	@Override
	protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) 
	{
		if (!serverLevel.isRaining()) return;
		if (!isNearRain(serverLevel, blockPos)) return;
		
		clearBlood(serverLevel, blockPos);
	}
	
	protected boolean isNearRain(Level level, BlockPos blockPos) 
	{ 
		return (level.isRainingAt(blockPos) || level.isRainingAt(blockPos.west()) || level.isRainingAt(blockPos.east()) || level.isRainingAt(blockPos.north()) || level.isRainingAt(blockPos.south())); 
	}
	
    public int getColour() 
    {
        return this.colour;
    }
    
    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
    {
    	  if (!this.canSurvive(state, worldIn, pos)) 
    	  {
    		  clearBlood(worldIn, pos);
          }
    }
    
    void clearBlood(Level world, BlockPos pos)
    {
    	world.removeBlock(pos, false);
    }
    
    
    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext context) {
        return true;
    }

    @Override
    public boolean canBeReplaced(BlockState state, Fluid fluid) {
        return true;
    }
    
    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockPos down = pos.below();
        BlockState downState = worldIn.getBlockState(down);
        return downState.isFaceSturdy(worldIn, down, Direction.UP) && state.canBeReplaced();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        int sign = context.getLevel().getRandom().nextInt(MAX_BLOOD_TYPES + 1);
        BlockState current = context.getLevel().getBlockState(pos);
        if (current.getBlock() == this) {
            sign = (current.getValue(BLOOD_SHAPE) + 1) % (MAX_BLOOD_TYPES + 1);
        }

        return this.defaultBlockState().setValue(BLOOD_SHAPE, sign)
                .setValue(BlockStateProperties.HORIZONTAL_FACING,
                        context.getHorizontalDirection().getOpposite());
    }
    
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOOD_SHAPE, BlockStateProperties.HORIZONTAL_FACING);
        super.createBlockStateDefinition(builder);
    }
}
