package modules.rh.employe;

import dev.cruding.engine.gen.Module;
import modules.rh.conge.ViewConsulterConge;
import modules.rh.conge.ViewCreerConge;
import modules.rh.conge.ViewModifierConge;

public class ModuleEmploye extends Module {
    public ModuleEmploye() {
        addPage("PageFiltrerEmploye", new ViewFiltrerEmploye()).icon("faUser");
        addPage("PageConsulterEmploye", new ViewConsulterEmploye()).pathById();
        addPage("PageModifierEmploye", new ViewModifierEmploye()).pathById();
        addPage("PageCreerEmploye", new ViewCreerEmploye());
        addPage("PageCreerConge", new ViewCreerConge()).pathById();
        addPage("PageConsulterConge", new ViewConsulterConge()).pathById();
        addPage("PageModifierConge", new ViewModifierConge()).pathById();
    }
}
