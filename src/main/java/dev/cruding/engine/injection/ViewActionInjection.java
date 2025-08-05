package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.LabelMapper;

public class ViewActionInjection extends ActionWrapper {

    public boolean addViewScript(ViewFlow flow) {
        return false;
    }

    public void addFlowScript(ViewFlow flow, int level, String args) {}


    public void addI18n(Flow f) {
        if (!noUi()) {

            Entite e = entite() == null ? Contexte.getInstance().getEntite(page().entiteUname) : entite();
            if (e != null) {

                String nomAction = LabelMapper.getInstance().nomAction(lcoreName(), e);
                String titreConfirmation = LabelMapper.getInstance().titreConfirmation(lcoreName(), e);
                String enteteConfirmation = LabelMapper.getInstance().enteteConfirmation(lcoreName(), e);
                String messageSuccess = LabelMapper.getInstance().messageSuccess(lcoreName(), e);


                String key = "Action" + page().module.unameLast + "." + "Uc" + uc() + "." + actionKey();
                f.L____("[", key, "]", ": '", nomAction, "',");

                if (confirmer()) {
                    f.L____("[titreConfirmation(", key, ")]: '", titreConfirmation, "',");
                    f.L____("[enteteConfirmation(", key, ")]: '", enteteConfirmation, "',");
                    f.L____("[messageSuccess(", key, ")]: '", messageSuccess, "',");
                }
            }
        }
    }
}
