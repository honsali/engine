package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlSpecifiqueInjection extends CtrlActionInjection {

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
        if (parForm()) {
            f.L____("await requete.form.validateFields();");
            f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        }

        f.L____("");
        if (resultatIn() != null) {
            f.__("resultat.", resultatIn().lname, entite().uname, " = ");
        }
        f.__("await Service", entite().uname, ".", lnameSansEntite(), "(");
        boolean withComma = false;
        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
            withComma = true;
        }
        if (parId()) {
            f.__("requete.id", entite().uname, ", ");
            withComma = true;
        }
        if (parChamp() != null) {
            for (Champ c : parChamp()) {
                f.__("requete.", c.lname, ", ");
            }
            withComma = true;
        }
        if (parForm()) {
            if (parEntite()) {
                f.__("{ ...", entite().lname, ", ...dataForm },");
            } else {
                f.__("dataForm, ");
            }
            withComma = true;
        }
        if (withComma) {
            f.removeAfterLastComma();
        }
        f.__(");");
        if (recharger()) {
            f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererParId(");
            if (parIdPere() && entite().havePere) {
                f.__("requete.id" + entite().upere, ", ");
            }
            f.__("requete.id", entite().uname, ");");
        }


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
