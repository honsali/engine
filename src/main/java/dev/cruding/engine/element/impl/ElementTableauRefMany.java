package dev.cruding.engine.element.impl;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.tableau.TableauRefMany;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.gen.Page;

public class ElementTableauRefMany extends Element {

    private static final String utype = "Tableau";
    private TableauRefMany composantTableau;

    public ElementTableauRefMany(Entity entity, TableauRefMany component) {
        super(utype, entity);
        this.composantTableau = component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();
        /* *********************************************************************** */

        for (Action action : Context.getInstance().actionElement(page, this)) {
            action.addViewScript(f);
        }

        for (int i = 0; i < composantTableau.fieldList.length; i++) {
            composantTableau.fieldList[i].addViewScript(f, page.uc, "..");
        }
        String sourceDonnee = StringUtils.uncapitalize(composantTableau.listField.containingEntity);

        f.addSpecificSelector(sourceDonnee, "../Mdl" + page.uc);
        f.addJsImport("{ Bloc, Colonne, Tableau }", "waxant");

        f.flushJsImportBloc();
        f.L("");
        f.L("const ", utype, composantTableau.listField.uname, " = () => {");
        f.flushInitBloc(page);
        f.flushScriptBloc();
        f.L("");
        f.L____("return (");
        f.L________("<Bloc");
        if (composantTableau.largeur > 0) {
            f.__(" largeur=\"", composantTableau.largeur, "px\"");
        }
        f.__(">");
        f.L____________("<Tableau listeDonnee={", sourceDonnee, ".liste", composantTableau.listField.uname, "}");
        if (composantTableau.onRowClickAction != null) {
            f.__(" siClicLigne={", composantTableau.onRowClickAction.actionName(), "}");
        }
        f.__(" texteAucunResultat=\"Aucun ", LabelMapper.getInstance().uAction(composantTableau.listField.uname), "\"");
        f.__(">");

        for (Field c : composantTableau.fieldList) {
            String prefix = c.of == null ? "" : c.of.lname + ".";
            f.L____________("<", c.ui(Element.TABLEAU), " nom=\"", prefix, c.lname, "\"");
            if (c.libelle != null) {
                f.__(" libelle=\"", c.libelle, "\"");
            }
            if (c.width > 0) {
                f.__(" width={", c.width, "}");
            }
            f.__(" />");
        }
        f.L____________("</Tableau>");
        f.L________("</Bloc>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default ", utype, composantTableau.listField.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantTableau.listField.uname + ".tsx");
    }

}
