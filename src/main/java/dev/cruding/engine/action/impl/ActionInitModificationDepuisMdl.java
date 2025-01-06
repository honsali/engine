package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.RefChamp;
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
        f.totalScript().L________("success && form.setFieldsValue(resultat.", entite().lname, ");");
        f.totalScript().L____("}, [success]);");
        f.useExecute("execute, success");
        f.addSpecificSelector("resultat", uc() + "Resultat", mvcPath() + "/Mdl" + uc());
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }


    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        for (Champ c : formulaire.fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addCtrlImport(f);
            }
        }
    }

    public void addMdlImport(MCFlow f) {
        for (Champ c : formulaire.fieldList) {
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
        for (Champ c : formulaire.fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlResultAttribute(f);
            }
        }
    }

    public void addMdlStateAttribute(MCFlow f) {
        for (Champ c : formulaire.fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlStateAttribute(f);
            }
        }
    }

    public void addMdlSelector(MCFlow f, String uc) {
        for (Champ c : formulaire.fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlSelector(f, uc);
            }
        }
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: ReqModifier", entite().uname, ", resultat: ResModifier", entite().uname, ", thunkAPI) => {");
        f.L____("const { mdl", mdlName, " } = thunkAPI.getState() as any;");
        f.L____("resultat.", entite().lname, " = mdl", mdlName, ".", entite().lname, ";");
        for (Champ c : formulaire.fieldList) {
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

        for (Champ c : formulaire.fieldList) {
            if (c instanceof RefChamp) {
                ((RefChamp) c).addMdlExtraReducer(f);
            }
        }

        f.L____________("})");
    }

}
