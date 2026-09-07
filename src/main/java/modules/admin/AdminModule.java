package modules.admin;

import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.PageRef;
import modules.admin.account.ViewConsulterAccount;
import modules.admin.account.ViewCreerAccount;
import modules.admin.account.ViewListerAccount;
import modules.admin.account.ViewModifierAccount;

public final class AdminModule {

    public static final PageRef pageListerAccount = new PageRef("PageListerAccount");
    public static final PageRef pageConsulterAccount = new PageRef("PageConsulterAccount");
    public static final PageRef pageModifierAccount = new PageRef("PageModifierAccount");
    public static final PageRef pageCreerAccount = new PageRef("PageCreerAccount");

    private AdminModule() {}

    public static void init() {
        new Module("ModuleAdmin", "admin").parent().menuIcon("faUserShield");

        Module moduleAccount = new Module("ModuleAccount", "admin.account");
        moduleAccount.addPage(new ViewListerAccount()).icon("faUsers").isIndex();
        moduleAccount.addPage(new ViewConsulterAccount()).pathById();
        moduleAccount.addPage(new ViewModifierAccount()).pathById();
        moduleAccount.addPage(new ViewCreerAccount());
    }
}
