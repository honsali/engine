package dev.cruding.engine.action.maj.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlMajInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        if (parForm()) {
            f.addCtrlImport("{ util }", "waxant");
        }
    }


    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        if (parEntite()) {
            f.L____("const { mdl", uc(), " } = thunkAPI.getState() as any;");
            f.L____("const ", entite().lname, " = mdl", uc(), ".", entite().lname, ";");
        }
        f.L____("await requete.form.validateFields();");
        f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        f.L____("await Service", entite().uname, ".", lnameSansEntite(), "(");
        f.__("dataForm");
        f.__(");");


        if (hasReussi()) {
            for (Action siReussiAction : siReussi()) {
                // if (siReussiAction.type.equals(ActionType.FLOW)) {
                if (!siReussiAction.inViewOnly) {
                    f.L____("await ", siReussiAction.lnameAvecEntite, "Impl(requete, resultat, thunkAPI);");
                }
                // }
            }
        }

        f.L("};");
    }
}
