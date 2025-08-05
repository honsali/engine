package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceInitModificationInjection extends ServiceActionInjection {

    private String nom = "";
    private String params = "";
    private String url = "";

    public ServiceInitModificationInjection() {

    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
        init();
    }

    public void init() {

        if (parId()) {
            nom = nom + "ParId";
            params = params + "id" + entite().uname + " :string";
            url = url + "/${id" + entite().uname + "}";
        }
        if (parIdGrandPere() && entite().haveGrandPere) {
            nom = nom + "ParId" + entite().ugrandPere;
            params = params + "id" + entite().ugrandPere + " :string";
            url = url + "/" + entite().lgrandPere + "/${id" + entite().ugrandPere + "}";
        }
        if (parIdPere() && entite().havePere) {
            nom = nom + "ParId" + entite().upere;
            params = params + "id" + entite().upere + " :string";
            url = url + "/" + entite().lpere + "/${id" + entite().upere + "}";
        }
    }

    public void addServiceImplementation(Flow f) {

        f.L("");
        f.L("const recuperer", nom, " = async (", params, ") => {");
        f.L____("const ", entite().lname, ": I", entite().uname, " = (await axios.get<I", entite().uname,
                ">(`${resourceUri}", url, "`)).data;");
        f.L____("return ", entite().lname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____("recuperer" + nom + ",");
    }
}
