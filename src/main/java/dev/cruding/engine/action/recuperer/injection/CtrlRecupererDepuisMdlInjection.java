package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlRecupererDepuisMdlInjection extends CtrlActionInjection {

    public String mdlName;

    public CtrlRecupererDepuisMdlInjection(String mdlName) {
        this.mdlName = mdlName;
    }

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const { mdl", mdlName, " } = thunkAPI.getState() as any;");
        f.L____("resultat.", entite().lname, " = mdl", mdlName, ".", entite().lname, ";");
        f.L("};");
    }


}
