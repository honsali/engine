package dev.cruding.engine.action.supprimer.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlSuppressionInjection extends CtrlActionInjection {

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
    }


    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("await Service", entite().uname, ".", lnameSansEntite(), "(requete.id", entite().uname, ");");


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
