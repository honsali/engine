package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitModificationDepuisMdl extends Action {

    private Formulaire formulaire;
    private String mdlName = "";

    public ActionInitModificationDepuisMdl(Formulaire formulaire, String mdlName) {
        this.mdlName = mdlName;
        this.formulaire = formulaire;
    }



    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".", lname(), ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("success && form.setFieldsValue(resultat.", entity().lname, ");");
        f.totalScript().L____("}, [success]);");
        f.useExecute("execute, success");
        f.addSpecificSelector("resultat", uc() + "Resultat", mvcPath() + "/Mdl" + uc());
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addCtrlImport(f);
        }
    }

    public void addMdlImport(MCFlow f) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlImport(f);
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }
        f.addMdlRequestAttribute("id" + entity().uname, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlResultAttribute(f);
        }

    }

    public void addMdlStateAttribute(MCFlow f) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlStateAttribute(f);
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlSelector(f, uc());
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: ReqModifier", entity().uname, ", resultat: ResModifier", entity().uname, ", thunkAPI) => {");
        f.L____("const { mdl", mdlName, " } = thunkAPI.getState() as any;");
        f.L____("resultat.", entity().lname, " = mdl", mdlName, ".", entity().lname, ";");
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addCtrlImplementation(f);
        }
        f.L("};");
    }



    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");

        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlExtraReducer(f);
        }

        f.L____________("})");
    }

}
