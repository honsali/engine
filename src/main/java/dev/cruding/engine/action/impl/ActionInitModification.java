package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitModification extends Action {


    public ActionInitModification() {
        super("initModification");
    }

    public ActionInitModification(Formulaire formulaire) {
        super("initModification");
        this.formulaire = formulaire;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addCtrlImport(f, "init");
        }
    }

    public void addMdlImport(MCFlow f) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlImport(f, "init");
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byFatherId && entity.haveFather) {
            f.addMdlRequestAttribute("id" + entity.ufather, "string");
        }
        f.addMdlRequestAttribute("id" + entity.uname, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlResultAttribute(f, "init");
        }

    }

    public void addMdlStateAttribute(MCFlow f) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlStateAttribute(f, "init");
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlSelector(f, uc);
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("resultat.", entity.lname, " = await Service", entity.uname, ".recupererParId(");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
        }
        if (byFatherId && entity.haveFather) {
            f.__("requete.id" + entity.ufather, ", ");
        }
        f.__("requete.id", entity.uname, ");");
        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addCtrlImplementation(f, "init");
        }
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

        for (int i = 0; i < formulaire.fieldList.length; i++) {
            formulaire.fieldList[i].addMdlExtraReducer(f, "init");
        }

        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L____("    ", f.getPretCondition(), "execute(Ctrl", uc, ".initModification", entity.uname, ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L____("    success && form.setFieldsValue(resultat.", entity.lname, ");");
        f.totalScript().L____("}, [success]);");
        f.addSpecificSelector("resultat", uc + "Resultat", mvcPath + "/Mdl" + uc);
        f.useExecute("execute, success");
        f.useEffect();

        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

}
