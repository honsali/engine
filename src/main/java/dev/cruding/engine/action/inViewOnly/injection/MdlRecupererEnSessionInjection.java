package dev.cruding.engine.action.inViewOnly.injection;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlRecupererEnSessionInjection extends MdlActionInjection {

    public String nomVariable;
    public String typeVariable;

    public MdlRecupererEnSessionInjection(String variable, String typeVariable) {
        this.nomVariable = variable;
        this.typeVariable = typeVariable;
    }

    public void addMdlImport(MdlFlow f) {
        if (typeVariable.startsWith("I")) {
            f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

    public void addMdlStateAttribute(MdlFlow f) {

        f.addMdlStateAttribute(nomVariable, typeVariable);
        f.addMdlSelectorAttribute(nomVariable, StringUtils.capitalize(nomVariable));
    }

    public void addUseSelector(MdlFlow f) {
        f.L________(nomVariable + ",");
    }
}
