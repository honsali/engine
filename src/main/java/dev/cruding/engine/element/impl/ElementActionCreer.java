package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.bouton.BoutonCreer;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;

public class ElementActionCreer extends Element {

    private String utype = "Action";
    private BoutonCreer composantAction;

    public ElementActionCreer(Entity entity, BoutonCreer boutonCreer) {
        super("Action" + boutonCreer.uname, entity);
        this.composantAction = boutonCreer;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */

        Page targetPage = Context.getInstance().getPage("PageConsulter" + entity.uname);

        f.useEffect();
        f.useGoToPage();
        f.useExecute("execute, success, rid");
        f.addSpecificSelector("resultat", page.uc + "Resultat", "../Mdl" + page.uc);
        f.addJsImport("{ ActionUcCreer }", "waxant");
        if (targetPage != null) {
            f.addJsImport("{ " + targetPage.name + " }", targetPage.moduleDefinition.listePage(page.path + "/element/"));
        }
        f.addJsImport("Ctrl" + page.uc, "../Ctrl" + page.uc);
        if (composantAction.siReussiAction != null) {
            composantAction.siReussiAction.addViewImport(f);
        }
        f.flushJsImportBloc();
        f.L("");
        f.L("const Action", composantAction.uname, " = ({ form }) => {");
        f.flushInitBloc(page);
        f.L("");
        f.L____("const ", composantAction.lname, " = () => {");
        f.L____________("execute(Ctrl", page.uc, ".", composantAction.lname, ", { form });");
        f.L____("};");
        f.L("");
        f.L____("useEffect(() => {");
        if (composantAction.siReussiAction != null) {
            f.L________("if (success) {");
            composantAction.siReussiAction.addScript(f, 3);
            f.L____________("goToPage(PageConsulter", entity.uname, ", resultat);");
            f.L________("}");
        } else {
            f.L________("success &&  goToPage(PageConsulter", entity.uname, ", resultat);");
        }
        f.L____("}, [success]);");
        f.L("");
        f.L____("return <ActionUcCreer ");
        if (!composantAction.lname.equals("creer" + entity.uname)) {
            f.__("nom=\"", composantAction.lname, "\" ");
        }
        f.__("action={", composantAction.lname, "} rid={rid} />;");
        f.L("};");
        f.L("");
        f.L("export default Action", composantAction.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantAction.uname + ".tsx");
    }
}
