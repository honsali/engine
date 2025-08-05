package dev.cruding.engine.printer.impl.page;

import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.helper.Attribute;
import dev.cruding.engine.printer.Printer;

public class FeHookPrinter extends Printer {

    public void print(Page page) {
        MdlFlow f = new MdlFlow();
        List<Action> actionList = Contexte.getInstance().actionPage(page);
        /* *********************************************************************** */
        for (Action action : actionList) {
            action.mdlActionInjection.addMdlStateAttribute(f);
            if (!action.inViewOnly && !action.isVide) {
                f.addMdlStateAttribute("etat" + action.unameAvecEntite, "createEtatInit()");
                f.addMdlSelectorAttribute("etat" + action.unameAvecEntite, "Etat" + action.unameAvecEntite);
            }
            if (action.resultatIn != null) {
                f.addMdlStateAttribute(action.resultatIn.lname + action.entite.uname, action.resultatIn.jstype);
                f.addMdlSelectorAttribute(action.resultatIn.lname + action.entite.uname, action.resultatIn.uname + action.entite.uname);
            }

        }
        f.addMdlImport("{ useSelector }", "react-redux");
        f.addMdlImport("{ useParams }", "react-router");
        f.addMdlImport("{ useAppDispatch }", "waxant");

        for (Attribute att : f.mdlSelectorAttributeSet) {
            f.addMdlImport("{ select" + att.name + " }", "./Mdl" + page.uc);
        }
        f.addMdlImport("Ctrl" + page.uc, "./Ctrl" + page.uc);
        f.addMdlImport("{ Mdl" + page.uc + ", Req" + page.uc + " }", "./Mdl" + page.uc);
        /* *********************************************************************** */
        f.flushMdlImportBloc();

        f.L("");
        f.L("const use", page.uc, " = () => {");


        f.L("");
        f.L____("const dispatch = useAppDispatch();");
        f.L____("const params = useParams();");
        f.L("");

        for (Attribute att : f.mdlSelectorAttributeSet) {
            f.L____("const ", att.type, " = useSelector(select", att.name, ");");
        }


        f.L("");
        f.L____("const createAction = (action: any) => (req?: Req", page.uc, ") => dispatch(action({ ...req, ...params }));");
        f.L("");
        f.L____("return {");
        f.L________("// Actions");

        for (Action action : actionList) {
            if (!action.inViewOnly && !action.isVide) {
                f.L________(action.lnameAvecEntite, ": createAction(Ctrl", page.uc, ".", action.lnameAvecEntite, "),");
            }
        }
        for (Action action : actionList) {
            if (!action.inViewOnly && !action.isVide) {
                f.L________("resetEtat", action.unameAvecEntite, ": () => dispatch(Mdl", page.uc, ".resetEtat", action.unameAvecEntite, "()),");
            }
        }
        f.L("");
        f.L________("// State");
        for (Attribute att : f.mdlSelectorAttributeSet) {
            f.L________(att.type, ",");
        }
        f.L____("};");
        f.L("};");
        f.L("");
        f.L("export default use", page.uc, ";");

        /* *********************************************************************** */
        String s = f.toString();

        printFile(s, getBasePath() + "/fe/src/" + page.path + "/use" + page.uc + ".ts");
    }
}
