package dev.cruding.engine.element.impl;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.entite.Formulaire;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementFormulaire extends Element {

    private static final String utype = "Formulaire";
    private Formulaire composantFormulaire;

    public ElementFormulaire(Entity entity, Component component) {
        super(utype, entity);
        this.composantFormulaire = (Formulaire) component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        List<Action> actionList = Context.getInstance().actionElement(page, this);
        for (Action action : actionList) {
            action.addViewScript(f);
        }

        for (Field c : composantFormulaire.fieldList) {
            if (c.siChange) {
                f.addJsImport("{ useOnChange }", "waxant");
            }
            c.addViewScript(f, page.uc, "..");
        }

        StringBuilder fieldImportList = processListeField(composantFormulaire, Element.FORM);
        f.addJsImport("{ Bloc, Formulaire }", "waxant");
        f.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
        f.flushJsImportBloc();
        f.L("");
        f.L("const ", utype, composantFormulaire.uname, " = ({ form }) => {");
        f.flushInitBloc(page);

        for (Field c : composantFormulaire.fieldList) {
            if (c.readOnlyIf != null) {
                f.L____("const ", c.readOnlyIf, ";");
            }
            if (c.avecVariable) {
                f.L____("const ", c.lname, " = Form.useWatch('", c.lname, "', form);");
            }
        }
        f.L("");

        f.flushScriptBloc();

        for (Field c : composantFormulaire.fieldList) {
            if (c.siChange) {
                f.L____("useOnChange('", c.lname, "', form, (valeur) => {");
                f.L____("});");
                f.L("");
            }
        }

        f.L____("return (");
        f.L________("<Bloc");
        if (composantFormulaire.largeur > 0) {
            f.__(" largeur=\"", composantFormulaire.largeur, "px\"");
        }
        f.__(">");
        f.L____________("<Formulaire form={form}");
        if (composantFormulaire.colNumber != 1) {
            f.__(" nombreColonne={", composantFormulaire.colNumber, "}");
        }
        f.__(">");
        for (Field c : composantFormulaire.fieldList) {
            f.L________________("<", c.ui(Element.FORM), " nom=\"", c.lname, "\"");
            if (c.libelle != null) {
                f.__(" libelle=\"", c.libelle, "\"");
            }
            if (c.width > 0) {
                f.__(" width={", c.width, "}");
            }
            f.__(c.getExtension());
            if (c.required) {
                f.__(" requis=\"true\"");
            }
            if (c.readOnlyIf != null) {
                f.__(" disabled={", c.readOnlyIf, "}");
            } else if (c.readOnly) {
                f.__(" disabled");
            }
            if (c.invisibleSi != null) {
                f.__(" invisible={", c.invisibleSi, "}");
            }
            if (c.seulDansLaLigne) {
                f.__(" seulDansLaLigne");
            }
            if (c.surTouteLaLigne) {
                f.__(" surTouteLaLigne");
            }
            f.__(" />");
        }
        f.L____________("</Formulaire>");
        f.L________("</Bloc>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default ", utype, composantFormulaire.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantFormulaire.uname + ".tsx");
    }
}
