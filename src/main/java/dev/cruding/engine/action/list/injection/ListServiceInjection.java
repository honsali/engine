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
        f.L____("const liste", entity().uname, ": I", entity().uname, "[] = (await axios.get<I", entity().uname, "[]>(`${API_URL}");

        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/${id", entity().ufather, "}");
        }

        f.__("/", entity().lname, "`)).data;");

        f.L____("return liste", entity().uname, ";");

        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
