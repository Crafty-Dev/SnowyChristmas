package de.crafty.snowychristmas.registry;

import de.crafty.snowychristmas.SnowyChristmas;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.Collection;
import java.util.LinkedHashMap;

public class ItemRegistry {

    private static final LinkedHashMap<ResourceLocation, Item> ITEMS = new LinkedHashMap<>();

    public static final Item CHRISTMAS_DISC_LAST_CHRISTMAS = register("christmas_disc_01", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).jukeboxPlayable(ChristmasSongs.LAST_CHRISTMAS)));
    public static final Item CHRISTMAS_DISC_AIWFCIY = register("christmas_disc_02", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ChristmasSongs.AIWFCIY)));
    public static final Item CHRISTMAS_DISC_DHFC = register("christmas_disc_03", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).jukeboxPlayable(ChristmasSongs.DHFC)));
    public static final Item CHRISTMAS_DISC_IBTLALLC = register("christmas_disc_04", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE).jukeboxPlayable(ChristmasSongs.IBTLALLC)));
    public static final Item CHRISTMAS_DISC_UTT = register("christmas_disc_05", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.COMMON).jukeboxPlayable(ChristmasSongs.UTT)));
    public static final Item CHRISTMAS_DISC_GZUZ = register("christmas_disc_06", new Item(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC).jukeboxPlayable(ChristmasSongs.GZUZ)));


    private static <T extends Item> T register(String id, T item) {
        ITEMS.put(ResourceLocation.fromNamespaceAndPath(SnowyChristmas.MODID, id), item);
        return item;
    }

    public static void perform() {
        ITEMS.forEach((resourceLocation, item) -> {
            Registry.register(BuiltInRegistries.ITEM, resourceLocation, item);
        });
    }

    public static Collection<Item> getItemList() {
        return ITEMS.values();
    }

}
