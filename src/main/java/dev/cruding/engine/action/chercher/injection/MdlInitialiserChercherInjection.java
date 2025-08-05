package dev.cruding.engine.action.chercher.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlInitialiserChercherInjection extends MdlActionInjection {


    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
    }
}
