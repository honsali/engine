package dev.cruding.engine.action.get.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class GetFromModelCtrlInjection extends ActionCtrlInjection {

    public String mdlName;

    public GetFromModelCtrlInjection(String mdlName) {
        this.mdlName = "mdl" + StringUtils.capitalize(mdlName);
    }

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("const { ", mdlName, " } = thunkAPI.getState() as any;");
        if (asList()) {
            f.L____("resultat.liste", entity().uname, " = ", mdlName, ".liste", entity().uname, ";");
        } else {
            f.L____("resultat.", entity().lname, " = ", mdlName, ".", entity().lname, ";");
        }
    }


}
