package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionListerParIdPere extends Action {


    public void actionnable(Actionnable actionnable) {
        this.actionnable = actionnable;
        this.actionnable.lcoreName("listerParId" + entite().father.uname);
        this.actionnable.lname("lister" + entite().uname + "ParId" + entite().father.uname);
    };

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
        f.__("requete.id", entite().ufather);
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
        f.totalScript().L________("execute(Ctrl", uc(), ".", lname(), ");");
        f.totalScript().L____("}, []);");
        f.useExecute();
        f.useEffect();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____("List<", entite().uname, "> findAllBy", entite().ufather, "_IdOrderBy", orderBy(), "(Long id", entite().ufather, ");");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
        f.addJavaImport("java.util.stream.Collectors");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName(), "/{id", entite().ufather, "}\")");
        f.L____("public List<", entite().uname, "Dto> ", lcoreName(), "(@PathVariable Long id", entite().ufather, ") {");
        f.L________("return ", entite().lname, "Repository.findAllBy", entite().ufather, "_IdOrderBy", orderBy(), "(id", entite().ufather, ").stream().map(", entite().uname, "Dto::asEntity).collect(Collectors.toList());");
        f.L____("}");
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (");
        f.__("id" + entite().ufather, ": string");
        f.__(") => {");
        f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/", lcoreName());
        f.__("/${id", entite().ufather, "}");
        f.__("`)).data;");
        f.L____("return liste", entite().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
