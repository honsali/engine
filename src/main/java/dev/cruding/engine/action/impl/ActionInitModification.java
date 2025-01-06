package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.RefChamp;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionInitModification extends Action {

    private Champ[] fieldList = new Champ[0];

    public ActionInitModification() {
        this.fieldList = new Champ[0];
    }

    public ActionInitModification(Champ... fieldList) {
        this.fieldList = fieldList;
    }


    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addCtrlImport(f);
            }
        }
    }

    public void addMdlImport(MCFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlImport(f);
            }
        }
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byFatherId() && entite().haveFather) {
            f.addMdlRequestAttribute("id" + entite().ufather, "string");
        }
        f.addMdlRequestAttribute("id" + entite().uname, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlResultAttribute(f);
            }
        }
    }

    public void addMdlStateAttribute(MCFlow f) {
        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlStateAttribute(f);
            }
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlSelector(f, uc);
            }
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererParId(");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("requete.id" + entite().ugrandfather, ", ");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("requete.id" + entite().ufather, ", ");
        }
        f.__("requete.id", entite().uname, ");");
        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addCtrlImplementation(f);
            }
        }
        f.L("};");
    }



    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");

        for (Champ c : fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlExtraReducer(f);
            }
        }

        f.L____________("})");
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________(f.getPretCondition(), "execute(Ctrl", uc(), ".initModification", entite().uname, ");");
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
