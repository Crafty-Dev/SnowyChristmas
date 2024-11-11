package de.crafty.snowychristmas.registry;

import de.crafty.snowychristmas.SnowyChristmas;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;

public class ItemGroupRegistry {

    private static final HashMap<ResourceLocation, CreativeModeTab> CREATIVE_TABS = new HashMap<>();

    public static final ResourceKey<CreativeModeTab> SNOWY_CHRISTMAS = registerTab("snowychristmas", Items.SNOWBALL);

    private static ResourceKey<CreativeModeTab> registerTab(String id, Item display) {
            ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(SnowyChristmas.MODID, id));
            CREATIVE_TABS.put(ResourceLocation.fromNamespaceAndPath(SnowyChristmas.MODID, id),
                    FabricItemGroup.builder()
                            .icon(() -> new ItemStack(display))
                            .title(Component.translatable(String.format("itemGroup.%s.%s", SnowyChristmas.MODID, id)))
                            .build()
                    );

            return key;
    }

    public static void perform() {
        CREATIVE_TABS.forEach((resourceLocation, creativeModeTab) -> {
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, resourceLocation, creativeModeTab);
        });
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroupRegistry.SNOWY_CHRISTMAS).register(entries -> {
            ItemRegistry.getItemList().forEach(entries::accept);
        });
    }
}
