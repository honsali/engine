package dev.cruding.engine.injection;

import java.util.regex.Pattern;
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
        MdlFlow fulfilled = new MdlFlow();
        addMdlExtraReducerAffectation(fulfilled);
        if (resultIn() != null) {
            fulfilled.L________________("state." + resultIn().lname + entity().uname + " = action.payload." + resultIn().lname + entity().uname + ";");
        }
        String fulfilledContent = fulfilled.toString();
        String fulfilledParameters = containsIdentifier(fulfilledContent, "action") ? "state, action" : "state";

        f.L____________(".addCase(Ctrl", uc(), ".", lnameWithEntity(), ".fulfilled, (", fulfilledParameters, ") => {");
        f.__(fulfilledContent);
        f.L________________("state.etat" + unameWithEntity(), " = createEtatSuccess();");
        f.L____________("})");
        f.L____________(".addCase(Ctrl", uc(), ".", lnameWithEntity(), ".pending, (state) => {");
        f.L________________("state.etat" + unameWithEntity(), " = createEtatPending();");
        f.L____________("})");
        f.L____________(".addCase(Ctrl", uc(), ".", lnameWithEntity(), ".rejected, (state) => {");
        f.L________________("state.etat" + unameWithEntity(), " = createEtatError();");
        f.L____________("})");
    }

    public void addMdlExtraReducerAffectation(MdlFlow f) {}

    private boolean containsIdentifier(String content, String identifier) {
        return Pattern.compile("\\b" + Pattern.quote(identifier) + "\\b")
                .matcher(content)
                .find();
    }

    public boolean addMdlReducer(MdlFlow f) {
        return false;
    }
}
