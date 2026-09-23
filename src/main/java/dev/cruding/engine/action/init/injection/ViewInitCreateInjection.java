package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class ViewInitCreateInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {
        f.useInitAction(action);
        f.addSelector("etat" + unameWithEntity());
        f.addSelector(entity().lname);
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("if (etat", unameWithEntity(), ".succes && ", entity().lname, ") {");
        f.totalScript().L____________("form.setFieldsValue(", entity().lname, ");");
        f.totalScript().L________("}");
        f.totalScript().L____("}, [etat", unameWithEntity(), ".succes, ", entity().lname, ", form]);");
        f.useEffect();
        return true;
    }
}
