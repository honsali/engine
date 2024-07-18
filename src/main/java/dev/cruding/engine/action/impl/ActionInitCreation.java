package dev.cruding.engine.action.impl;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitCreation extends Action {

    private Formulaire formulaire;

    public ActionInitCreation() {
        super("initCreation");
    }

    public ActionInitCreation(Formulaire formulaire) {
        super("initCreation");
        this.formulaire = formulaire;
    }

    public void addCtrlImport(MCFlow f) {
        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addCtrlImport(f, "init");
            }
        }

    }

    public void addMdlImport(MCFlow f) {
        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addMdlImport(f, "init");
            }
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {}

    public void addMdlResultAttribute(MCFlow f) {

        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addMdlResultAttribute(f, "init");
            }
        }

    }

    public void addMdlStateAttribute(MCFlow f) {
        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addMdlStateAttribute(f, "init");
            }
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addMdlSelector(f, uc);
            }
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("resultat.", entity.lname, " = {");
        if (this.formulaire != null) {
            String result = Arrays.asList(formulaire.fieldList).stream().filter(field -> field.init != null).map(field -> " " + field.lname + ": " + field.init).collect(Collectors.joining(","));
            f.__(result, StringUtils.isNotBlank(result) ? " " : "");
        }
        f.__("};");
        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addCtrlImplementation(f, "init");
            }
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

        if (this.formulaire != null) {
            for (int i = 0; i < formulaire.fieldList.length; i++) {
                formulaire.fieldList[i].addMdlExtraReducer(f, "init");
            }
        }

        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L____("    ", f.getPretCondition(), "execute(Ctrl", uc, ".initCreation", entity.uname, ");");
        f.totalScript().L____("},  [", f.getPretArray(), "]);");
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
