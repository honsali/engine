package dev.cruding.engine.action.chercher.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlChercherInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        f.addCtrlImport("{ util }", "waxant");
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        f.L____("resultat.listePaginee", entite().uname, " = await Service", entite().uname, ".", lnameSansEntite(), "({ ...dataForm, pageCourante: 0 });");
        f.L____("resultat.filtre = dataForm;");
        f.L("};");
    }
}
