package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class FindServiceInjection extends ActionServiceInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entity().uname + ", I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (", entity().lname, ": I", entity().uname, ", pageCourante: number) => {");
        f.L____("const listePaginee", entity().uname, ": IListePaginee", entity().uname, " = {} as IListePaginee", entity().uname, ";");
        f.L____("const requetePage = MapperPagination.creerRequetePage(pageCourante);");
        f.L____("const page()", entity().uname, ": Page<I", entity().uname, "> = (await axios.post<Page<I", entity().uname, ">>(`${API_URL}/", entity().lname, "/", lnameWithoutEntity(), "?page()=${requetePage.page()}&size=${requetePage.size}`, ", entity().lname).__(")).data;");
        f.L____("listePaginee", entity().uname, ".liste = page()", entity().uname, ".content;");
        f.L____("listePaginee", entity().uname, ".pagination = MapperPagination.creerPagination(page()", entity().uname, ");");
        f.L____("return listePaginee", entity().uname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
