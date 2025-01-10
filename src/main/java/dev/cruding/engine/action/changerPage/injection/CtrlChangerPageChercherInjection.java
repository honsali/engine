package dev.cruding.engine.action.changerPage.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlChangerPageChercherInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const { mdl", uc(), " } = thunkAPI.getState() as any;");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".chercher({ ...mdl", uc(), ".filtre, pageCourante: requete.pageCourante });");
        f.L("};");
    }

}
