package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.CtrlActionInjection;

public class CtrlInitModificationInjection extends CtrlActionInjection {

    private Champ[] listeChamp = new Champ[0];

    public CtrlInitModificationInjection(Champ[] listeChamp) {
        this.listeChamp = listeChamp;
    };

    public void addCtrlImport(CtrlFlow f) {
        f.addCtrlImport("Service" + entite().uname, "modele/" + entite().path + "/Service" + entite().uname);
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addCtrlImport(f);
            }
        }
    }

    public void addCtrlImplementation(CtrlFlow f) {
        f.L("");
        f.L("const ", lnameAvecEntite(), "Impl = async (requete: Req", uc(), ", resultat: Res", uc(), ", thunkAPI) => {");
        f.L____("resultat.", entite().lname, " = await Service", entite().uname, ".recupererParId(");
        if (parIdGrandPere() && entite().haveGrandPere) {
            f.__("requete.id" + entite().ugrandPere, ", ");
        }
        if (parIdPere() && entite().havePere) {
            f.__("requete.id" + entite().upere, ", ");
        }
        f.__("requete.id", entite().uname, ");");
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef) c).addCtrlImplementation(f);
            }
        }
        f.L("};");
    }
}
