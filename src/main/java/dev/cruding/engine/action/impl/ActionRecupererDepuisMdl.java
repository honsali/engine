package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionRecupererDepuisMdl extends Action {

    private String mdlName = "";

    public ActionRecupererDepuisMdl(String mdlName) {
        super("recupererEnSession");
        this.mdlName = mdlName;
    }

    public Action entity(Entity entity) {
        this.entity = entity;
        this.lname = actionType;
        return this;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSpecificSelector(entity.uname, mvcPath + "/Mdl" + uc);
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc, ".", lname, ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

    public void addCtrlImport(MCFlow f) {}

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("id" + entity.uname, "string");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity.ugrandfather, "string");
        }
        if (byFatherId && entity.haveFather) {
            f.addMdlRequestAttribute("id" + entity.ufather, "string");
        }
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute(entity.lname, "I" + entity.uname);
    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(entity.lname, "I" + entity.uname);
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const select", entity.uname, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.", entity.lname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("const { mdl", mdlName, " } = thunkAPI.getState() as any;");
        f.L____("resultat.", entity.lname, " = mdl", mdlName, ".", entity.lname, ";");
        f.L("};");
    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____("", lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("    state.", entity.lname, " = action.payload.", entity.lname, ";");
        f.L____________("})");
    }

}
