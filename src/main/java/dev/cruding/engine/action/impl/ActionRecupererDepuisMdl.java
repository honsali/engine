package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionRecupererDepuisMdl extends Action {

    private String mdlName = "";

    public ActionRecupererDepuisMdl(String mdlName) {
        this.mdlName = mdlName;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSpecificSelector(entite().uname, mvcPath() + "/Mdl" + uc());
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".", lname(), ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());

        f.addSpecificSelector(entite().lname, entite().uname, mvcPath() + "/Mdl" + uc());
        return true;
    }

    public void addCtrlImport(CtrlFlow f) {}

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("id" + entite().uname, "string");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (byPereId() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        f.L("export const select", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.", entite().lname, ");");
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const { mdl", mdlName, " } = thunkAPI.getState() as any;");
        f.L____("resultat.", entite().lname, " = mdl", mdlName, ".", entite().lname, ";");
        f.L("};");
    }



    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");
        f.L____________("})");
    }

}
