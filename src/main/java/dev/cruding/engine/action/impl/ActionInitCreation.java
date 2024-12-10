package dev.cruding.engine.action.impl;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitCreation extends Action {

    private Field[] fieldList = new Field[0];

    public ActionInitCreation() {
        this.fieldList = new Field[0];
    }

    public ActionInitCreation(Field... fieldList) {
        this.fieldList = fieldList;
    }

    public void addCtrlImport(MCFlow f) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addCtrlImport(f);
        }
    }

    public void addMdlImport(MCFlow f) {
        for (int i = 0; i < fieldList.length; i++) {
            fieldList[i].addMdlImport(f);
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {}

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
        f.L____("resultat.", entity().lname, " = {");
        String result = Arrays.asList(fieldList).stream().filter(field -> field.init != null).map(field -> " " + field.lname + ": " + field.init).collect(Collectors.joining(","));
        f.__(result, StringUtils.isNotBlank(result) ? " " : "");
        f.__("};");
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
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".initCreation", entity().uname, ");");
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
