package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class FindServiceInjection extends ActionServiceInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ PageResponse }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (", entity().lname, ": I", entity().uname, ", pageCourante: number) => {");
        f.L____("const pageable = MapperPagination.creerPageable(pageCourante);");
        f.L____("const { data } = await axios.post<PageResponse<I", entity().uname, ">>(`${API_URL}", entity().apiCollectionPath(), "/", lnameWithoutEntity(), "`, ", entity().lname).__(", { params: { page: pageable.page, size: pageable.size } });");
        f.L____("return {");
        f.L________("liste: data.items,");
        f.L________("pagination: MapperPagination.creerPagination<I", entity().uname, ">(data),");
        f.L____("};");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
