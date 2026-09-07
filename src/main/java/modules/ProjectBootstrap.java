package modules;

import dev.cruding.engine.gen.Context;
import modules.admin.AdminModule;
import modules.rh.RhModule;

public final class ProjectBootstrap {

    private ProjectBootstrap() {}

    public static void init(Context context) {
        AdminModule.init(context);
        RhModule.init(context);
    }
}
