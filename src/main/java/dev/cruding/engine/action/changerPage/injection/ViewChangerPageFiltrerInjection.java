package dev.cruding.engine.action.changerPage.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewChangerPageFiltrerInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const actionChangementPage = (pageCourante: number) => {");
        f.totalScript().L________(lnameAvecEntite(), "({ pageCourante });");
        f.totalScript().L____("};");
        f.addSelector(lnameAvecEntite());
        return true;
    }

}
