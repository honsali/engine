package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlInitialiserFiltrerInjection extends CtrlActionInjection {
    private boolean filtrerAuDepart = false;

    public CtrlInitialiserFiltrerInjection(boolean filtrerAuDepart) {
        this.filtrerAuDepart = filtrerAuDepart;
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        if (filtrerAuDepart) {
            if (pagine()) {
                f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".filtrer({ pageCourante: 0 });");
            } else {
                f.L____("resultat.liste", entite().uname, " = await Service", entite().uname, ".filtrer();");
            }

        } else {
            if (pagine()) {
                f.L____("resultat.listePaginee", entite().uname, " = { liste: [], pagination: { pageCourante: 0 } };");
            } else {
                f.L____("resultat.liste", entite().uname, " = [];");
            }

        }
        f.L____("resultat.filtre = {};");
        f.L("};");
    }

}
