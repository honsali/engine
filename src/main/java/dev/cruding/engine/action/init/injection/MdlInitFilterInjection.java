package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class MdlInitFilterInjection extends ActionMdlInjection {



    public void addMdlExtraReducerAffectation(MdlFlow f) {
        if (paginated()) {
            f.L________________("state.listePaginee", entity().uname, " = action.payload.listePaginee", entity().uname, ";");
            f.L________________("state.pageCourante = 0;");
        } else {
            f.L________________("state.liste", entity().uname, " = action.payload.liste", entity().uname, ";");
        }
        f.L________________("state.filtre = action.payload.filtre;");
    }
}
