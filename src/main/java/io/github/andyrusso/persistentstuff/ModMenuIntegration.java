package io.github.andyrusso.persistentstuff;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            Config config = Config.getInstance();

            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("persistentstuff.title"));

            builder.getOrCreateCategory(Text.literal("General"))
                    .addEntry(toggleOption("enabled", config.enabled, (b) -> config.enabled = b))
                    .addEntry(toggleOption(
                            "sprinting", config.sprintingEnabled, (b) -> config.sprintingEnabled = b))
                    .addEntry(toggleOption("hitboxes", config.hitboxesEnabled, (b) -> config.hitboxesEnabled = b))
                    .addEntry(toggleOption(
                            "chunkBorders", config.chunkBordersEnabled, (b) -> config.chunkBordersEnabled = b));

            return builder.build();
        };
    }

    private BooleanListEntry toggleOption(String key, boolean value, Consumer<Boolean> save) {
        return ConfigEntryBuilder.create()
                .startBooleanToggle(Text.translatable("persistentstuff." + key), value)
                .setSaveConsumer(save)
                .setDefaultValue(true)
                .build();
    }
}
