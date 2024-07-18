package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.bouton.BoutonActionLocale;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementActionLocale extends Element {

    private static final String listeActionType = "#Creer#Modifier#Supprimer#Confirmer#Annuler#RetourList#Ajouter#Enregistrer#Valider#Imprimer#Deverrouiller#Verrouiller";

    private static final String utype = "Action";
    private BoutonActionLocale composantAction;

    public ElementActionLocale(BoutonActionLocale boutonActionSpecifique) {
        super("Action" + boutonActionSpecifique.uname);
        this.composantAction = boutonActionSpecifique;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();
        /* *********************************************************************** */
        boolean actionTypeConnu = listeActionType.indexOf(composantAction.utype) > -1;
        if (composantAction.siReussiGoToPage != null) {
            f.useGoToPage();
            Page targetPage = Context.getInstance().getPage(composantAction.siReussiGoToPage);
            f.addJsImport("{ " + targetPage.name + " }", targetPage.moduleDefinition.listePage(page.path + "/element"));
        }
        if (composantAction.icone != null) {
            f.addJsImport("{ " + composantAction.icone + " }", "@ant-design/icons");
        }
        f.addJsImport("{ ActionUc" + (actionTypeConnu ? composantAction.utype : this.composantAction.confirmer ? "Confirmer" : "Normale") + " }", "waxant");
        if (this.composantAction.confirmer) {
            f.useEffect();
            f.useSelector();
            f.useExecute("execute, success, rid");
            f.addSpecificSelector("resultat", page.uc + "Resultat", "../Mdl" + page.uc);
        } else {
            f.useExecute();
        }
        f.addJsImport("Ctrl" + page.uc, "../Ctrl" + page.uc);
        f.flushJsImportBloc();
        f.L("");
        f.L("const Action", composantAction.uname, " = (");
        if (this.composantAction.byForm) {
            f.__("{ form }");
        }
        f.__(") => {");
        f.flushInitBloc(page);
        f.L("");
        f.L____("const ", composantAction.lname, " = () => {");
        if (this.composantAction.byForm) {
            f.__(", { form }");
        }
        f.__(");");
        f.L____("};");
        f.L("");
        if (this.composantAction.confirmer) {
            f.L____("useEffect(() => {");

            if (composantAction.siReussiGoToPage != null) {
                f.L________("success && goToPage(", composantAction.siReussiGoToPage, ");");
            } else {
                f.L________("success && console.log('@TODO');");
            }
            f.L____("}, [success]);");
            f.L("");

        }
        String nom = actionTypeConnu ? composantAction.utype : this.composantAction.confirmer ? ("Confirmer nom=\"" + composantAction.ltype + "\"") : ("Normale nom=\"" + composantAction.ltype + "\"");
        f.L____("return <ActionUc", nom, " action={", composantAction.lname, "}");

        if (composantAction.icone != null) {
            f.__(" icone={<", composantAction.icone, " />}");
        }
        if (this.composantAction.confirmer) {
            f.__(" rid={rid}");
        }
        f.__(" />;");
        f.L("};");
        f.L("");
        f.L("export default Action", composantAction.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantAction.uname + ".tsx");
    }
}
