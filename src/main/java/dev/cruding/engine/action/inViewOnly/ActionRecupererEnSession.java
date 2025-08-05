package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.MdlRecupererEnSessionInjection;
import dev.cruding.engine.action.inViewOnly.injection.ViewRecupererEnSessionInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionRecupererEnSession extends Action {


    public String typeVariable;

    public ActionRecupererEnSession(Entite entite, Element element, String nomVariable) {
        super(ActionType.FLOW, "###", entite, element);
        isVide(true);
        inViewOnly();
        this.enTantQueListe = nomVariable != null && nomVariable.startsWith("liste");
        String array = this.enTantQueListe ? "[]" : "";
        if (entite == null && nomVariable != null) {
            this.nomVariable = nomVariable;
            this.typeVariable = "string" + array;
        } else if (entite != null && nomVariable == null) {
            this.nomVariable = entite.lname;
            this.typeVariable = "I" + entite.uname + array;
        } else if (entite != null && nomVariable != null) {
            this.nomVariable = nomVariable;
            this.typeVariable = "I" + entite.uname + array;
        }
    }


    public void overrideActionInjection() {
        mdlActionInjection = new MdlRecupererEnSessionInjection(nomVariable, typeVariable);
        viewActionInjection = new ViewRecupererEnSessionInjection(nomVariable);
    }

}
