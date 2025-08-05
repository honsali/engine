package modules.rh;

import dev.cruding.engine.gen.Module;
import modules.rh.departement.ViewConsulterDepartement;
import modules.rh.departement.ViewCreerDepartement;
import modules.rh.departement.ViewListerDepartement;
import modules.rh.departement.ViewModifierDepartement;

public class ModuleRh extends Module {
    public ModuleRh() {
        addPage("PageListerDepartement", new ViewListerDepartement()).menuIcone("faMagnifyingGlass");
        addPage("PageConsulterDepartement", new ViewConsulterDepartement());
        addPage("PageModifierDepartement", new ViewModifierDepartement());
        addPage("PageCreerDepartement", new ViewCreerDepartement());
    }

}
