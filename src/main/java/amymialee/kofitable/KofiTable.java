package amymialee.kofitable;

import amymialee.kofitable.registry.KofiTableBlocks;
import amymialee.kofitable.registry.KofiTableItems;
import amymialee.kofitable.supporter.Supporter;
import amymialee.kofitable.supporter.SupporterKofiTable;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class KofiTable implements ModInitializer {
    public static final String MOD_ID = "kofitable";
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ItemGroup KOFI_GROUP = FabricItemGroupBuilder.create(id("kofitable_group")).icon(KofiTableItems::getItemGroupStack).build();

    public static final String CONTRIBUTOR_URL = "https://raw.githubusercontent.com/AmyMialee/kofi-table/main/supporters.toml";
    public static final Map<UUID, SupporterKofiTable> CONTRIBUTORS = initContributors(MOD_ID, SupporterKofiTable.class, CONTRIBUTOR_URL);

    @Override
    public void onInitialize() {
        KofiTableItems.init();
        KofiTableBlocks.init();
    }

    public static <T extends Supporter> Map<UUID, T> initContributors(String mod_id, Class<T> clazz, String link) {
        UnmodifiableCommentedConfig config;
        try {
            URL url = new URL(link);
            config = new TomlParser().parse(url).unmodifiable();
        } catch (Exception exception) {
            LOGGER.warn("Couldn't load contributors for mod %s: {}".formatted(mod_id), exception.getMessage());
            return Object2ObjectMaps.emptyMap();
        }
        HashMap<UUID, T> out = new HashMap<>();
        for (UnmodifiableCommentedConfig.Entry entry : config.entrySet()) {
            try {
                AbstractConfig rawEntry = entry.getValue();
                UUID uuid = UUID.fromString(entry.getKey());
                T contributor = clazz.getConstructor(AbstractConfig.class).newInstance(rawEntry);
                out.put(uuid, contributor);
            } catch (Exception exception) {
                LOGGER.warn("Error loading contributor '{}' for mod %s: {}".formatted(mod_id), entry.getKey(), exception.getMessage());
            }
        }
        LOGGER.warn("Loaded %s contributor%s for mod %s.".formatted(out.size(), out.size() == 1 ? "" : "s", mod_id));
        return out;
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}