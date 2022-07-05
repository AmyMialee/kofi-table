package amymialee.kofitable.supporter;

import com.electronwill.nightconfig.core.AbstractConfig;

public class SupporterKofiTable extends Supporter {
    public final boolean supporter;

    public SupporterKofiTable(AbstractConfig supporterData) {
        super(supporterData);

        this.supporter = getBoolean("kofitable:supporter");
    }
}
