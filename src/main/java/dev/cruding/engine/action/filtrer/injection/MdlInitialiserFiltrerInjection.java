package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlInitialiserFiltrerInjection extends MdlActionInjection {



    public void addMdlExtraReducerAffectation(MdlFlow f) {
        if (pagine()) {
            f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
            f.L________________("state.pageCourante = 0;");
        } else {
            f.L________________("state.liste", entite().uname, " = action.payload.liste", entite().uname, ";");
        }
        f.L________________("state.filtre = action.payload.filtre;");
    }
}
