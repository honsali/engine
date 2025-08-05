package dev.cruding.engine.action.rechargerPage.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlRechargerPageFiltrerInjection extends CtrlActionInjection {



    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const { mdl", uc(), " } = thunkAPI.getState() as any;");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".filtrer({ ...mdl", uc(), ".filtre, pageCourante: mdl", uc(), ".pageCourante });");
        f.L("};");
    }


}
