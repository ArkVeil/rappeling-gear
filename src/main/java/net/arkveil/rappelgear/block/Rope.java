package net.arkveil.rappelgear.block;

import net.arkveil.rappelgear.RappelGearMod;
import net.arkveil.rappelgear.registry.RappelBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

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

    /**
     * Determines hitbox for rope depending on facing.
     * @param state - state of rope block on placement
     * @return hitbox dimensions
     */
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

    /**
     * Determines logic for block placement.
     * @param ctx context for item usage
     * @return blockstate of new block
     */
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getSide();    //gets side of block hit
        //grab blockstate of block in world at offset from block being placed
        //BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(direction.getOpposite()));
        //.isOf(block) returns true if block == this
        //if (blockState.isOf(this) && blockState.get(FACING) == direction) {
        //    return (BlockState)this.getDefaultState().with(FACING, direction);
        //}
        //if blockside clicked facing up, set facing as up
        if (direction == Direction.UP)
        {
            return (BlockState)this.getDefaultState().with(FACING, direction);
        }
        return (BlockState)this.getDefaultState().with(FACING, direction.getOpposite());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit)
    {
        if(world.isClient) {
            if (!state.isOf(this)) return ActionResult.PASS; //check if clicked item is rope
            if (!player.getMainHandStack().isOf(this.asItem())) return ActionResult.PASS; //check if held item is rope
            return ActionResult.SUCCESS;        //has to be .isClient to show hand motion

        }
//        BlockPos down = pos.offset(Direction.DOWN);
//        BlockState blockDown = world.getBlockState(down);
//        Direction direction = state.get(FACING);
//        if(blockDown.isAir() && !world.isOutOfHeightLimit(down.getY()))
//        {
//            world.setBlockState(down,state);
//            if(!player.isCreative() ) player.getMainHandStack().decrement(1);
//        }
        RappelGearMod.LOGGER.info("Rope used");
        return ActionResult.SUCCESS;
    }

}
