package dev.cruding.engine.injection;

import java.util.regex.Pattern;
import dev.cruding.engine.action.ActionWrapper;
import dev.cruding.engine.flow.CtrlFlow;

public class ActionCtrlInjection extends ActionWrapper {

    public void addCtrlImport(CtrlFlow f) {}

    public void addCtrlImplementation(CtrlFlow f) {
        CtrlFlow implementation = new CtrlFlow();
        addCtrlImplementationCore(implementation);
        String implementationContent = implementation.toString();

        f.L("");
        f.L("const ", lnameWithEntity(), "Impl: ActionOperation<Req", uc(), ", Res", uc(), "> = async (",
                parameterName(implementationContent, "requete"), ", ",
                parameterName(implementationContent, "resultat"), ", ",
                parameterName(implementationContent, "thunkAPI"), ") => {");
        f.__(implementationContent);
        f.L("};");
    }

    public void addCtrlDeclaration(CtrlFlow f) {

        f.L____(lnameWithEntity(), ": action<Req", uc(), ", Res", uc(), ">(", lnameWithEntity(), "Impl, Action", page().module.unameLast, ".Uc", uc(), ".", actionKey(), "),");

    }

    public void addCtrlImplementationCore(CtrlFlow f) {

    }

    private String parameterName(String implementationContent, String parameterName) {
        boolean used = Pattern.compile("\\b" + Pattern.quote(parameterName) + "\\b")
                .matcher(implementationContent)
                .find();
        return used ? parameterName : "_" + parameterName;
    }

}
