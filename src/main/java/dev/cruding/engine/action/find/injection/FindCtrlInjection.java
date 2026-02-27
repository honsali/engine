package dev.cruding.engine.action.find.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class FindCtrlInjection extends ActionCtrlInjection {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
        f.addCtrlImport("{ util }", "waxant");
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("await requete.form.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        f.L____("resultat.", entity().lname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(dataForm);");
    }
}
