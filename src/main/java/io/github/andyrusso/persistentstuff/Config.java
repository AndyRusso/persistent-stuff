package io.github.andyrusso.persistentstuff;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
    // Saved state of the previous game session
    @Hidden
    @Entry
    public static boolean sprinting = false;
    @Hidden
    @Entry
    public static boolean hitboxes = false;
    @Hidden
    @Entry
    public static boolean chunkBorders = false;
    // Whether the whole mod is enabled
    @Entry
    public static boolean enabled = true;
    // Toggles for separate features
    @Entry
    public static boolean sprintingEnabled = true;
    @Entry
    public static boolean hitboxesEnabled = true;
    @Entry
    public static boolean chunkBordersEnabled = true;
    @Entry
    public static boolean afterDeath = true;
}
