package dev.cruding.engine.action;

public abstract class ActionInViewOnly extends Action {

    public ActionInViewOnly(String actionType) {
        super(actionType);
        inViewOnly = true;
    }

}