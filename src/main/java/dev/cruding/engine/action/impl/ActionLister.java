package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionLister extends Action {


    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (byGrandPereId() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (byPereId() && entite().havePere)
            f.addMdlRequestAttribute("id" + entite().upere, "string");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("liste" + entite().uname, "I" + entite().uname + "[]");

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("liste" + entite().uname, "I" + entite().uname + "[]");
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        f.L("export const selectListe", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.liste", entite().uname, ");");
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.liste", entite().uname, " = await Service", entite().uname, ".", lcoreName(), "(");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        if (byPereId() && entite().havePere) {
            f.__("requete.id", entite().upere);
        }
        f.__(");");
        f.L("};");
    }

    public void addMdlExtraReducer(MdlFlow f) {
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
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("id" + entite().ugrandPere, ": string, ");
        }
        if (byPereId() && entite().havePere) {
            f.__("id" + entite().upere, ": string");
        }
        f.__(") => {");
        f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/", lcoreName());
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/${id", entite().ugrandPere, "}");
        }
        if (byPereId() && entite().havePere) {
            f.__("/", entite().lpere, "/${id", entite().upere, "}");
        }
        f.__("`)).data;");
        f.L____("return liste", entite().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
