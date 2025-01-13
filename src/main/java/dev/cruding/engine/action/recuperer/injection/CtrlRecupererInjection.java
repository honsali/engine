package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlRecupererInjection extends CtrlActionInjection {

    public String lnameField;
    public String unameField;

    public CtrlRecupererInjection(String lnameField, String unameField) {
        this.lnameField = lnameField;
        this.unameField = unameField;
    }

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererPar", unameField, "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
        }
        f.__("requete.", lnameField, entite().uname, ");");
        f.L("};");
    }


}
