package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionLister extends ActionAvecTable {



    public ActionLister() {
        super("lister");
        withService = true;
    }

    public ActionLister(boolean byFatherId) {
        this();
        this.byFatherId = byFatherId;
    }

    public ActionLister(boolean byFatherId, boolean byGrandFatherId) {
        this(byFatherId);
        this.byGrandFatherId = byGrandFatherId;
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byGrandFatherId && entity.haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity.ugrandfather, "string");
        }
        if (byFatherId && entity.haveFather)
            f.addMdlRequestAttribute("id" + entity.ufather, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute("liste" + entity.uname, "I" + entity.uname + "[]");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute("liste" + entity.uname, "I" + entity.uname + "[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", entity.uname, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.liste", entity.uname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("resultat.liste", entity.uname, " = await Service", entity.uname, ".", actionType, "(");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
        }
        if (byFatherId && entity.haveFather) {
            f.__("requete.id", entity.ufather);
        }
        f.__(");");
        f.L("};");
    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____(lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("    state.liste", entity.uname, " = action.payload.liste", entity.uname, ";");
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L____("    execute(Ctrl", uc, ".", lname, ");");
        f.totalScript().L____("}, []);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L____________("List<", entity.uname, "> findAllByOrderBy", orderBy, "();");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", actionType, "\")");
        f.L____("public List<", entity.uname, "> ", actionType, "() {");
        f.L________("return ", entity.lname, "Repository.findAllByOrderBy", orderBy, "();");
        f.L____("}");
    }

    public void addServicImplementation(Flow f) {
        f.L("");
        f.L("const ", actionType, " = async (");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("id" + entity.ugrandfather, ": string, ");
        }
        if (byFatherId && entity.haveFather) {
            f.__("id" + entity.ufather, ": string");
        }
        f.__(") => {");
        f.L____("const liste", entity.uname, ": I", entity.uname, "[] = (await axios.get<I", entity.uname, "[]>(`${resourceUri}/", actionType);
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("/${id", entity.ugrandfather, "}");
        }
        if (byFatherId && entity.haveFather) {
            f.__("/${id", entity.ufather, "}");
        }
        f.__("`)).data;");
        f.L____("return liste", entity.uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(actionType, ",");
    }
}
