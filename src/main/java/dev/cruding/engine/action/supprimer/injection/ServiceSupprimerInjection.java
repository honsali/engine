package dev.cruding.engine.action.supprimer.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceSupprimerInjection extends ServiceActionInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const supprimer = async (id", entite().uname, ": string) => {");
        f.L____("await axios.delete(`${API_URL}/", entite().lname, "/${id", entite().uname, "}`);");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
