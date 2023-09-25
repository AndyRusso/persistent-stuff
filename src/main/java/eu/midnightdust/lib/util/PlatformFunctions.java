package eu.midnightdust.lib.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class PlatformFunctions {
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
    public static boolean isClientEnv() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }
}