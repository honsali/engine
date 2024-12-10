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
        this.actionnable.lcoreName("listerParId" + entity().father.uname);
        this.actionnable.lname("lister" + entity().uname + "ParId" + entity().father.uname);
    };

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        if (byGrandFatherId() && entity().haveGrandFather) {
            f.addMdlRequestAttribute("id" + entity().ugrandfather, "string");
        }
        f.addMdlRequestAttribute("id" + entity().ufather, "string");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute("liste" + entity().uname, "I" + entity().uname + "[]");

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute("liste" + entity().uname, "I" + entity().uname + "[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", entity().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.liste", entity().uname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.liste", entity().uname, " = await Service", entity().uname, ".", lcoreName(), "(");
        if (byGrandFatherId() && entity().haveGrandFather) {
            f.__("requete.id" + entity().ugrandfather, ", ");
        }
        f.__("requete.id", entity().ufather);
        f.__(");");
        f.L("};");
    }



    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.liste", entity().uname, " = action.payload.liste", entity().uname, ";");
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
        f.L____("List<", entity().uname, "> findAllBy", entity().ufather, "_IdOrderBy", orderBy(), "(Long id", entity().ufather, ");");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("java.util.List");
        f.addJavaImport("org.springframework.web.bind.annotation.GetMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.PathVariable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName(), "/{id", entity().ufather, "}\")");
        f.L____("public List<", entity().uname, "> ", lcoreName(), "(@PathVariable Long id", entity().ufather, ") {");
        f.L________("return ", entity().lname, "Repository.findAllBy", entity().ufather, "_IdOrderBy", orderBy(), "(id", entity().ufather, ");");
        f.L____("}");
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (");
        f.__("id" + entity().ufather, ": string");
        f.__(") => {");
        f.L____("const liste", entity().uname, ": I", entity().uname, "[] = (await axios.get<I", entity().uname, "[]>(`${resourceUri}/", lcoreName());
        f.__("/${id", entity().ufather, "}");
        f.__("`)).data;");
        f.L____("return liste", entity().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
