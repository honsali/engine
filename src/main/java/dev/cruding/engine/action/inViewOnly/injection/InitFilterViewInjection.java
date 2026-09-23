package dev.cruding.engine.action.inViewOnly.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.injection.ActionViewInjection;

public class InitFilterViewInjection extends ActionViewInjection {
    private Action action;

    public InitFilterViewInjection(Action action) {
        this.action = action;

    }

    public boolean addViewScript(ViewFlow f) {
        Action initialization = Context.getInstance().actionPage(page()).stream()
                .filter(candidate -> candidate.lnameWithEntity.equals("initialiser" + action.unameWithEntity))
                .findFirst().orElseThrow();
        f.useInitAction(initialization);
        f.totalScript().L____("const initialiserFiltre", entity().uname, " = () => {");
        f.totalScript().L________("form.resetFields();");
        f.totalScript().L________("initialiser", action.unameWithEntity, "();");
        f.totalScript().L____("};");
        f.addSelector("initialiser" + action.unameWithEntity);
        return true;
    }

}
