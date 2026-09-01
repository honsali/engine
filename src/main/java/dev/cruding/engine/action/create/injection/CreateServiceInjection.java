package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class CreateServiceInjection extends ActionServiceInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const creer = async (");
        if (byFatherId() && entity().haveFather) {
            f.__("id", entity().father.uname, ": string, ");
        }
        f.__(entity().lname, ": I", entity().uname, ") => {");
        f.L____("const { data } = await axios.post<I", entity().uname, ">(`${API_URL}");
        if (byFatherId() && entity().haveFather) {
            f.__(entity().father.referencedEntity.apiCollectionPath(), "/${id", entity().ufather, "}/", entity().apiCollectionName());
        } else {
            f.__(entity().apiCollectionPath());
        }
        f.__("`, ", entity().lname);
        f.__(");");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
