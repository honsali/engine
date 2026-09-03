package dev.cruding.engine.action.listPaginated.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class ListPaginatedServiceInjection extends ActionServiceInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ PageResponse }", "modele/commun/pagination/DomainePagination");
        f.addJsImport("MapperPagination", "modele/commun/pagination/MapperPagination");
        f.addJsImport("{ I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (");
        if (byFatherId() && entity().haveFather) {
            f.__("id" + entity().ufather, ": string, ");
        }

        f.__("pageCourante: number = 0) => {");
        f.L____("const pageable = MapperPagination.creerPageable(pageCourante);");
        if (byFatherId() && entity().haveFather) {
            f.L____("const { data } = await axios.get<PageResponse<I", entity().uname, ">>(`${API_URL}", entity().father.referencedEntity.apiCollectionPath(), "/${id", entity().ufather, "}/", entity().apiCollectionName(), "/", lcoreName());
        } else {
            f.L____("const { data } = await axios.get<PageResponse<I", entity().uname, ">>(`${API_URL}", entity().apiCollectionPath(), "/", lcoreName());
        }
        f.__("`, { params: { page: pageable.page, size: pageable.size } });");
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
