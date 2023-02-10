package io.github.andyrusso.persistent_toggle_sprint;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PersistentToggleSprint implements ClientModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("persistent-toggle-sprint");

	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STOPPING.register(PersistentToggleSprint::onStop);
	}

	private static void onStop(MinecraftClient client) {
		File file = new File(FabricLoader.getInstance().getConfigDir().toString(), "persistent-toggle-sprint.txt");
		try {
			if (file.createNewFile()) {
				LOGGER.info("Created persistent-toggle-sprint.txt");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		FileWriter writer;
		try {
			writer = new FileWriter(file);
			String state = "0";
			if (client.options.sprintKey.isPressed()) {
				state = "1";
			}
			writer.write(state);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
