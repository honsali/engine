package dev.cruding.engine.element.impl;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.entite.Etat;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementEtat extends Element {

    private static final String utype = "Etat";
    private Etat etat;

    public ElementEtat(Entity entity, Etat component) {
        super(utype, entity);
        this.etat = component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        List<Action> actionList = Context.getInstance().actionElement(page, this);
        for (Action action : actionList) {
            action.addViewScript(f);
        }
        f.addJsImport("{ Bloc, Champ, Etat }", "waxant");
        f.flushJsImportBloc();
        f.L("");
        f.L("const ", utype, etat.uname, " = () => {");
        f.flushInitBloc(page);
        f.flushScriptBloc();
        f.L("");
        f.L____("return (");
        f.L________("<Bloc");
        if (etat.largeur > 0) {
            f.__(" largeur=\"", etat.largeur, "px\"");
        }
        f.__(">");
        if (etat.field != null) {
            f.L____________("<Etat modele={", entity.lname, ".", etat.field.lname, "}");
        } else {
            f.L____________("<Etat modele={", entity.lname, "}");
        }
        if (etat.colNumber != 2) {
            f.__(" nombreColonne={", etat.colNumber, "}");
        }
        f.__(">");
        for (Field c : etat.fieldList) {
            f.L________________("<Champ ", c.ui(Element.DETAIL), "=\"", c.lname, "\"");
            if (c.libelle != null) {
                f.__(" libelle=\"", c.libelle, "\"");
            }
            if (c.width > 0) {
                f.__(" width={", c.width, "}");
            }
            if (c.seulDansLaLigne) {
                f.__(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                f.__(" surTouteLaLigne");
            }
            f.__(" />");
        }
        f.L____________("</Etat>");
        f.L________("</Bloc>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default ", utype, etat.uname, ";");
        /* *********************************************************************** */

        String s = f.toString();
        if (etat.communModule) {
            printFile(s, getBasePath() + "/fe/src/" + page.moduleDefinition.path + "/commun/" + utype + etat.uname + ".tsx");
        } else if (etat.communEntite) {
            printFile(s, getBasePath() + "/fe/src/modules/" + page.packge.replace('.', '/') + "/commun/" + utype + etat.uname + ".tsx");
        } else {
            printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + etat.uname + ".tsx");
        }
    }
}
