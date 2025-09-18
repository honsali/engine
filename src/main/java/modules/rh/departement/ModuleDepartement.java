package modules.rh.departement;

import dev.cruding.engine.gen.Module;

public class ModuleDepartement extends Module {
    public ModuleDepartement() {
        addPage("PageListerDepartement", new ViewListerDepartement()).menuIcone("faSitemap");
        addPage("PageConsulterDepartement", new ViewConsulterDepartement()).pathById();
        addPage("PageModifierDepartement", new ViewModifierDepartement()).pathById();
        addPage("PageCreerDepartement", new ViewCreerDepartement());

    }

}
