package dev.cruding.engine.action.get.injection;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.injection.ActionViewInjection;

public class GetByFieldViewInjection extends ActionViewInjection {



    public boolean addViewScript(ViewFlow f) {
        String lnameField = byField()[0].lname;
        if (flow()) {
            return false;
        } else if (inInit()) {
            f.totalScript().L____("useEffect(() => {");
            f.totalScript().L________(lnameWithEntity(), "();");
            f.totalScript().L____("}, [", lnameField.equals("id") ? "" : lnameField, "]);");
            f.useEffect();
            f.addSelector(lnameWithEntity());
        } else if (byForm()) {
            f.totalScript().L____("const ", lnameWithEntity(), " = () => {");
            f.totalScript().L________(lnameWithEntity(), "({ ", lnameField, ": form.getFieldValue('", lnameField, "') });");
            f.totalScript().L____("};");
            f.useForm();
        } else {
            f.totalScript().L____("const ", lnameWithEntity(), " = (", lnameField, ") => {");
            f.totalScript().L________(lnameWithEntity(), "({ ", lnameField, " });");
            f.totalScript().L____("};");
            f.useEffect();
        }
        return true;
    }

    public void addViewSelector(ViewFlow f) {
        f.addSelector(entity().uname);
    }

}
