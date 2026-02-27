package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class InitFindMdlInjection extends ActionMdlInjection {


    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");
    }
}
