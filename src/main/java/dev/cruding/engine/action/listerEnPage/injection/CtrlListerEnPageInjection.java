package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlListerEnPageInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".", lcoreName(), "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("requete.id", entite().upere, ", ");
        }
        f.__("0);");
        f.L("};");
    }
}
