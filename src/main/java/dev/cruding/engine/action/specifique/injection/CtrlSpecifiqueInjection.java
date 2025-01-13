package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlSpecifiqueInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        if (byForm()) {
            f.addCtrlImport("{ util }", "waxant");
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        if (byEntite()) {
            f.L____("const { mdl", uc(), " } = thunkAPI.getState() as any;");
            f.L____("const ", entite().lname, " = mdl", uc(), ".", entite().lname, ";");
        }
        if (byForm()) {
            f.L____("await requete.form.validateFields();");
            f.L____("const dataForm = util.removeNonSerialisable(requete.form.getFieldsValue());");
        }

        f.L____("");
        if (resultatInId()) {
            f.__("resultat.id", entite().uname, " = ");
        }
        f.__("await Service", entite().uname, ".", lnameSansEntite(), "(");
        boolean withComma = false;
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
            withComma = true;
        }
        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
            withComma = true;
        }
        if (byId()) {
            f.__("requete.id", entite().uname, ", ");
            withComma = true;
        }
        if (byChamp() != null) {
            f.__("requete.", byChamp().lname, ", ");
            withComma = true;
        }
        if (child() != null) {
            f.__("requete.liste" + child().uname, ", ");
            withComma = true;
        }
        if (byForm()) {
            if (byEntite()) {
                f.__("{ ...", entite().lname, ", ...dataForm },");
            } else {
                f.__("dataForm, ");
            }
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(");");
        if (recharger()) {
            f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererParId(");
            if (parIdGrandPere() && entite().haveGrandPere) {
                f.__("requete.id" + entite().ugrandPere, ", ");
            }
            if (parIdPere() && entite().havePere) {
                f.__("requete.id" + entite().upere, ", ");
            }
            f.__("requete.id", entite().uname, ");");
        }
        f.L("};");
    }
}
