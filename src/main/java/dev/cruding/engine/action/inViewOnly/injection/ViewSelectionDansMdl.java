package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewSelectionDansMdl extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.useDispatch();
        f.totalScript().L____("const changerSelection = (liste) => {");
        f.totalScript().L________("dispatch(Mdl", uc(), ".modifierListe", entite().uname, "(liste));");
        f.totalScript().L____("};");
        f.addJsImport("{ Mdl" + uc() + " }", mvcPath() + "/Mdl" + uc());
        return true;
    }

}
