package net.arkveil.rappelgear.block;

import net.arkveil.rappelgear.RappelGearMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class RopeBlock {

    public static final Block ROPE_BLOCK = registerBlock("rope_block",
            new Block(FabricBlockSettings.of(Material.WOOL).strength(0.5f)));
    public static final Item ROPE_ITEM = registerBlockItem("rope",new BlockItem(ROPE_BLOCK, new FabricItemSettings().group(ItemGroup.TOOLS)));

    private static Block registerBlock(String name, Block block)
    {
        return Registry.register(Registry.BLOCK, new Identifier(RappelGearMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, BlockItem blockItem)
    {
        return Registry.register(Registry.ITEM, new Identifier(RappelGearMod.MOD_ID, name), blockItem);
    }
/*
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context)
    {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 0.5f);
    }
*/
    public static void registerBlocks() { RappelGearMod.LOGGER.info("Registering Blocks"); }

}
