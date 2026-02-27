package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class InitModelViewInjection extends ActionViewInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const initialiser", entity().uname, " = () => {");
        if (byForm()) {
            f.totalScript().L________("form.resetFields();");
        }
        f.totalScript().L________("dispatch(Mdl", uc(), ".initialiser());");
        f.totalScript().L____("};");
        f.useDispatch();

        f.addJsImport("{ Mdl" + uc() + " }", mvcPath() + "/Mdl" + uc());
        return true;
    }

}
