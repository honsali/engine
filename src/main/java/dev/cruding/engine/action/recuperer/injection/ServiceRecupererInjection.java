package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceRecupererInjection extends ServiceActionInjection {

    public String lnameField;
    public String unameField;

    public ServiceRecupererInjection(String lnameField, String unameField) {
        this.lnameField = lnameField;
        this.unameField = unameField;
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const recupererPar", unameField, " = async (", lnameField, entite().uname, ": string) => {");
        f.L____("const ", entite().lname, ": I", entite().uname, " = (await axios.get<I", entite().uname, ">(`${resourceUri}/${", lnameField, entite().uname, "}`)).data;");
        f.L____("return ", entite().lname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____("recupererPar" + unameField + ",");
    }
}
