package modules.rh;

import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.ProjectBootstrap;
import modules.rh.conge.ViewConsulterConge;
import modules.rh.conge.ViewCreerConge;
import modules.rh.conge.ViewModifierConge;
import modules.rh.departement.ViewConsulterDepartement;
import modules.rh.departement.ViewCreerDepartement;
import modules.rh.departement.ViewListerDepartement;
import modules.rh.departement.ViewModifierDepartement;
import modules.rh.employe.ViewConsulterEmploye;
import modules.rh.employe.ViewCreerEmploye;
import modules.rh.employe.ViewFiltrerEmploye;
import modules.rh.employe.ViewModifierEmploye;

public class RhProject implements ProjectBootstrap {

    public static Page pageFiltrerEmploye;
    public static Page pageConsulterEmploye;
    public static Page pageModifierEmploye;
    public static Page pageCreerEmploye;

    public static Page pageCreerConge;
    public static Page pageConsulterConge;
    public static Page pageModifierConge;

    public static Page pageListerDepartement;
    public static Page pageConsulterDepartement;
    public static Page pageModifierDepartement;
    public static Page pageCreerDepartement;

    @Override
    public void init() {
        new Module("ModuleRh", "rh").parent().menuIcon("faPeopleLine");

        Module moduleEmploye = new Module("ModuleEmploye", "rh.employe");
        pageFiltrerEmploye = moduleEmploye.addPage(new ViewFiltrerEmploye()).icon("faUser").isIndex();
        pageConsulterEmploye = moduleEmploye.addPage(new ViewConsulterEmploye()).pathById();
        pageModifierEmploye = moduleEmploye.addPage(new ViewModifierEmploye()).pathById();
        pageCreerEmploye = moduleEmploye.addPage(new ViewCreerEmploye());
        pageCreerConge = moduleEmploye.addPage(new ViewCreerConge()).pathById();
        pageConsulterConge = moduleEmploye.addPage(new ViewConsulterConge()).pathById();
        pageModifierConge = moduleEmploye.addPage(new ViewModifierConge()).pathById();

        Module moduleDepartement = new Module("ModuleDepartement", "rh.departement");
        pageListerDepartement = moduleDepartement.addPage(new ViewListerDepartement()).icon("faSitemap").isIndex();
        pageConsulterDepartement = moduleDepartement.addPage(new ViewConsulterDepartement()).pathById();
        pageModifierDepartement = moduleDepartement.addPage(new ViewModifierDepartement()).pathById();
        pageCreerDepartement = moduleDepartement.addPage(new ViewCreerDepartement());
    }
}
