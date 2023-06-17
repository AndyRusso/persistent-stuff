package io.github.andyrusso.persistentstuff;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;

public class PersistentStuff implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientLifecycleEvents.CLIENT_STOPPING.register(PersistentStuff::onStop);
	}

	private static void onStop(MinecraftClient client) {
		Config config = Config.getInstance();
		config.sprinting = client.options.sprintKey.isPressed();
		config.hitboxes = client.getEntityRenderDispatcher().shouldRenderHitboxes();
		config.chunkBorders = !client.debugRenderer.toggleShowChunkBorder();
		config.save();
	}
}