package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewInitCreationInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.addSelector(lnameAvecEntite());
        f.addSelector("etat" + unameAvecEntite());
        f.addSelector(entite().lname);
        f.totalScript().L____("useEffect(() => {");
        if (attendreSiPret()) {
            f.totalScript().L________("if (pret) {");
            f.totalScript().L____________(lnameAvecEntite(), "();");
            f.totalScript().L________("}");
            f.totalScript().L____("}, [pret]);");
        } else {
            f.totalScript().L________(lnameAvecEntite(), "();");
            f.totalScript().L____("}, []);");
        }
        f.totalScript().L("");
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("if (etat", unameAvecEntite(), ".succes) {");
        f.totalScript().L____________("form.setFieldsValue(", entite().lname, ");");
        f.totalScript().L________("}");
        f.totalScript().L____("}, [etat", unameAvecEntite(), ".succes]);");
        f.useEffect();
        f.addJsImport("use" + uc(), mvcPath() + "/use" + uc());
        return true;
    }
}
