package dev.cruding.engine.element.impl;

import dev.cruding.engine.component.bouton.BoutonActionWorkflow;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class ElementActionWorkflow extends Element {


    private static final String utype = "Action";
    private BoutonActionWorkflow composantAction;

    public ElementActionWorkflow(BoutonActionWorkflow boutonActionWorkflow) {
        super("Action" + boutonActionWorkflow.uname);
        this.composantAction = boutonActionWorkflow;
    }

    public void print(Page page) {
        ViewFlow f = new ViewFlow();

        /* *********************************************************************** */
        f.addJsImport("{ APP_EVENT }", "commun/config/constants.config");
        String typeAction = composantAction.confirmer ? "Confirmer" : "Normale";
        f.addJsImport("{ ActionUc" + typeAction + " }", "waxant");
        f.addJsImport("{ useEventBus }", "waxant");

        f.flushJsImportBloc();
        f.L("");
        f.L("const Action", composantAction.uname, " = (");
        f.__(") => {");
        f.flushInitBloc(page);
        f.L("const { emit } = useEventBus();");
        f.L("");
        f.L____("const ", composantAction.lname, " = () => {");
        f.L________("emit(APP_EVENT.", composantAction.cname, ");");
        f.L____("};");
        f.L("");


        f.L____("return <ActionUc" + typeAction + " nom=\"", composantAction.ltype, "\" action={", composantAction.lname, "} />;");
        f.L("};");
        f.L("");
        f.L("export default Action", composantAction.uname, ";");

        /* *********************************************************************** */

        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/element/" + utype + composantAction.uname + ".tsx");
    }
}
