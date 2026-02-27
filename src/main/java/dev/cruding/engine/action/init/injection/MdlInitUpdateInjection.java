package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.injection.ActionMdlInjection;

public class MdlInitUpdateInjection extends ActionMdlInjection {

    private Action[] actionList = new Action[0];

    public MdlInitUpdateInjection(Action[] actionList) {
        this.actionList = actionList;
    };

    public void addMdlImport(MdlFlow f) {
        for (Action a : actionList) {
            a.mdlActionInjection.addMdlImport(f);
        }
    }

    public void addMdlRequestAttribute(MdlFlow f) {
        for (Action a : actionList) {
            a.mdlActionInjection.addMdlRequestAttribute(f);
        }
    }

    public void addMdlResultAttribute(MdlFlow f) {
        for (Action a : actionList) {
            a.mdlActionInjection.addMdlResultAttribute(f);
        }
    }

    public void addMdlStateAttribute(MdlFlow f) {
        for (Action a : actionList) {
            a.mdlActionInjection.addMdlStateAttribute(f);
        }
    }

    public void addUseSelector(MdlFlow f) {
        for (Action a : actionList) {
            a.mdlActionInjection.addUseSelector(f);
        }
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {
        for (Action a : actionList) {
            a.mdlActionInjection.addMdlExtraReducerAffectation(f);
        }

    }
}
