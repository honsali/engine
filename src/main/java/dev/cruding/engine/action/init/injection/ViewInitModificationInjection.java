package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.action.recuperer.injection.ViewRecupererParChampInjection;
import dev.cruding.engine.flow.ViewFlow;

public class ViewInitModificationInjection extends ViewRecupererParChampInjection {



    public boolean addViewScript(ViewFlow f) {
        super.addViewScript(f);
        f.addSelector("etat" + unameAvecEntite());
        f.addSelector(entite().lname);
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
