package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitModification extends Action {

    private Field[] fieldList = new Field[0];

    public ActionInitModification() {
        this.fieldList = new Field[0];
    }

    public ActionInitModification(Field... fieldList) {
        this.fieldList = fieldList;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addCtrlImport(f);
        }
    }

    public void addMdlImport(MCFlow f) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addMdlImport(f);
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byFatherId() && entity().haveFather) {
            f.addMdlRequestAttribute("id" + entity().ufather, "string");
        }
        f.addMdlRequestAttribute("id" + entity().uname, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addMdlResultAttribute(f);
        }
    }

    public void addMdlStateAttribute(MCFlow f) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addMdlStateAttribute(f);
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addMdlSelector(f, uc());
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entity().lname, " = await Service", entity().uname, ".recupererParId(");
        if (byGrandFatherId() && entity().haveGrandFather) {
            f.__("requete.id" + entity().ugrandfather, ", ");
        }
        if (byFatherId() && entity().haveFather) {
            f.__("requete.id" + entity().ufather, ", ");
        }
        f.__("requete.id", entity().uname, ");");
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addCtrlImplementation(f);
        }
        f.L("};");
    }



    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entity().lname, " = action.payload.", entity().lname, ";");

        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addMdlExtraReducer(f);
        }

        f.L____________("})");
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".initModification", entity().uname, ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("success && form.setFieldsValue(resultat.", entity().lname, ");");
        f.totalScript().L____("}, [success]);");
        f.addSpecificSelector("resultat", uc() + "Resultat", mvcPath() + "/Mdl" + uc());
        f.useExecute("execute, success");
        f.useEffect();

        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

}
