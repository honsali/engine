package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceRecupererInjection extends ServiceActionInjection {

    public String lnameChamp;
    public String unameChamp;

    public ServiceRecupererInjection(String lnameChamp, String unameChamp) {
        this.lnameChamp = lnameChamp;
        this.unameChamp = unameChamp;
    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const recupererPar", unameChamp, " = async (", lnameChamp, entite().uname, ": string) => {");
        f.L____("const ", entite().lname, ": I", entite().uname, " = (await axios.get<I", entite().uname, ">(`${resourceUri}/${", lnameChamp, entite().uname, "}`)).data;");
        f.L____("return ", entite().lname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____("recupererPar" + unameChamp + ",");
    }
}
