package dev.cruding.engine.action.maj.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceMajInjection extends ServiceActionInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const maj = async (", entite().lname, ": I", entite().uname, ") => {");
        f.L____("const { data } = await axios.put(`${API_URL}/", entite().lname, "/${", entite().lname, ".id}`, ", entite().lname, ");");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
