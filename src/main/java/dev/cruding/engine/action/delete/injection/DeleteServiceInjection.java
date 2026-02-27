package dev.cruding.engine.action.delete.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class DeleteServiceInjection extends ActionServiceInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const supprimer = async (id", entity().uname, ": string) => {");
        f.L____("await axios.delete(`${API_URL}/", entity().lname, "/${id", entity().uname, "}`);");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
