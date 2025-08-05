package dev.cruding.engine.action.specifique.injection;

import java.util.ArrayList;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlSpecifiqueInjection extends MdlActionInjection {

    public void addMdlImport(MdlFlow f) {
        if (parEntite()) {
            f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        }
        if (parForm()) {
            f.addMdlImport("{ FormInstance }", "antd");
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        if (parForm()) {
            f.addMdlRequestAttribute("form", "FormInstance");
        }
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.addMdlRequestAttribute("id" + entite().ugrandPere, "string");
        }
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }

        if (parId()) {
            f.addMdlRequestAttribute("id" + entite().uname, "string");
        }

        if (parChamp() != null) {
            f.addMdlRequestAttribute(parChamp().lname, "string");
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        if (parEntite()) {
            f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
        }
        if (resultatIn() != null) {
            f.addMdlResultAttribute(resultatIn().lname + entite().uname, resultatIn().jstype);
        }

    }

    public void addMdlStateAttribute(MdlFlow f) {
        if (parEntite()) {
            f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
            f.addMdlSelectorAttribute(entite().lname, entite().uname);
        }
    }


    public void addMdlExtraReducerAffectation(MdlFlow f) {

        if (hasReussi()) {
            ArrayList<Action> siReussiActionList = siReussi();
            if (siReussiActionList.size() > 0) {
                for (Action siReussiAction : siReussiActionList) {
                    if (siReussiAction.mdlActionInjection != null) {
                        siReussiAction.mdlActionInjection.addMdlExtraReducerAffectation(f);
                    }
                    if (siReussiAction.resultatIn != null) {
                        f.L________________("state." + siReussiAction.resultatIn.lname + siReussiAction.entite.uname + " = action.payload." + siReussiAction.resultatIn.lname + siReussiAction.entite.uname + ";");
                    }
                }
            }
        }
    }
}
