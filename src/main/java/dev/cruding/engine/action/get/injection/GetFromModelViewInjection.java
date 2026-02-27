package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class GetFromModelViewInjection extends ActionViewInjection {

    public String variable;

    public GetFromModelViewInjection(String variable) {
        this.variable = variable;
    }

    public boolean addViewScript(ViewFlow f) {
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
        f.useEffect();
        f.addSelector(variable);
        f.addSelector(lnameWithEntity());
        return true;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSelector(variable);
    }

}
