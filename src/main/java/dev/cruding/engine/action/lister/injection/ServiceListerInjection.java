package dev.cruding.engine.action.lister.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceListerInjection extends ServiceActionInjection {

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameSansEntite(), " = async (");
        if (parIdPere() && entite().havePere) {
            f.__("id" + entite().upere, ": string, ");
        }

        f.__(") => {");
        f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${API_URL}");

        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/${id", entite().upere, "}");
        }

        f.__("/", entite().lname, "`)).data;");

        f.L____("return liste", entite().uname, ";");

        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
