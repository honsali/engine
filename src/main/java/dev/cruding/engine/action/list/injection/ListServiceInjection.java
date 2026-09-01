package dev.cruding.engine.action.list.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class ListServiceInjection extends ActionServiceInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (");
        if (byFatherId() && entity().haveFather) {
            f.__("id" + entity().ufather, ": string");
        }
        f.__(") => {");
        if (byFatherId() && entity().haveFather) {
            f.L____("const { data } = await axios.get<I", entity().uname, "[]>(`${API_URL}", entity().father.referencedEntity.apiCollectionPath(), "/${id", entity().ufather, "}/", entity().apiCollectionName());
        } else {
            f.L____("const { data } = await axios.get<I", entity().uname, "[]>(`${API_URL}", entity().apiCollectionPath());
        }
        f.__("`);");
        f.L____("return data;");

        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
