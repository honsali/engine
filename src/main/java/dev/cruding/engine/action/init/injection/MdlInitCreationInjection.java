package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.champ.impl.ChampRef;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.MdlActionInjection;

public class MdlInitCreationInjection extends MdlActionInjection {

    protected Champ[] listeChamp = new Champ[0];

    public MdlInitCreationInjection(Champ[] listeChamp) {
        this.listeChamp = listeChamp;
    };

    public void addMdlImport(MdlFlow f) {
        f.addMdlImport("{ I" + entite().uname + " }", "modele/" + entite().path + "/Domaine" + entite().uname);
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef<?>) c).addMdlImport(f);
            }
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {
        f.addMdlResultAttribute(entite().lname, "I" + entite().uname);
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef<?>) c).addMdlResultAttribute(f);
            }
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        f.addMdlStateAttribute(entite().lname, "I" + entite().uname);
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef<?>) c).addMdlStateAttribute(f);
            }
        }
        f.addMdlSelectorAttribute(entite().lname, entite().uname);
    }



    public void addUseSelector(MdlFlow f) {
        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef<?>) c).addUseSelector(f);
            }
        }
        f.L________(entite().lname, ",");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        f.L________________("state.", entite().lname, " = action.payload.", entite().lname, ";");

        for (Champ c : listeChamp) {
            if (c instanceof ChampRef) {
                ((ChampRef<?>) c).addMdlExtraReducer(f);
            }
        }

    }
}
