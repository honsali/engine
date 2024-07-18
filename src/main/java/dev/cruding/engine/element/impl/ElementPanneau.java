package dev.cruding.engine.element.impl;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.entite.Panneau;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementPanneau extends Element {

    private static final String utype = "Panneau";
    private Panneau composantPanneau;

    public ElementPanneau(Entity entity, Panneau component) {
        super(utype, entity);
        this.composantPanneau = component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        for (Component component : this.composantPanneau.componentList) {
            component.addContent(null, f, 1);
        }
        List<Action> actionList = Context.getInstance().actionElement(page, this);
        for (Action action : actionList) {
            action.addViewScript(f);
        }
        f.flushJsImportBloc();
        f.L("");
        f.L("const ", utype, composantPanneau.uname, " = () => {");
        f.flushInitBloc(page);
        f.flushScriptBloc();
        f.L("");
        f.L____("return (");
        f.flushUiBloc();
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
