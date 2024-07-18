package dev.cruding.engine.printer.impl.page;

import java.util.List;
import java.util.stream.Collectors;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.printer.Printer;

public class FeMdlPrinter extends Printer {

    public void print(Page page) {
        MCFlow f = new MCFlow();
        List<Action> actionList = Context.getInstance().actionPage(page);
        /* *********************************************************************** */

        f.addMdlImport("{ createSelector, createSlice, isPending, isRejected }", "@reduxjs/toolkit");
        f.addMdlImport("{ IRequete, IResultat }", "waxant");
        f.addMdlImport("Ctrl" + page.uc, "./Ctrl" + page.uc);
        for (Action action : actionList) {
            action.addMdlImport(f);
        }
        f.flushMdlImportBloc();

        f.L(""); // ---------------------------------------------------------------------------------------

        f.L("export interface Req", page.uc, " extends IRequete {");
        for (Action action : actionList) {
            action.addMdlRequestAttribute(f);
        }
        f.flushMdlRequestAttributeBloc();
        f.L("}");
        f.L("");
        f.L("export interface Res", page.uc, " extends IResultat {");
        for (Action action : actionList) {
            action.addMdlResultAttribute(f);
        }
        f.flushMdlResultAttributeBloc();
        f.L("}");
        f.L("");
        f.L("const initialState = {");
        f.L____("resultat: {} as Res", page.uc, ",");
        for (Action action : actionList) {
            action.addMdlStateAttribute(f);
        }
        f.flushMdlStateAttributeBloc();
        f.L("};");
        f.L("");
        f.L("type ", page.uc, "Type = typeof initialState;");
        f.L("");
        f.L("const Slice", page.uc, " = createSlice({");
        f.L____("name: 'Mdl", page.uc, "',");
        f.L____("initialState,");
        f.L____("reducers: {");
        boolean full = false;
        for (Action action : actionList) {
            full = full || action.addMdlReducer(f);
        }
        if (full) {
            f.L____("},");
        } else {
            f.__("},");
        }
        f.L____("extraReducers(builder) {");
        f.L________("builder");
        for (Action action : actionList) {
            action.addMdlExtraReducer(f);
        }

        String listeNomAction = actionList.stream().filter(action -> !action.isInViewOnly()).map(action -> "Ctrl" + page.uc + "." + action.lname).collect(Collectors.joining(", "));

        f.L____________(".addMatcher(isPending(", listeNomAction, "), (state) => {");
        f.L____________("    state.resultat = {} as Res", page.uc, ";");
        f.L____________("})");
        f.L____________(".addMatcher(isRejected(", listeNomAction, "), (state) => {");
        f.L____________("    state.resultat = { rid: 'erreur' } as Res", page.uc, ";");
        f.L____________("});");

        f.L____("},");
        f.L("});");
        f.L("");
        f.L("export const Mdl", page.uc, " = Slice", page.uc, ".actions;");
        f.L("");
        f.L("const selectMdl", page.uc, " = (state) => state.mdl", page.uc, ";");
        f.L("export const select", page.uc, "Resultat = createSelector([selectMdl", page.uc, "], (state: ", page.uc, "Type) => state.resultat);");
        for (Action action : actionList) {
            action.addMdlSelector(f, page.uc);
        }
        f.L("");
        f.L("export default Slice", page.uc, ".reducer;");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/Mdl" + page.uc + ".ts");
    }
}