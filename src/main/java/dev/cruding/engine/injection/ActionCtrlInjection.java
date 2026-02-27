package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.flow.CtrlFlow;

public class ActionCtrlInjection extends ActionWrapper {

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameWithEntity(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        addCtrlImplementationCore(f);
        f.L("};");
    }

    public void addCtrlDeclaration(CtrlFlow f) {

        f.L____(lnameWithEntity(), ": action<Req", uc(), ", Res", uc(), ">(", lnameWithEntity(), "Impl, Action", page().module.unameLast, ".Uc", uc(), ".", actionKey(), "),");

    }

    public void addCtrlImplementationCore(CtrlFlow f) {

    }

}
