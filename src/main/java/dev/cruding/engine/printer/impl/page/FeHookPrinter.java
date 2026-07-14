package dev.cruding.engine.printer.impl.page;

import java.util.ArrayList;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.flow.helper.Attribute;
import dev.cruding.engine.flow.helper.AttributeSorter;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeHookPrinter extends Printer {

    public void print(Page page) {
        MdlFlow f = new MdlFlow();
        List<Action> actionList = Context.getInstance().actionPage(page);
        /* *********************************************************************** */
        for (Action action : actionList) {
            action.mdlActionInjection.addMdlStateAttribute(f);
            if (!action.inViewOnly && !action.isEmpty) {
                f.addMdlStateAttribute("etat" + action.unameWithEntity, "createEtatInit()");
                f.addMdlSelectorAttribute("etat" + action.unameWithEntity, "Etat" + action.unameWithEntity);
            }
            if (action.resultIn != null) {
                f.addMdlStateAttribute(action.resultIn.lname + action.entity.uname, action.resultIn.jstype);
                f.addMdlSelectorAttribute(action.resultIn.lname + action.entity.uname, action.resultIn.uname + action.entity.uname);
            }

        }
        f.addMdlImport("{ useSelector }", "react-redux");
        f.addMdlImport("{ useParams }", "react-router");
        f.addMdlImport("{ useAppDispatch }", "waxant");

        List<Attribute> mdlSelectorAttributeList = new ArrayList<>(f.mdlSelectorAttributeSet);
        mdlSelectorAttributeList.sort(new AttributeSorter());

        for (Attribute att : mdlSelectorAttributeList) {
            f.addMdlImport("{ select" + att.name + " }", "./Mdl" + page.uc);
        }
        f.addMdlImport("Ctrl" + page.uc, "./Ctrl" + page.uc);

        f.addMdlImport("{ Mdl" + page.uc + ", Req" + page.uc + " }", "./Mdl" + page.uc);
        /* *********************************************************************** */
        f.flushMdlImportBlock();

        f.L("");
        f.L("const use", page.uc, " = () => {");


        f.L("");
        f.L____("const dispatch = useAppDispatch();");
        f.L____("const params = useParams();");
        f.L("");

        for (Attribute att : mdlSelectorAttributeList) {
            f.L____("const ", att.type, " = useSelector(select", att.name, ");");
        }


        f.L("");
        f.L____("const createAction = (action: any) => (req?: Req", page.uc, ") => dispatch(action({ ...req, ...params }));");
        f.L("");
        f.L____("return {");
        f.L________("// Actions");

        for (Action action : actionList) {
            if (!action.inViewOnly && !action.isEmpty) {
                f.L________(action.lnameWithEntity, ": createAction(Ctrl", page.uc, ".", action.lnameWithEntity, "),");
            }
        }
        for (Action action : actionList) {
            if (!action.inViewOnly && !action.isEmpty) {
                f.L________("resetEtat", action.unameWithEntity, ": () => dispatch(Mdl", page.uc, ".resetEtat", action.unameWithEntity, "()),");
            }
        }
        f.L("");
        f.L________("// State");
        for (Attribute att : mdlSelectorAttributeList) {
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
