package de.crafty.snowychristmas.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.crafty.snowychristmas.SnowyChristmas;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SnowyChristmasConfig {

    public static final SnowyChristmasConfig INSTANCE = new SnowyChristmasConfig();

    private final File configFile;
    private JsonObject data;

    private SnowyChristmasConfig() {
        this.configFile = new File("config/snowychristmas", "config.json");
        this.data = new JsonObject();
    }


    public void load() {
        this.setDefaults();

        if(!this.configFile.exists()){
            this.save();
            return;
        }

        try {
            String content = FileUtils.readFileToString(this.configFile, StandardCharsets.UTF_8);
            this.data = JsonParser.parseString(content).getAsJsonObject();

        } catch (Exception e) {
            SnowyChristmas.LOGGER.error("Failed to load Config file!", e);
        }
    }

    public void save() {
        try {
            if(!this.configFile.exists())
                SnowyChristmas.LOGGER.info("Config file not present; Generating one...");

            FileUtils.writeStringToFile(this.configFile, new GsonBuilder().setPrettyPrinting().create().toJson(this.data), StandardCharsets.UTF_8);
        } catch (IOException e) {
            SnowyChristmas.LOGGER.error("Failed to save config file!", e);
        }

    }

    private void setDefaults() {
        JsonObject rainSnowChange = new JsonObject();
        rainSnowChange.addProperty("enabled", true);
        rainSnowChange.addProperty("chunkRadius", 16);

        this.data().add("changeRainToSnow", rainSnowChange);
        this.data().addProperty("longerSnowPeriods", 0.25F);
        this.data().addProperty("higherSnowRate", 0.25F);
    }


    private JsonObject data(){
        return this.data;
    }

    public boolean changeRainToSnow() {
        return this.data().getAsJsonObject("changeRainToSnow").get("enabled").getAsBoolean();
    }

    public int rainSnowChangeRadius(){
        return this.data().getAsJsonObject("changeRainToSnow").get("chunkRadius").getAsInt();
    }

    public float longerSnowPeriods(){
        return this.data().get("longerSnowPeriods").getAsFloat();
    }

    public float higherSnowRate(){
        return this.data().get("higherSnowRate").getAsFloat();
    }
}
