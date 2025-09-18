package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.injection.ServiceActionInjection;

public class ServiceSpecifiqueInjection extends ServiceActionInjection {


    public void addServiceImplementation(Flow f) {
        f.L("");
        f.L("const ", lnameSansEntite(), " = async (");
        boolean withComma = false;
        if (parIdPere() && entite().havePere) {
            f.__("id" + entite().upere, ": string, ");
            withComma = true;
        }
        if (parId()) {
            f.__("id", entite().uname, ": string, ");
            withComma = true;
        }
        if (parChamp() != null) {
            for (Champ c : parChamp()) {
                f.__("", c.lname, ", ");
            }
            withComma = true;
        }
        if (parForm()) {
            f.__(entite().lname, ": I", entite().uname, ", ");
            withComma = true;
        }
        if (withComma) {
            f.removeAfterLastComma();
        }
        f.__(") => {");
        f.L____("const { data } = await axios.", lrest(), "(`${API_URL}/", entite().lname, "/");
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
        f.__(")");
        f.__(";");

        f.L____("return data");
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
