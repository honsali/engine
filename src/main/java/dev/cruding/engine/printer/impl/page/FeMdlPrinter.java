package dev.cruding.engine.printer.impl.page;

import java.util.ArrayList;
import java.util.List;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MdlFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.helper.Attribute;
import dev.cruding.engine.printer.Printer;

public class FeMdlPrinter extends Printer {

    public void print(Page page) {
        MdlFlow f = new MdlFlow();
        List<Action> actionList = Contexte.getInstance().actionPage(page);
        /* *********************************************************************** */
        for (Action action : actionList) {
            action.mdlActionInjection.addMdlImport(f);
            action.mdlActionInjection.addMdlRequestAttribute(f);
            action.mdlActionInjection.addMdlResultAttribute(f);
            action.mdlActionInjection.addMdlStateAttribute(f);
            if (!action.inViewOnly) {
                f.addMdlStateAttribute("etat" + action.unameAvecEntite, "createEtatInit()");
                f.addMdlSelectorAttribute("etat" + action.unameAvecEntite, "Etat" + action.unameAvecEntite);
            }
            if (action.resultatIn != null) {
                f.addMdlStateAttribute(action.resultatIn.lname + action.entite.uname, action.resultatIn.jstype);
                f.addMdlSelectorAttribute(action.resultatIn.lname + action.entite.uname, action.resultatIn.uname + action.entite.uname);
            }
        }
        f.addMdlImport("{ createSelector, createSlice }", "@reduxjs/toolkit");
        f.addMdlImport("{ createEtatError, createEtatInit, createEtatPending, createEtatSuccess, IRequete, IResultat, IRootState }", "waxant");
        f.addMdlImport("Ctrl" + page.uc, "./Ctrl" + page.uc);
        /* *********************************************************************** */

        f.flushMdlImportBloc();

        f.L("");// --------------------------------------------

        f.L("export interface Req", page.uc, " extends IRequete {");
        f.flushMdlRequestAttributeBloc();
        f.L("}");

        f.L("");// --------------------------------------------

        f.L("export interface Res", page.uc, " extends IResultat {");
        f.flushMdlResultAttributeBloc();
        f.L("}");

        f.L("");// --------------------------------------------

        f.L("const initialState = {");
        f.flushMdlStateAttributeBloc();
        f.L("};");

        f.L("");// --------------------------------------------
        f.L("type ", page.uc, "Type = typeof initialState;");
        f.L("");// --------------------------------------------

        f.L("const Slice", page.uc, " = createSlice({");
        f.L____("name: 'Mdl", page.uc, "',");
        f.L____("initialState,");
        f.L____("reducers: {");

        for (Action action : actionList) {
            action.mdlActionInjection.addMdlReducer(f);
        }
        for (Action action : actionList) {
            if (!action.inViewOnly) {
                f.L________("resetEtat" + action.unameAvecEntite, "(state) {");
                f.L____________("state.etat" + action.unameAvecEntite, " = createEtatInit();");
                f.L________("},");
            }
        }



        f.L____("},");

        f.L____("extraReducers(builder) {");
        f.L________("builder");
        for (Action action : actionList) {
            if (action.uc != null && !action.inViewOnly) {
                action.mdlActionInjection.addMdlExtraReducer(f);
            }
        }

        // String listeNomAction = actionList.stream().filter(action -> !action.inViewOnly).map(action ->
        // "Ctrl" + page.uc + "." + action.lnameAvecEntite).collect(Collectors.joining(", "));


        f.__(";");
        f.L____("},");
        f.L("});");
        f.L("");
        f.L("export const Mdl", page.uc, " = Slice", page.uc, ".actions;");
        f.L("");
        f.L("const selectMdl", page.uc, " = (state: IRootState) => state.mdl", page.uc, ";");

        List<Attribute> mdlResultAttributeList = new ArrayList<>(f.mdlSelectorAttributeSet);
        for (Attribute att : mdlResultAttributeList) {
            f.L("export const select", att.name, " = createSelector([selectMdl", page.uc, "], (state: ", page.uc, "Type) => state.", att.type, ");");
        }
        f.L("");
        f.L("export default Slice", page.uc, ".reducer;");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/Mdl" + page.uc + ".ts");
    }
}
