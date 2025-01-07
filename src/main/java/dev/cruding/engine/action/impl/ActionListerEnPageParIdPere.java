package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionListerEnPageParIdPere extends Action {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("pageCourante", "number");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        f.addMdlRequestAttribute("id" + entite().upere, "string");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        f.L("export const selectListePaginee", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.listePaginee", entite().uname, ");");
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".", lcoreName(), "(");
        if (byGrandPereId() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        f.__("requete.id", entite().upere);
        f.__(", 0);");
        f.L("};");
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
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
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L____________("Page<", entite().uname, "> findAll(Pageable page()able);");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springdoc.core.annotations.ParameterObject");
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@GetMapping(\"/", lcoreName(), "\")");
        f.L____("public Page<", entite().uname, "> ", lcoreName(), "(@ParameterObject Pageable page()able) {");
        f.L________("return ", entite().lname, "Repository.findBy", entite().upere, "IdOrderBy", entite().uid, "(id", entite().upere, ",page()able);");
        f.L____("}");
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entite().uname + ", I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (id", entite().upere, ": string, pageCourante: number) => {");
        f.L____("const listePaginee", entite().uname, ": IListePaginee", entite().uname, " = {} as IListePaginee", entite().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entite().uname, ": Page<I", entite().uname, "> = (await axios.get<Page<I", entite().uname, ">>(`${resourceUri}/", lcoreName(), "/${id", entite().upere, "}?page()=${requetePage.page()}&size=${requetePage.size}`)).data;");
        f.L____("listePaginee", entite().uname, ".liste = page()", entite().uname, ".content;");
        f.L____("listePaginee", entite().uname, ".pagination = MapperPagination.creerPagination(page()", entite().uname, ");");
        f.L____("return listePaginee", entite().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
