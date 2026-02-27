package dev.cruding.engine.action.init.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.CtrlFlow;
import dev.cruding.engine.injection.ActionCtrlInjection;

public class CtrlInitUpdateInjection extends ActionCtrlInjection {

    private Action[] actionList = new Action[0];

    public CtrlInitUpdateInjection(Action[] actionList) {
        this.actionList = actionList;
    };

    public void addCtrlImport(CtrlFlow f) {
        for (Action a : actionList) {
            a.ctrlActionInjection.addCtrlImport(f);
        }
    }

    public void addCtrlImplementationCore(CtrlFlow f) {
        for (Action a : actionList) {
            a.ctrlActionInjection.addCtrlImplementationCore(f);
        }
    }
}
