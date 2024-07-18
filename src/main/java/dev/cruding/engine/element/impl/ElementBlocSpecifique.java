package dev.cruding.engine.element.impl;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.entite.BlocSpecifique;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementBlocSpecifique extends Element {

    private static final String utype = "Bloc";
    private BlocSpecifique composantBloc;

    public ElementBlocSpecifique(Component component) {
        super(utype);
        this.composantBloc = (BlocSpecifique) component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        List<Action> actionList = Context.getInstance().actionElement(page, this);
        for (Action action : actionList) {
            action.addViewScript(f);
        }
        f.L("const ", utype, composantBloc.uname, " = () => {");
        f.L("");
        f.L____("return (");
        f.L________("<div></div>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default ", utype, composantBloc.uname, ";");
        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantBloc.uname + ".tsx");
    }
}
