package dev.cruding.engine.action.recuperer;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.action.recuperer.injection.CtrlRecupererDepuisMdlInjection;
import dev.cruding.engine.action.recuperer.injection.MdlRecupererInjection;
import dev.cruding.engine.action.recuperer.injection.ViewRecupererInjection;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;

public class ActionRecupererDepuisMdl extends Action {

    private String mdlName = "";


    public ActionRecupererDepuisMdl(Entite entite, Element element, String mdlName) {
        super(ActionType.NOUI, "recupererEnSession", entite, element);
        this.mdlName = mdlName;
        init();
    }

    public void overrideActionInjection() {
        ctrlActionInjection = new CtrlRecupererDepuisMdlInjection(mdlName);
        mdlActionInjection = new MdlRecupererInjection();
        viewActionInjection = new ViewRecupererInjection();
    }



}
