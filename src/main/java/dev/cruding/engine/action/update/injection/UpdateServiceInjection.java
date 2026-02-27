package dev.cruding.engine.action.update.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class UpdateServiceInjection extends ActionServiceInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const maj = async (", entity().lname, ": I", entity().uname, ") => {");
        f.L____("const { data } = await axios.put(`${API_URL}/", entity().lname, "/${", entity().lname, ".id}`, ", entity().lname, ");");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
