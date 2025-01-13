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
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("id" + entite().ugrandPere, ": string, ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("id" + entite().upere, ": string");
        }
        f.__(") => {");
        f.L____("const liste", entite().uname, ": I", entite().uname, "[] = (await axios.get<I", entite().uname, "[]>(`${resourceUri}/", lcoreName());
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/${id", entite().ugrandPere, "}");
        }
        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/${id", entite().upere, "}");
        }
        f.__("`)).data;");
        f.L____("return liste", entite().uname, ";");
        f.L("};");
    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
