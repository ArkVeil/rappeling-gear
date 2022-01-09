package net.arkveil.rappelgear.item;

import net.arkveil.rappelgear.RappelGearMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RappelItems {

   public static final Item ROPE = registerItem("rope", new Item(new FabricItemSettings().group(ItemGroup.TOOLS)));

   private static Item registerItem(String name, Item item)
   {
       // register(registry type, namespace:path, item object)
       return Registry.register(Registry.ITEM, new Identifier(RappelGearMod.MOD_ID, name), item);
   }

   public static void registerRappelItems()
   {
       RappelGearMod.LOGGER.info("Registering Items");
   }
}
