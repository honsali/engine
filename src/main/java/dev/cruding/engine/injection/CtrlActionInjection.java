package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.flow.CtrlFlow;

public class CtrlActionInjection extends ActionWrapper {

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementation(CtrlFlow f) {}

    public void addCtrlDeclaration(CtrlFlow f) {
        if (uc() != null && !inViewOnly()) {
            f.L____(lname(), ": action<Req", uc(), ", Res", uc(), ">(", lname(), "Impl, Action", page().module.unameLast, ".Uc", uc(), ".", actionKey(), "),");
        }
    }


}
