package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class ListPaginatedServiceInjection extends ActionServiceInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entity().uname + ", I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (");
        if (byFatherId() && entity().haveFather) {
            f.__("id" + entity().ufather, ": string, ");
        }

        f.__("pageCourante: number) => {");
        f.L____("const listePaginee", entity().uname, ": IListePaginee", entity().uname, " = {} as IListePaginee", entity().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entity().uname, ": Page<I", entity().uname, "> = (await axios.get<Page<I", entity().uname, ">>(`${API_URL}/", entity().lname, "/", lcoreName());

        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/${id", entity().ufather, "}");
        }

        f.__("?page()=${requetePage.page()}&size=${requetePage.size}");

        f.__("`)).data;");

        f.L____("listePaginee", entity().uname, ".liste = page()", entity().uname, ".content;");
        f.L____("listePaginee", entity().uname, ".pagination = MapperPagination.creerPagination(page()", entity().uname, ");");
        f.L____("return listePaginee", entity().uname, ";");

        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
