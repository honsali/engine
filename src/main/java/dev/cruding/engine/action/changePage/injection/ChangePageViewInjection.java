package dev.cruding.engine.action.changePage.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ChangePageViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const actionChangementPage = (pageCourante: number) => {");
        f.totalScript().L________(lnameWithEntity(), "({ pageCourante });");
        f.totalScript().L____("};");
        f.addSelector(lnameWithEntity());
        return true;
    }

}
