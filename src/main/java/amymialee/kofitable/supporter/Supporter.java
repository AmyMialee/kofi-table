package amymialee.kofitable.supporter;

import com.electronwill.nightconfig.core.AbstractConfig;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class Supporter {
    private final AbstractConfig supporterData;

    public Supporter(AbstractConfig supporterData) {
        this.supporterData = supporterData;
    }

    public boolean has(String key) {
        return this.supporterData.get(key) != null;
    }

    @Nullable
    public <T> T get(String key) {
        return this.supporterData.get(key);
    }

    public int getInt(String key) {
        if (has(key) && get(key) instanceof Integer integer) {
            return integer;
        }
        return -1;
    }

    public long getLong(String key) {
        if (has(key) && get(key) instanceof Long longer) {
            return longer;
        }
        return -1;
    }

    public float getFloat(String key) {
        if (has(key) && get(key) instanceof Float floaty) {
            return floaty;
        }
        return -1;
    }

    public double getDouble(String key) {
        if (has(key) && get(key) instanceof Double doubled) {
            return doubled;
        }
        return -1;
    }

    public boolean getBoolean(String key) {
        if (has(key) && get(key) instanceof Boolean bool) {
            return bool;
        }
        return false;
    }

    public String getString(String key) {
        if (has(key) && get(key) instanceof String string) {
            return string;
        }
        return "";
    }

    public <T> boolean hasClass(String key, Class<T> clazz) {
        return has(key) && clazz.isInstance(get(key));
    }

    public boolean hasInt(String key) {
        return has(key) && get(key) instanceof Integer;
    }

    public boolean hasLong(String key) {
        return has(key) && get(key) instanceof Long;
    }

    public boolean hasFloat(String key) {
        return has(key) && get(key) instanceof Float;
    }

    public boolean hasDouble(String key) {
        return has(key) && get(key) instanceof Double;
    }

    public boolean hasBoolean(String key) {
        return has(key) && get(key) instanceof Boolean;
    }

    public boolean hasString(String key) {
        return has(key) && get(key) instanceof String;
    }
}