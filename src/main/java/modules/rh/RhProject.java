package modules.rh;

import dev.cruding.engine.gen.Module;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.PageRef;
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

    public static final PageRef pageFiltrerEmploye = new PageRef("PageFiltrerEmploye");
    public static final PageRef pageConsulterEmploye = new PageRef("PageConsulterEmploye");
    public static final PageRef pageModifierEmploye = new PageRef("PageModifierEmploye");
    public static final PageRef pageCreerEmploye = new PageRef("PageCreerEmploye");

    public static final PageRef pageCreerConge = new PageRef("PageCreerConge");
    public static final PageRef pageConsulterConge = new PageRef("PageConsulterConge");
    public static final PageRef pageModifierConge = new PageRef("PageModifierConge");

    public static final PageRef pageListerDepartement = new PageRef("PageListerDepartement");
    public static final PageRef pageConsulterDepartement = new PageRef("PageConsulterDepartement");
    public static final PageRef pageModifierDepartement = new PageRef("PageModifierDepartement");
    public static final PageRef pageCreerDepartement = new PageRef("PageCreerDepartement");

    @Override
    public void init(Context context) {
        new Module(context, "ModuleRh", "rh").parent().menuIcon("faPeopleLine");

        Module moduleEmploye = new Module(context, "ModuleEmploye", "rh.employe");
        moduleEmploye.addPage(new ViewFiltrerEmploye()).icon("faUser").isIndex();
        moduleEmploye.addPage(new ViewConsulterEmploye()).pathById();
        moduleEmploye.addPage(new ViewModifierEmploye()).pathById();
        moduleEmploye.addPage(new ViewCreerEmploye());
        moduleEmploye.addPage(new ViewCreerConge()).pathById();
        moduleEmploye.addPage(new ViewConsulterConge()).pathById();
        moduleEmploye.addPage(new ViewModifierConge()).pathById();

        Module moduleDepartement = new Module(context, "ModuleDepartement", "rh.departement");
        moduleDepartement.addPage(new ViewListerDepartement()).icon("faSitemap").isIndex();
        moduleDepartement.addPage(new ViewConsulterDepartement()).pathById();
        moduleDepartement.addPage(new ViewModifierDepartement()).pathById();
        moduleDepartement.addPage(new ViewCreerDepartement());

    }
}
