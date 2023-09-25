package io.github.andyrusso.persistentstuff;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;

public class PersistentStuff implements ClientModInitializer {
	public static final String MODID = "persistent-stuff";

	@Override
	public void onInitializeClient() {
		MidnightConfig.init(PersistentStuff.MODID, Config.class);
		ClientLifecycleEvents.CLIENT_STOPPING.register(PersistentStuff::onStop);
	}

	private static void onStop(MinecraftClient client) {
		Config.sprinting = client.options.sprintKey.isPressed();
		Config.hitboxes = client.getEntityRenderDispatcher().shouldRenderHitboxes();
		Config.chunkBorders = !client.debugRenderer.toggleShowChunkBorder();
		MidnightConfig.write(PersistentStuff.MODID);
	}
}