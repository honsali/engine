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
        f.L("const ", lnameSansEntite(), " = async (pageCourante: number) => {");
        f.L____("const listePaginee", entite().uname, ": IListePaginee", entite().uname, " = {} as IListePaginee", entite().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entite().uname, ": Page<I", entite().uname, "> = (await axios.get<Page<I", entite().uname, ">>(`${resourceUri}/", lnameSansEntite(), "?page()=${requetePage.page()}&size=${requetePage.size}`)).data;");
        f.L____("listePaginee", entite().uname, ".liste = page()", entite().uname, ".content;");
        f.L____("listePaginee", entite().uname, ".pagination = MapperPagination.creerPagination(page()", entite().uname, ");");
        f.L____("return listePaginee", entite().uname, ";");
        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
