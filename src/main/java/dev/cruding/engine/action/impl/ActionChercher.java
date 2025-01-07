package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JavaFlow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.flow.MdlFlow;

public class ActionChercher extends Action {


    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        f.addCtrlImport("{ util }", "waxant");
    }

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ IListePaginee" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        f.addMdlImport("{ IRequete" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        f.addMdlRequestAttribute("form", "any");
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
        f.addMdlResultAttribute("filtre", "IRequete" + entite().uname);

    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute("listePaginee" + entite().uname, "IListePaginee" + entite().uname);
        f.addMdlStateAttribute("filtre", "IRequete" + entite().uname);
    }

    public void addMdlSelector(MdlFlow f, String uc) {
        f.L("export const selectListePaginee", entite().uname, " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.listePaginee", entite().uname, ");");
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lname(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".", lcoreName(), "({ ...dataForm, pageCourante: 0 });");
        f.L____("resultat.filtre = dataForm;");
        f.L("};");
    }

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lname(), ".fulfilled, (state, action) => {");
        f.L________________("state.resultat = action.payload;");
        f.L________________("state.listePaginee", entite().uname, " = action.payload.listePaginee", entite().uname, ";");
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
        f.L____________("@Query(value = \"select x from ", entite().uname, " x where ( :#{#query.", entite().lid, "} is null OR lower(x.", entite().lid, ") like lower(CONCAT('%',:#{#query.", entite().lid, "},'%')))\")  ");
        f.L____________("Page<", entite().uname, "> chercher(@Param(\"query\") ", entite().uname, " ", entite().lname, ", Pageable page()able);");
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
        f.L____("public Page<", entite().uname, "> ", lcoreName(), "(@RequestBody ", entite().uname, " ", entite().lname, ", @ParameterObject Pageable page()able) {");
        f.L________("return ", entite().lname, "Repository.", lcoreName(), "(", entite().lname, ", page()able);");
        f.L____("}");
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entite().uname + ", I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lcoreName(), " = async (", entite().lname, ": I", entite().uname, ", pageCourante: number) => {");
        f.L____("const listePaginee", entite().uname, ": IListePaginee", entite().uname, " = {} as IListePaginee", entite().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entite().uname, ": Page<I", entite().uname, "> = (await axios.post<Page<I", entite().uname, ">>(`${resourceUri}/", lcoreName(), "?page()=${requetePage.page()}&size=${requetePage.size}`, ", entite().lname).__(")).data;");
        f.L____("listePaginee", entite().uname, ".liste = page()", entite().uname, ".content;");
        f.L____("listePaginee", entite().uname, ".pagination = MapperPagination.creerPagination(page()", entite().uname, ");");
        f.L____("return listePaginee", entite().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lcoreName(), ",");
    }
}
