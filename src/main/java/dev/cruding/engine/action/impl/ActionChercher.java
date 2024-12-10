package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MCFlow;

public class ActionChercher extends Action {


    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
        f.addCtrlImport("{ util }", "waxant");
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ IListePaginee" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        f.addMdlImport("{ IRequete" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("form", "any");
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
        f.addMdlResultAttribute("filtre", "IRequete" + entity().uname);

    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute("listePaginee" + entity().uname, "IListePaginee" + entity().uname);
        f.addMdlStateAttribute("filtre", "IRequete" + entity().uname);
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListePaginee", entity().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.listePaginee", entity().uname, ");");
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        f.L____("resultat.listePaginee", entity().uname, " = await Service", entity().uname, ".", lcoreName(), "({ ...dataForm, pageCourante: 0 });");
        f.L____("resultat.filtre = dataForm;");
        f.L("};");
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.listePaginee", entity().uname, " = action.payload.listePaginee", entity().uname, ";");
        f.L________________("state.filtre = action.payload.filtre;");
        f.L____________("})");
    }


    public void addRepositoryImport(JavaFlow f) {
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("org.springframework.data.repository.query.Param");
        f.addJavaImport("org.springframework.data.jpa.repository.Query");
    }

    public void addRepositoryDeclaration(JavaFlow f) {
        f.L("");
        f.L____________("@Query(value = \"select x from ", entity().uname, " x where ( :#{#query.", entity().lid, "} is null OR lower(x.", entity().lid, ") like lower(CONCAT('%',:#{#query.", entity().lid, "},'%')))\")  ");
        f.L____________("Page<", entity().uname, "> chercher(@Param(\"query\") ", entity().uname, " ", entity().lname, ", Pageable page()able);");
    }

    public void addResourceImport(JavaFlow f) {
        f.addJavaImport("org.springdoc.core.annotations.ParameterObject");
        f.addJavaImport("org.springframework.data.domain.Page");
        f.addJavaImport("org.springframework.data.domain.Pageable");
        f.addJavaImport("org.springframework.web.bind.annotation.PostMapping");
        f.addJavaImport("org.springframework.web.bind.annotation.RequestBody");
    }

    public void addResourceDeclaration(JavaFlow f) {
        f.L("");
        f.L____("@PostMapping(\"/", lcoreName(), "\")");
        f.L____("public Page<", entity().uname, "> ", lcoreName(), "(@RequestBody ", entity().uname, " ", entity().lname, ", @ParameterObject Pageable page()able) {");
        f.L________("return ", entity().lname, "Repository.", lcoreName(), "(", entity().lname, ", page()able);");
        f.L____("}");
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entity().uname + ", I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (", entity().lname, ": I", entity().uname, ", pageCourante: number) => {");
        f.L____("const listePaginee", entity().uname, ": IListePaginee", entity().uname, " = {} as IListePaginee", entity().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entity().uname, ": Page<I", entity().uname, "> = (await axios.post<Page<I", entity().uname, ">>(`${resourceUri}/", lcoreName(), "?page()=${requetePage.page()}&size=${requetePage.size}`, ", entity().lname).__(")).data;");
        f.L____("listePaginee", entity().uname, ".liste = page()", entity().uname, ".content;");
        f.L____("listePaginee", entity().uname, ".pagination = MapperPagination.creerPagination(page()", entity().uname, ");");
        f.L____("return listePaginee", entity().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
