package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class InitFindCtrlInjection extends ActionCtrlInjection {



    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("resultat.", entity().lname, " = {};");
    }

}
