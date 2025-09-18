package modules.rh.employe;

import dev.cruding.engine.gen.Module;
import modules.rh.conge.ViewModifierConge;

public class ModuleEmploye extends Module {
    public ModuleEmploye() {
        addPage("PageFiltrerEmploye", new ViewFiltrerEmploye()).menuIcone("faUser");
        addPage("PageConsulterEmploye", new ViewConsulterEmploye()).pathById();
        addPage("PageModifierEmploye", new ViewModifierEmploye()).pathById();
        addPage("PageCreerEmploye", new ViewCreerEmploye());
        addPage("PageModifierConge", new ViewModifierConge()).pathById();
    }

}
