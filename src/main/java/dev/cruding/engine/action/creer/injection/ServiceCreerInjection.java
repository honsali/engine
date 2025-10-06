package dev.cruding.engine.action.creer.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceCreerInjection extends ServiceActionInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const creer = async (", entite().lname, ": I", entite().uname, ") => {");
        f.L____("const { data } = await axios.post(`${API_URL}");
        if (parIdPere() && entite().havePere) {
            f.__(entite().lpere, "/${", entite().upere, "}");
        }
        f.__("/", entite().lname, "`, ", entite().lname);
        f.__(");");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
