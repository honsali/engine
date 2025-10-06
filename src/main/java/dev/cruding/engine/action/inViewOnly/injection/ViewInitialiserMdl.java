package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewInitialiserMdl extends ViewActionInjection {

    public boolean addViewScript(ViewFlow f) {
        f.totalScript().L____("const initialiser", entite().uname, " = () => {");
        if (parForm()) {
            f.totalScript().L________("form.resetFields();");
        }
        f.totalScript().L________("dispatch(Mdl", uc(), ".initialiser());");
        f.totalScript().L____("};");
        f.useDispatch();

        f.addJsImport("{ Mdl" + uc() + " }", mvcPath() + "/Mdl" + uc());
        return true;
    }

}
