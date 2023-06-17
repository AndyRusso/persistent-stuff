package io.github.andyrusso.persistentstuff.mixin;

import io.github.andyrusso.persistentstuff.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(RunArgs args, CallbackInfo ci) {
        Config config = Config.getInstance();
        // Don't do anything if the whole mod is disabled
        if (!config.enabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        GameOptions options = client.options;

        options.sprintKey.setPressed(config.sprinting && config.sprintingEnabled);
        client.getEntityRenderDispatcher().setRenderHitboxes(config.hitboxes && config.hitboxesEnabled);
        if (config.chunkBorders && config.chunkBordersEnabled) client.debugRenderer.toggleShowChunkBorder();
    }
}
