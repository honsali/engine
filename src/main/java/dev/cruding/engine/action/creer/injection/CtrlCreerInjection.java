package dev.cruding.engine.action.creer.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlCreerInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        f.addCtrlImport("{ util }", "waxant");
    }


    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const creer", entite().uname, "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("await requete.form.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");

        f.L____("");
        f.__("const { id } = await Service", entite().uname, ".", lnameSansEntite(), "(");
        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
        }
        f.__("dataForm);");
        f.L____("resultat.id", entite().uname, " = id;");



        if (hasReussi()) {
            for (Action siReussiAction : siReussi()) {
                if (!siReussiAction.inViewOnly) {
                    f.L____("await ", siReussiAction.lnameAvecEntite, "Impl(requete, resultat, thunkAPI);");
                }
            }
        }

        f.L("};");
    }
}
