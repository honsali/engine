package dev.cruding.engine.action;

import dev.cruding.engine.entity.Entity;

public class ActionAvecTable extends Action {

    public String paginee = "";
    public String orderBy;

    public ActionAvecTable(String actionType) {
        super(actionType);
    }

    public void setPaginee(boolean paginee) {
        this.paginee = paginee ? "Paginee" : "";
    }


    public ActionAvecTable orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    public Action entity(Entity entity) {
        this.orderBy = this.orderBy == null ? entity.uid : this.orderBy;
        return super.entity(entity);
    }

}
