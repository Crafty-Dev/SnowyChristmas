package de.crafty.snowychristmas;

import de.crafty.snowychristmas.config.SnowyChristmasConfig;
import de.crafty.snowychristmas.registry.ItemGroupRegistry;
import de.crafty.snowychristmas.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SnowyChristmas implements ModInitializer {

    public static final String MODID = "snowychristmas";
    public static final Logger LOGGER = LoggerFactory.getLogger("SnowyChristmas");

    @Override
    public void onInitialize() {

        ItemRegistry.perform();
        ItemGroupRegistry.perform();

        ItemGroupRegistry.registerModItems();

        SnowyChristmasConfig.INSTANCE.load();
    }
}
