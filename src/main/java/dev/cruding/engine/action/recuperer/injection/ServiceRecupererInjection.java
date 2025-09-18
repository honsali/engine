package dev.cruding.engine.action.recuperer.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceRecupererInjection extends ServiceActionInjection {



    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entite().uname + " }", "./Domaine" + entite().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameSansEntite(), " = async (", parChamp()[0].lname, entite().uname, ": string) => {");
        f.L____("const { data } = await axios.get<I", entite().uname, ">(`${API_URL}/", entite().lname, "/${", parChamp()[0].lname, entite().uname, "}`);");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
