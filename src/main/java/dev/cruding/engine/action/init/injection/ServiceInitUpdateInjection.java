package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.JsFlow;
import dev.cruding.engine.injection.ActionServiceInjection;

public class ServiceInitUpdateInjection extends ActionServiceInjection {

    private String name = "";
    private String params = "";
    private String url = "";

    public ServiceInitUpdateInjection() {

    }

    public void addServiceImport(JsFlow f) {
        f.addJsImport("{ I" + entity().uname + " }", "./Domaine" + entity().uname);
        init();
    }

    public void init() {

        if (byId()) {
            name = name + "ParId";
            params = params + "id" + entity().uname + " :string";
            url = url + "/${id" + entity().uname + "}";
        }
        if (byFatherId() && entity().haveFather) {
            name = name + "ParId" + entity().ufather;
            params = params + "id" + entity().ufather + " :string";
            url = url + "/" + entity().lfather + "/${id" + entity().ufather + "}";
        }
    }

    public void addServiceImplementation(Flow f) {

        f.L("");
        f.L("const recuperer", name, " = async (", params, ") => {");
        f.L____("const ", entity().lname, ": I", entity().uname, " = (await axios.get<I", entity().uname, ">(`${API_URL}/", entity().lname, "/", url, "`)).data;");
        f.L____("return ", entity().lname, ";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____("recuperer" + name + ",");
    }
}
