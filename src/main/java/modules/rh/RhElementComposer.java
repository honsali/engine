package modules.rh;

import dev.cruding.engine.gen.ElementComposer;
import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.ProjectBootstrap;
import model.rh.Conge;
import model.rh.Departement;
import model.rh.Employe;
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

public class RhElementComposer extends ElementComposer implements ProjectBootstrap {

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

    public RhElementComposer() {
        super();
    }

    @Override
    public void init() {
        new Module("ModuleRh", "rh").parent().menuIcon("faPeopleLine");

        Module moduleEmploye = new Module("ModuleEmploye", "rh.employe");
        pageFiltrerEmploye = moduleEmploye.addPage(Employe.class, new ViewFiltrerEmploye()).icon("faUser");
        pageConsulterEmploye = moduleEmploye.addPage(Employe.class, new ViewConsulterEmploye()).pathById();
        pageModifierEmploye = moduleEmploye.addPage(Employe.class, new ViewModifierEmploye()).pathById();
        pageCreerEmploye = moduleEmploye.addPage(Employe.class, new ViewCreerEmploye());
        pageCreerConge = moduleEmploye.addPage(Conge.class, new ViewCreerConge()).pathById();
        pageConsulterConge = moduleEmploye.addPage(Conge.class, new ViewConsulterConge()).pathById();
        pageModifierConge = moduleEmploye.addPage(Conge.class, new ViewModifierConge()).pathById();

        Module moduleDepartement = new Module("ModuleDepartement", "rh.departement");
        pageListerDepartement = moduleDepartement.addPage(Departement.class, new ViewListerDepartement()).icon("faSitemap");
        pageConsulterDepartement = moduleDepartement.addPage(Departement.class, new ViewConsulterDepartement()).pathById();
        pageModifierDepartement = moduleDepartement.addPage(Departement.class, new ViewModifierDepartement()).pathById();
        pageCreerDepartement = moduleDepartement.addPage(Departement.class, new ViewCreerDepartement());
    }
}
