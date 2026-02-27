package dev.cruding.engine.action.changePage.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class ChangePageForListCtrlInjection extends ActionCtrlInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("resultat.listePaginee", entity().uname, " = await Service", entity().uname, ".listerEnPage(");
        if (byFatherId()) {
            f.__("requete.id", entity().ufather, ", ");
        }
        f.__("requete.pageCourante);");
    }

}
