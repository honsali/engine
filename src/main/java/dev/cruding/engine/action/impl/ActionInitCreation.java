package dev.cruding.engine.action.impl;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitCreation extends Action {

    private Champ[] fieldList = new Champ[0];

    public ActionInitCreation() {
        this.fieldList = new Champ[0];
    }

    public ActionInitCreation(Champ... fieldList) {
        this.fieldList = fieldList;
    }

    public void addCtrlImport(CtrlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addCtrlImport(f);
            }
        }
    }

    public void addMdlImport(MdlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlImport(f);
            }
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlResultAttribute(f);
            }
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlStateAttribute(f);
            }
        }
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlSelector(f, uc);
            }
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = {");
        String result = Arrays.asList(fieldList).stream().filter(field -> field.init != null).map(field -> " " + field.lname + ": " + field.init).collect(Collectors.joining(","));
        f.__(result, StringUtils.isNotBlank(result) ? " " : "");
        f.__("};");
        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addCtrlImplementation(f);
            }
        }
        f.L("};");
    }



    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");

        for (Champ c : fieldList) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addMdlExtraReducer(f);
            }
        }

        f.L____________("})");
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".initCreation", entite().uname, ");");
        f.totalScript().L____("}, [", f.getPretArray(), "]);");
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("success && form.setFieldsValue(resultat.", entite().lname, ");");
        f.totalScript().L____("}, [success]);");
        f.addSpecificSelector("resultat", uc() + "Resultat", mvcPath() + "/Mdl" + uc());
        f.useExecute("execute, success");
        f.useEffect();

        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

}
