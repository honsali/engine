package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlListerInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.liste", entite().uname, " = await Service", entite().uname, ".", lnameSansEntite(), "(");

        if (parIdPere() && entite().havePere) {
            f.__("requete.id", entite().upere);
        }
        f.__(");");
        f.L("};");
    }
}
