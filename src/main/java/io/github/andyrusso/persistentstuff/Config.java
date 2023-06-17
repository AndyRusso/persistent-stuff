package io.github.andyrusso.persistentstuff;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger("PersistentStuff");
    private static final Path file = FabricLoader.getInstance().getConfigDir().resolve("persistent-stuff.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Config instance;

    // Saved state of the previous game session
    public boolean sprinting = false;
    public boolean hitboxes = false;
    public boolean chunkBorders = false;
    // Whether the whole mod is enabled
    public boolean enabled = true;
    // Toggles for separate features
    public boolean sprintingEnabled = true;
    public boolean hitboxesEnabled = true;
    public boolean chunkBordersEnabled = true;

    public void save() {
        try {
            Files.writeString(file, GSON.toJson(this));
        } catch (IOException e) {
            LOGGER.error("PersistentStuff could not save the config.");
            throw new RuntimeException(e);
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            try {
                instance = GSON.fromJson(Files.readString(file), Config.class);
            } catch (IOException exception) {
                LOGGER.warn("PersistentStuff couldn't load the config, using defaults.");
                instance = new Config();
            }
        }

        return instance;
    }
}
