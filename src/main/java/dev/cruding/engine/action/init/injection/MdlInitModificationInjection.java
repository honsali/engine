package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.champ.Champ;
import dev.cruding.engine.flow.MdlFlow;

public class MdlInitModificationInjection extends MdlInitCreationInjection {


    public MdlInitModificationInjection(Champ[] listeChamp) {
        super(listeChamp);
    };


    public void addMdlRequestAttribute(MdlFlow f) {
        if (parIdPere() && entite().havePere) {
            f.addMdlRequestAttribute("id" + entite().upere, "string");
        }
        f.addMdlRequestAttribute("id" + entite().uname, "string");
    }
}
