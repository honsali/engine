package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class InitFilterViewInjection extends ActionViewInjection {
    private Action action;

    public InitFilterViewInjection(Action action) {
        this.action = action;

    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("initialiserFiltre", entity().uname, "();");
        f.totalScript().L____("}, []);");
        f.totalScript().L("");
        f.totalScript().L____("const initialiserFiltre", entity().uname, " = () => {");
        f.totalScript().L________("form.resetFields();");
        f.totalScript().L________("initialiser", action.unameWithEntity, "();");
        f.totalScript().L____("};");
        f.useEffect();
        f.addSelector("initialiser" + action.unameWithEntity);
        return true;
    }

}
