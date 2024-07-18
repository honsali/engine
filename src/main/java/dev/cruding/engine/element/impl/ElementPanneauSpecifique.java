package dev.cruding.engine.element.impl;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.PanneauSpecifique;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementPanneauSpecifique extends Element {

    private static final String utype = "Panneau";
    private PanneauSpecifique composantPanneau;

    public ElementPanneauSpecifique(PanneauSpecifique component) {
        super(utype);
        this.composantPanneau = component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        List<Action> actionList = Context.getInstance().actionElement(page, this);
        for (Action action : actionList) {
            action.addViewScript(f);
        }
        f.L("const ", utype, composantPanneau.uname, " = () => {");
        f.L("");
        f.L____("return (");
        f.L________("<div></div>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default ", utype, composantPanneau.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        if (composantPanneau.communModule) {
            printFile(s, getBasePath() + "/fe/src/" + page.moduleDefinition.path + "/commun/" + utype + composantPanneau.uname + ".tsx");
        } else {
            printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + this.composantPanneau.uname + ".tsx");
        }
    }
}
