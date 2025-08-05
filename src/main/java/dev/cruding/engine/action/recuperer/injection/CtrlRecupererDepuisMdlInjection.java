package dev.cruding.engine.action.recuperer.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlRecupererDepuisMdlInjection extends CtrlActionInjection {

    public String mdlName;

    public CtrlRecupererDepuisMdlInjection(String mdlName) {
        this.mdlName = "mdl" + StringUtils.capitalize(mdlName);
    }

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const { ", mdlName, " } = thunkAPI.getState() as any;");
        if (enTantQueListe()) {
            f.L____("resultat.liste", entite().uname, " = ", mdlName, ".liste", entite().uname, ";");
        } else {
            f.L____("resultat.", entite().lname, " = ", mdlName, ".", entite().lname, ";");
        }
        f.L("};");
    }


}
