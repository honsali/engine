package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlInitialiserMdl extends MdlActionInjection {

    public boolean addMdlReducer(MdlFlow f) {

        f.L____________("initialiser: (state) => {");
        f.L________________("return initialState;");
        f.L____________("}");
        return true;
    }

}
