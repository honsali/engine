package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ViewInitCreateInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {
        f.addSelector(lnameWithEntity());
        f.addSelector("etat" + unameWithEntity());
        f.addSelector(entity().lname);
        f.totalScript().L____("useEffect(() => {");
        if (waitUntilReady()) {
            f.totalScript().L________("if (pret) {");
            f.totalScript().L____________(lnameWithEntity(), "();");
            f.totalScript().L________("}");
            f.totalScript().L____("}, [pret]);");
        } else {
            f.totalScript().L________(lnameWithEntity(), "();");
            f.totalScript().L____("}, []);");
        }
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("if (etat", unameWithEntity(), ".succes) {");
        f.totalScript().L____________("form.setFieldsValue(", entity().lname, ");");
        f.totalScript().L________("}");
        f.totalScript().L____("}, [etat", unameWithEntity(), ".succes]);");
        f.useEffect();
        f.addJsImport("use" + uc(), mvcPath() + "/use" + uc());
        return true;
    }
}
