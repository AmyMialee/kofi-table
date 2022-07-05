package amymialee.kofitable;

import amymialee.kofitable.supporter.Supporter;
import amymialee.kofitable.supporter.SupporterKofiTable;
import com.electronwill.nightconfig.core.AbstractConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import net.fabricmc.api.ModInitializer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KofiTable implements ModInitializer {
    public static final String MOD_ID = "kofitable";
    Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final String CONTRIBUTOR_URL = "https://raw.githubusercontent.com/AmyMialee/kofi-table/main/contributors.toml";

    @Override
    public void onInitialize() {
        System.out.println(CONTRIBUTORS);
    }

    private static final Map<UUID, SupporterKofiTable> CONTRIBUTORS = initContributors(SupporterKofiTable.class, CONTRIBUTOR_URL);

    public static <T extends Supporter> Map<UUID, T> initContributors(Class<T> clazz, String link) {
        UnmodifiableCommentedConfig config;
        try {
            URL url = new URL(link);
            config = new TomlParser().parse(url).unmodifiable();
        } catch (IOException exn) {
            return Object2ObjectMaps.emptyMap();
        }
        HashMap<UUID, T> out = new HashMap<>();
        for (var entry : config.entrySet()) {
            try {
                AbstractConfig rawEntry = entry.getValue();
                UUID uuid = UUID.fromString(entry.getKey());
                T contributor = clazz.getDeclaredConstructor().newInstance(rawEntry);
                out.put(uuid, contributor);
            } catch (Exception ignored) {}
        }
        return out;
    }
}
