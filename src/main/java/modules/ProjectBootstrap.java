package modules;

import modules.admin.AdminModule;
import modules.rh.RhModule;

public final class ProjectBootstrap {

    private ProjectBootstrap() {}

    public static void init() {
        AdminModule.init();
        RhModule.init();
    }
}
