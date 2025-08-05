package dev.cruding.engine.action.filtrer.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlFiltrerInjection extends CtrlActionInjection {



    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        f.addCtrlImport("{ util }", "waxant");
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("await requete.form.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        if (pagine()) {
            f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".", lnameSansEntite(), "({ ...dataForm, pageCourante: 0 });");
        } else {
            f.L____("resultat.liste", entite().uname, " = await Service", entite().uname, ".", lnameSansEntite(), "(dataForm);");
        }
        f.L____("resultat.filtre = dataForm;");
        f.L("};");
    }
}
