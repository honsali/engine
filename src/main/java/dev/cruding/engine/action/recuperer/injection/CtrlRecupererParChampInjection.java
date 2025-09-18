package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlRecupererParChampInjection extends CtrlActionInjection {


    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        String lnameChamp = parChamp()[0].lname;
        String unameChamp = parChamp()[0].uname;

        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererPar", unameChamp, "(");

        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
        }
        f.__("requete.", lnameChamp.equals("id") ? "id" + entite().uname : lnameChamp, ");");
        f.L("};");
    }
}
