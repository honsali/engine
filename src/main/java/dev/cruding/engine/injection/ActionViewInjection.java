package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.TsLiteral;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;

public class ActionViewInjection extends ActionWrapper {

    public boolean addViewScript(ViewFlow flow) {
        return false;
    }

    public void addFlowScript(ViewFlow flow, int level, String args) {}


    public void addI18n(Flow f) {
        if (!noUi()) {

            Entity e = entity() == null ? Context.getInstance().getEntity(page().entityUname) : entity();
            if (e != null) {

                String nameAction = LabelMapper.getInstance().nameAction(lcoreName(), e);
                String titleConfirmation = LabelMapper.getInstance().titleConfirmation(lcoreName(), e);
                String enteteConfirmation = LabelMapper.getInstance().enteteConfirmation(lcoreName(), e);
                String messageSuccess = LabelMapper.getInstance().messageSuccess(lcoreName(), e);


                String key = "Action" + page().module.unameLast + "." + "Uc" + uc() + "." + actionKey();
                f.L____("[", key, "]", ": ", TsLiteral.string(nameAction), ",");

                if (confirm()) {
                    f.L____("[titreConfirmation(", key, ")]: ", TsLiteral.string(titleConfirmation), ",");
                    f.L____("[enteteConfirmation(", key, ")]: ", TsLiteral.string(enteteConfirmation), ",");
                    f.L____("[messageSuccess(", key, ")]: ", TsLiteral.string(messageSuccess), ",");
                }
            }
        }
    }
}
