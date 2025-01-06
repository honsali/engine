package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionLister extends Action {


    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.addMdlRequestAttribute("id" + entite().ugrandfather, "string");
        }
        if (byFatherId() && entite().haveFather)
            f.addMdlRequestAttribute("id" + entite().ufather, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute("liste" + entite().uname, "I" + entite().uname + "[]");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.liste", entite().uname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.liste", entite().uname, " = await Service", entite().uname, ".", lcoreName(), "(");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("requete.id" + entite().ugrandfather, ", ");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("requete.id", entite().ufather);
        }
        f.__(");");
        f.L("};");
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.liste", entite().uname, " = action.payload.liste", entite().uname, ";");
        f.L____________("})");
    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("execute(Ctrl", uc(), ".", lname());
        if (byProp()) {
            f.totalScript().append(", { ").append(element().byProp).append(" }");
        }
        f.totalScript().append(");");
        f.totalScript().L____("}, [");
        if (byProp()) {
            f.totalScript().append(element().byProp);
        }
        f.totalScript().append("]);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L____("List<", entite().uname, "> findAllByOrderBy", orderBy(), "();");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("java.util.stream.Collectors");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName(), "\")");
        f.L____("public List<", entite().uname, "Dto> ", lcoreName(), "() {");
        f.L________("return ", entite().lname, "Repository.findAllByOrderBy", orderBy(), "().stream().map(", entite().uname, "Dto::asEntity).collect(Collectors.toList());");
        f.L____("}");
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (");
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("id" + entite().ugrandfather, ": string, ");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("id" + entite().ufather, ": string");
        }
        f.__(") => {");
        f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/", lcoreName());
        if (byGrandFatherId() && entite().haveGrandFather) {
            f.__("/", entite().lgrandfather, "/${id", entite().ugrandfather, "}");
        }
        if (byFatherId() && entite().haveFather) {
            f.__("/", entite().lfather, "/${id", entite().ufather, "}");
        }
        f.__("`)).data;");
        f.L____("return liste", entite().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
