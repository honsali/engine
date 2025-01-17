package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlRecupererInjection extends CtrlActionInjection {

    public String lnameChamp;
    public String unameChamp;

    public CtrlRecupererInjection(String lnameChamp, String unameChamp) {
        this.lnameChamp = lnameChamp;
        this.unameChamp = unameChamp;
    }

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererPar", unameChamp, "(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
        }
        f.__("requete.", lnameChamp, entite().uname, ");");
        f.L("};");
    }


}
