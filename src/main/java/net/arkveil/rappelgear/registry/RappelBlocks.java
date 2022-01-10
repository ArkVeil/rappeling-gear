package net.arkveil.rappelgear.registry;

import net.arkveil.rappelgear.RappelGearMod;
import net.arkveil.rappelgear.block.Rope;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RappelBlocks {

    public static final Rope ROPE_BLOCK = new Rope(FabricBlockSettings.of(Material.BAMBOO).noCollision().sounds(BlockSoundGroup.WOOL));
    /*
    public static final Block ROPE_BLOCK = registerBlock("rope_block",
            new Block(FabricBlockSettings.of(Material.WOOL).strength(0.5f)));
    public static final Item ROPE_ITEM = registerBlockItem("rope",new BlockItem(ROPE_BLOCK, new FabricItemSettings().group(ItemGroup.TOOLS)));
*/
    private static Block registerBlock(String name, Block block)
    {
        return Registry.register(Registry.BLOCK, new Identifier(RappelGearMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, BlockItem blockItem)
    {
        return Registry.register(Registry.ITEM, new Identifier(RappelGearMod.MOD_ID, name), blockItem);
    }

    public static void registerAll()
    {
        registerBlock("rope", ROPE_BLOCK);
        registerBlockItem("rope",new BlockItem(ROPE_BLOCK, new FabricItemSettings().group(ItemGroup.TOOLS)));
        RappelGearMod.LOGGER.info("Registering Blocks");
    }
}
