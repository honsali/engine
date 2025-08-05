package dev.cruding.engine.action.inViewOnly;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.inViewOnly.injection.MdlSelectionDansMdl;
import dev.cruding.engine.action.inViewOnly.injection.ViewSelectionDansMdl;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionSelectionDansMdl extends Action {

    public ActionSelectionDansMdl(Entite entite, Element element) {
        super(ActionType.NOUI, "changerSelection", entite, element);
        inViewOnly();
    }

    public void overrideActionInjection() {
        mdlActionInjection = new MdlSelectionDansMdl();
        viewActionInjection = new ViewSelectionDansMdl();
    }
}
