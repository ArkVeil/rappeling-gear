package net.arkveil.rappelgear.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class Rope extends FacingBlock{
    protected static final VoxelShape CENTER_SHAPE  = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    protected static final VoxelShape EAST_SHAPE    = Block.createCuboidShape(12.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape WEST_SHAPE    = Block.createCuboidShape(0.0, 0.0, 6.0, 4.0, 16.0, 10.0);
    protected static final VoxelShape SOUTH_SHAPE   = Block.createCuboidShape(6.0, 0.0, 12.0, 10.0, 16.0, 16.0);
    protected static final VoxelShape NORTH_SHAPE   = Block.createCuboidShape(6.0, 0.0, 0.0, 10.0, 16.0, 4.0);

    public Rope(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.DOWN));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context)
    {
        switch (state.get(FACING)) {
            default: {
                return CENTER_SHAPE;
            }
            case NORTH: {
                return NORTH_SHAPE;
            }
            case SOUTH: {
                return SOUTH_SHAPE;
            }
            case WEST: {
                return WEST_SHAPE;
            }
            case EAST: {
                return EAST_SHAPE;
            }
        }
    }

    @Override
    protected void appendProperties (StateManager.Builder<Block, BlockState> stateManager)
    {
        stateManager.add((FACING));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();    //gets side of block hit
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction));
        if (blockState.isOf(this) && blockState.get(FACING) == direction) {
            return (BlockState)this.getDefaultState().with(FACING, direction);
        }
        return (BlockState)this.getDefaultState().with(FACING, direction.getOpposite());
    }




}
