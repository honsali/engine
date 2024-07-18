package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.bouton.BoutonActionDialogue;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.field.Field;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementActionDialogue extends Element {

    private static final String utype = "Action";
    private BoutonActionDialogue composantAction;

    public ElementActionDialogue(BoutonActionDialogue boutonActionDialogue) {
        super("Action" + boutonActionDialogue.uname);
        this.composantAction = boutonActionDialogue;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();
        f.useForm("actionForm");
        /* *********************************************************************** */
        if (composantAction.siReussiGoToPage != null) {
            f.useGoToPage();
            Page targetPage = Context.getInstance().getPage(composantAction.siReussiGoToPage);
            f.addJsImport("{ " + targetPage.name + " }", targetPage.moduleDefinition.listePage(page.path + "/element"));
        }
        if (composantAction.fieldList != null) {
            StringBuilder fieldImportList = processListeField(composantAction, Element.FORM);
            f.addJsImport(" { " + fieldImportList.toString() + " } ", "waxant");
        }
        if (composantAction.icone != null) {
            f.addJsImport("{ " + composantAction.icone + " }", "@ant-design/icons");
        }
        f.addJsImport("{ ActionUcDialogue }", "waxant");
        f.addSpecificSelector("resultat", page.uc + "Resultat", "../Mdl" + page.uc);
        if (composantAction.actionCtrl) {
            f.addJsImport("Ctrl" + page.uc, "../Ctrl" + page.uc);
        }
        f.flushJsImportBloc();
        f.L("");
        f.L("const Action", composantAction.uname, " = () => {");
        f.flushInitBloc(page);
        f.L("");

        if (composantAction.siReussiGoToPage != null) {
            f.L____("const siSucces = () => {");
            f.L________("goToPage(", composantAction.siReussiGoToPage, ");");
            f.L____("};");
            f.L("");
        }

        f.L____("const siAnnuler = () => {");
        f.L________("actionForm.resetFields();");
        f.L____("};");
        f.L("");
        if (!composantAction.actionCtrl) {
            f.L____("const action = () => {");
            f.L____("};");
            f.L("");
        }

        f.L____("return (");
        f.L____________("<ActionUcDialogue");
        f.L________________("nom=\"", composantAction.actionType, "\" //");
        if (composantAction.icone != null) {
            f.L________________("icone={<", composantAction.icone, " />}");
        }
        if (composantAction.actionCtrl) {
            f.L________________("actionCtrl={Ctrl", page.uc, ".", composantAction.lname, "}");
        } else {
            f.L________________("action={action}");
        }
        f.L________________("args={{ form: actionForm }}");
        f.L________________("resultat={resultat}");
        if (composantAction.siReussiGoToPage != null) {
            f.L________________("siSucces={siSucces}");
        }
        f.L________________("siAnnuler={siAnnuler}");
        f.L____________(">");
        f.L________________("<Formulaire form={actionForm}>");
        if (composantAction.fieldList != null) {
            for (Field c : composantAction.fieldList) {
                f.L____________________("<", c.ui(Element.FORM), " nom=\"", c.lname, "\"");
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
                if (c.siChange) {
                    f.__(" siChange={changer", c.uname, "}");
                }
                if (c.seulDansLaLigne) {
                    f.__(" seulDansLaLigne");
                }
                if (c.surTouteLaLigne) {
                    f.__(" surTouteLaLigne");
                }
                f.__(" />");
            }
        } else {
            f.L____________________("'@TODO'");
        }
        f.L________________("</Formulaire>");
        f.L____________("</ActionUcDialogue>");
        f.L____(");");
        f.L("};");
        f.L("");
        f.L("export default Action", composantAction.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantAction.uname + ".tsx");
    }
}
