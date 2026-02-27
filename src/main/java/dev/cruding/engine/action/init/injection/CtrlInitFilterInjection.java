package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class CtrlInitFilterInjection extends ActionCtrlInjection {
    private boolean filterOnLoad = false;

    public CtrlInitFilterInjection(boolean filterOnLoad) {
        this.filterOnLoad = filterOnLoad;
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        if (filterOnLoad) {
            if (paginated()) {
                f.L____("resultat.listePaginee", entity().uname, " = await Service", entity().uname, ".filtrer({});");
            } else {
                f.L____("resultat.liste", entity().uname, " = await Service", entity().uname, ".filtrer();");
            }

        } else {
            if (paginated()) {
                f.L____("resultat.listePaginee", entity().uname, " = { liste: [], pagination: { pageCourante: 0 } };");
            } else {
                f.L____("resultat.liste", entity().uname, " = [];");
            }

        }
        f.L____("resultat.filtre = {};");
    }

}
