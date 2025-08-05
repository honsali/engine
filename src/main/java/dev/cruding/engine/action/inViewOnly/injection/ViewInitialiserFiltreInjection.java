package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewInitialiserFiltreInjection extends ViewActionInjection {
    private Action action;

    public ViewInitialiserFiltreInjection(Action action) {
        this.action = action;

    }

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("useEffect(() => {");
        f.totalScript().L________("initialiserFiltre", entite().uname, "();");
        f.totalScript().L____("}, []);");
        f.totalScript().L("");
        f.totalScript().L____("const initialiserFiltre", entite().uname, " = () => {");
        f.totalScript().L________("form.resetFields();");
        f.totalScript().L________("initialiser", action.unameAvecEntite, "();");
        f.totalScript().L____("};");
        f.useEffect();
        f.addSelector("initialiser" + action.unameAvecEntite);
        return true;
    }

}
