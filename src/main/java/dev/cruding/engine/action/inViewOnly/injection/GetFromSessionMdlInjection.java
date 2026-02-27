package dev.cruding.engine.action.inViewOnly.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class GetFromSessionMdlInjection extends ActionMdlInjection {

    public String nameVariable;
    public String typeVariable;

    public GetFromSessionMdlInjection(String variable, String typeVariable) {
        this.nameVariable = variable;
        this.typeVariable = typeVariable;
    }

    public void addMdlImport(MdlFlow f) {
        if (typeVariable.startsWith("I")) {
            f.addMdlImport("{ I" + entity().uname + " }", "modele/" + entity().path + "/Domaine" + entity().uname);
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

    public void addMdlStateAttribute(MdlFlow f) {

        f.addMdlStateAttribute(nameVariable, typeVariable);
        f.addMdlSelectorAttribute(nameVariable, StringUtils.capitalize(nameVariable));
    }

    public void addUseSelector(MdlFlow f) {
        f.L________(nameVariable + ",");
    }
}
