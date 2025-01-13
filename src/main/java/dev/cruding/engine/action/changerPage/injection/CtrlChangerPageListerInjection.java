package dev.cruding.engine.action.changerPage.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlChangerPageListerInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".listerEnPage(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        f.__("requete.id", entite().upere);
        f.__(", requete.pageCourante);");
        f.L("};");
    }

}
