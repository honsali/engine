package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.flow.MCFlow;

public class ActionChangerSelection extends Action {

    public void actionnable(Actionnable actionnable) {
        this.actionnable = actionnable;
        this.actionnable.lname(this.actionnable.lcoreName);
    }


    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute(lname(), "I" + entite().uname + "[]");
    }



    public boolean addMdlReducer(MCFlow f) {
        f.L________("modifier", uname(), "(state, action: PayloadAction<I", entite().uname, "[]>) {");
        f.L____________("state.", lname(), " = action.payload;");
        f.L________("},");
        return true;
    }


    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const select", uname(), " = createSelector([selectMdl", uc(), "], (state: ", uc(), "Type) => state.", lname(), ");");
    }
}
