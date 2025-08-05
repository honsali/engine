package dev.cruding.engine.action.rechargerPage.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlRechargerPageFiltrerInjection extends MdlActionInjection {



    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
    }

}
