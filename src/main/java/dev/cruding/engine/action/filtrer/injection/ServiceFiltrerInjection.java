package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceFiltrerInjection extends ServiceActionInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ Page }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ IListePaginee" + entite().uname + ", I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameSansEntite(), " = async (", entite().lname, ": I", entite().uname, ", pageCourante = 0): Promise<IListePaginee" + entite().uname + "> => {");
        f.L____("const pageable = MapperPagination.creerPageable(pageCourante);");
        f.L____("const { data } = await axios.post<Page<I", entite().uname, ">>(`${API_URL}/", entite().lname, "/", lnameSansEntite(), "`, ", entite().lname).__(", { params: { page: pageable.page, size: pageable.size } });");
        f.L____("return {");
        f.L________("liste: data.content,");
        f.L________("pagination: MapperPagination.creerPagination<I", entite().uname, ">(data),");
        f.L____("};");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
