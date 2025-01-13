package dev.cruding.engine.action.changerPage.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewChangerPageChercherInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const actionChangementPage = (pageCourante: number) => {");
        f.totalScript().L________("execute(Ctrl", uc(), ".", lnameAvecEntite(), ", { pageCourante });");
        f.totalScript().L____("};");
        f.useExecute();
        f.addJsImport("Ctrl" + uc(), mvcPath() + "/Ctrl" + uc());
        return true;
    }

}
