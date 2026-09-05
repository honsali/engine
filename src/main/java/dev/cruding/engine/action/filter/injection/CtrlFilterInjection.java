package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class CtrlFilterInjection extends ActionCtrlInjection {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        if (paginated()) {
            f.L____("resultat.listePaginee", entity().uname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(requete.filtre);");
        } else {
            f.L____("resultat.liste", entity().uname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(requete.filtre);");
        }
        f.L____("resultat.filtre = requete.filtre;");
    }
}
