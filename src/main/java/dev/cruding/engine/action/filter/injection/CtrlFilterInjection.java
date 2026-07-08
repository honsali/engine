package dev.cruding.engine.action.filter.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class CtrlFilterInjection extends ActionCtrlInjection {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entity().uname, "modele/" + entity().path + "/Service" + entity().uname);
        f.addCtrlImport("{ util }", "waxant");
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        f.L____("await requete.form?.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form?.getFieldsValue());");
        if (paginated()) {
            f.L____("resultat.listePaginee", entity().uname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(dataForm);");
        } else {
            f.L____("resultat.liste", entity().uname, " = await Service", entity().uname, ".", lnameWithoutEntity(), "(dataForm);");
        }
        f.L____("resultat.filtre = dataForm;");
    }
}
