package dev.cruding.engine.injection;

import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.flow.MdlFlow;

public class MdlActionInjection extends ActionWrapper {

    public void addMdlImport(MdlFlow f) {}

    public void addMdlRequestAttribute(MdlFlow f) {}

    public void addMdlResultAttribute(MdlFlow f) {}

    public void addMdlStateAttribute(MdlFlow f) {}

    public void addMdlSelector(MdlFlow f) {}

    public void addUseSelector(MdlFlow f) {}

    public void addMdlExtraReducer(MdlFlow f) {
        f.L____________(".addCase(Ctrl", uc(), ".", lnameAvecEntite(), ".fulfilled, (state, action) => {");
        addMdlExtraReducerAffectation(f);
        if (resultatIn() != null) {
            f.L________________("state." + resultatIn().lname + entite().uname + " = action.payload." + resultatIn().lname + entite().uname + ";");
        }
        f.L________________("state.etat" + unameAvecEntite(), " = createEtatSuccess();");
        f.L____________("})");
        f.L____________(".addCase(Ctrl", uc(), ".", lnameAvecEntite(), ".pending, (state, action) => {");
        f.L________________("state.etat" + unameAvecEntite(), " = createEtatPending();");
        f.L____________("})");
        f.L____________(".addCase(Ctrl", uc(), ".", lnameAvecEntite(), ".rejected, (state, action) => {");
        f.L________________("state.etat" + unameAvecEntite(), " = createEtatError();");
        f.L____________("})");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {}

    public boolean addMdlReducer(MdlFlow f) {
        return false;
    }
}
