package dev.cruding.engine.action.create.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class CreateServiceInjection extends ActionServiceInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const creer = async (");
        if (byFatherId() && entity().haveFather) {
            f.__("id", entity().father.uname, ": string, ");
        }
        f.__(entity().lname, ": I", entity().uname, ") => {");
        f.L____("const { data } = await axios.post(`${API_URL}", entity().apiDomainPath());
        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/${id", entity().ufather, "}");
        }
        f.__("/", entity().lname, "`, ", entity().lname);
        f.__(");");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
