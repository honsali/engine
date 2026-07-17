package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class BasicServiceInjection extends ActionServiceInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (");
        boolean withComma = false;
        if (byFatherId() && entity().haveFather) {
            f.__("id" + entity().ufather, ": string, ");
            withComma = true;
        }
        if (byId()) {
            f.__("id", entity().uname, ": string, ");
            withComma = true;
        }
        if (byField() != null) {
            for (Field c : byField()) {
                f.__("", c.lname, ", ");
            }
            withComma = true;
        }
        if (byForm()) {
            f.__(entity().lname, ": I", entity().uname, ", ");
            withComma = true;
        }
        if (withComma) {
            f.removeAfterLastComma();
        }
        f.__(") => {");
        f.L____("const { data } = await axios.", lrest(), "(`${API_URL}", entity().apiDomainPath(), "/", entity().lname, "/");
        if (byFatherId() && entity().haveFather) {
            f.__("/", entity().lfather, "/${id" + entity().ufather, "}");
        }
        if (byId()) {
            f.__("/${id", entity().uname, "}");
        }
        f.__("`");
        if (byForm() || byEntity()) {
            f.__(", ", entity().lname);
        }
        f.__(")");
        f.__(";");

        f.L____("return data");
        if (resultIn() != null) {
            f.__(".", resultIn().lname);
        }
        f.__(";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
