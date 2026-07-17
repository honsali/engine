package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class GetByFieldServiceInjection extends ActionServiceInjection {



    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entity().uname + " }", "./Domaine" + entity().uname);
    }

    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameWithoutEntity(), " = async (", byField()[0].lname, entity().uname, ": string) => {");
        f.L____("const { data } = await axios.get<I", entity().uname, ">(`${API_URL}", entity().apiDomainPath(), "/", entity().lname, "/${", byField()[0].lname, entity().uname, "}`);");
        f.L____("return data;");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameWithoutEntity(), ",");
    }
}
