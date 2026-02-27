package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.flow.MdlFlow;

public class ActionMdlInjection extends ActionWrapper {

    public void addMdlImport(MdlFlow f) {}

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

    public void addMdlStateAttribute(MdlFlow f) {}

    public void addMdlSelector(MdlFlow f) {}

    public void addUseSelector(MdlFlow f) {}

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lnameWithEntity(), ".fulfilled, (state, action) => {");
        addMdlExtraReducerAffectation(f);
        if (resultIn() != null) {
            f.L________________("state." + resultIn().lname + entity().uname + " = action.payload." + resultIn().lname + entity().uname + ";");
        }
        f.L________________("state.etat" + unameWithEntity(), " = createEtatSuccess();");
        f.L____________("})");
        f.L____________(".addCase(Ctrl", uc(), ".", lnameWithEntity(), ".pending, (state, action) => {");
        f.L________________("state.etat" + unameWithEntity(), " = createEtatPending();");
        f.L____________("})");
        f.L____________(".addCase(Ctrl", uc(), ".", lnameWithEntity(), ".rejected, (state, action) => {");
        f.L________________("state.etat" + unameWithEntity(), " = createEtatError();");
        f.L____________("})");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {}

    public boolean addMdlReducer(MdlFlow f) {
        return false;
    }
}
