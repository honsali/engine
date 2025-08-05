package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceSpecifiqueInjection extends ServiceActionInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameSansEntite(), " = async (");
        boolean withComma = false;
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("id" + entite().ugrandPere, ", ");
            withComma = true;
        }
        if (parIdPere() && entite().havePere) {
            f.__("id" + entite().upere, ": string, ");
            withComma = true;
        }
        if (parId()) {
            f.__("id", entite().uname, ": string, ");
            withComma = true;
        }
        if (parChamp() != null) {
            f.__("", parChamp().lname, ", ");
            withComma = true;
        }
        if (parForm()) {
            f.__(entite().lname, ": I", entite().uname, ", ");
            withComma = true;
        }
        if (withComma) {
            f.removeLastComma();
        }
        f.__(") => {");
        f.L____("return (await axios.", lrest(), "(`${resourceUri}");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("/", entite().lgrandPere, "/${id" + entite().ugrandPere, "}");
        }
        if (parIdPere() && entite().havePere) {
            f.__("/", entite().lpere, "/${id" + entite().upere, "}");
        }
        if (parId()) {
            f.__("/${id", entite().uname, "}");
        }
        f.__("`");
        if (parForm() || parEntite()) {
            f.__(", ", entite().lname);
        }
        f.__(")).data");
        if (resultatIn() != null) {
            f.__(".", resultatIn().lname);
        }
        f.__(";");
        f.L("};");

    }

    public void addServiceDeclaration(Flow f) {
        f.L____(lnameSansEntite(), ",");
    }
}
