package io.github.andyrusso.persistentstuff.mixin;

import io.github.andyrusso.persistentstuff.Config;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {

    @Redirect(method = "requestRespawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;untoggleStickyKeys()V"))
    private void doNotUntoggleStickyKeys() {
        if (!Config.enabled || !Config.afterDeath) KeyBinding.untoggleStickyKeys();
    }
}
