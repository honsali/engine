package dev.cruding.engine.action.listerEnPage.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceListerEnPageInjection extends ServiceActionInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entite().uname + ", I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameSansEntite(), " = async (");
        if (parIdPere() && entite().havePere) {
            f.__("id" + entite().upere, ": string, ");
        }

        f.__("pageCourante: number) => {");
        f.L____("const listePaginee", entite().uname, ": IListePaginee", entite().uname, " = {} as IListePaginee", entite().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entite().uname, ": Page<I", entite().uname, "> = (await axios.get<Page<I", entite().uname, ">>(`${API_URL}/", entite().lname, "/", lcoreName());

        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/${id", entite().upere, "}");
        }

        f.__("?page()=${requetePage.page()}&size=${requetePage.size}");

        f.__("`)).data;");

        f.L____("listePaginee", entite().uname, ".liste = page()", entite().uname, ".content;");
        f.L____("listePaginee", entite().uname, ".pagination = MapperPagination.creerPagination(page()", entite().uname, ");");
        f.L____("return listePaginee", entite().uname, ";");

        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
