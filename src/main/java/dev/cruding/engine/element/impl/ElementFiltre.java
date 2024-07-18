package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.entite.Filtre;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class ElementFiltre extends Element {

    private static final String utype = "Filtre";
    private Filtre filter;

    public ElementFiltre(Entity entity, Component component) {
        super(utype, entity);
        this.filter = (Filtre) component;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        StringBuilder fieldImportList = processListeField(filter, Element.FORM);
        f.useEffect();
        f.useForm();
        f.useExecute();
        f.addJsImport("{ BlocActionDroit }", "waxant");
        f.addJsImport("{ Bloc }", "waxant");
        f.addJsImport("{ ActionUcInitialiserFiltre }", "waxant");
        f.addJsImport("{ ActionUcAppliquerFiltre }", "waxant");
        f.addJsImport("{ " + fieldImportList + " }", "waxant");
        f.addJsImport("Ctrl" + page.uc, "../Ctrl" + page.uc);
        f.flushJsImportBloc();
        f.L("");
        f.L("const ", utype, entity.uname, " = () => {");
        f.flushInitBloc(page);
        f.L("");
        f.L____("useEffect(() => {");
        f.L________("    initialiserFiltre();");
        f.L____("}, []);");
        f.L("");
        f.L____("const initialiserFiltre = () => {");
        f.L________("form.resetFields();");
        f.L________("appliquerFiltre();");
        f.L____("};");
        f.L("");
        f.L____("const appliquerFiltre = () => {");
        f.L________("execute(Ctrl", page.uc, ".chercher", entity.uname, ", { form });");
        f.L____("};");
        f.L("");
        f.L____("return (");
        f.L________("<Bloc>");

        f.L____________("<Formulaire form={form}");
        if (filter.colNumber > 0) {
            f.__(" nombreColonne={", filter.colNumber, "}");
        }
        f.__(">");

        for (Field c : filter.fieldList) {
            String prefix = c.of == null ? "" : c.of.lname + ".";
            f.L________________("<", c.ui(Element.FORM), " nom=\"", prefix, c.lname, "\"");
            if (c.libelle != null) {
                f.__(" libelle=\"", c.libelle, "\"");
            }
            if (c.width > 0) {
                f.__(" width={", c.width, "}");
            }
            f.__(c.getExtension());
            f.__(" />");
        }

        f.L____________("</Formulaire>");
        f.L____________("<BlocActionDroit>");
        f.L________________("<ActionUcInitialiserFiltre action={initialiserFiltre} />");
        f.L________________("<ActionUcAppliquerFiltre action={appliquerFiltre} />");
        f.L____________("</BlocActionDroit>");
        f.L________("</Bloc>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default Filtre", entity.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();

        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + entity.uname + ".tsx");
    }
}
