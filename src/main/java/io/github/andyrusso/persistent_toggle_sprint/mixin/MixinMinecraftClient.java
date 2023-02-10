package io.github.andyrusso.persistent_toggle_sprint.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(RunArgs args, CallbackInfo ci) {
        File file = new File(FabricLoader.getInstance().getConfigDir().toString(), "persistent-toggle-sprint.txt");
        if (!file.exists()) return;

        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        GameOptions options = MinecraftClient.getInstance().options;

        if (options.getSprintToggled().getValue() && scanner.next().equals("1")) {
            options.sprintKey.setPressed(true);
        }
    }
}
