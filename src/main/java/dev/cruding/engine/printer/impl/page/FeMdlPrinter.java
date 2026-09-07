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

public class FeMdlPrinter extends Printer {

    public void print(Page page) {
        MdlFlow f = new MdlFlow();
        List<Action> actionList = Context.getInstance().actionPage(page);
        /* *********************************************************************** */
        for (Action action : actionList) {
            action.mdlActionInjection.addMdlImport(f);
            action.mdlActionInjection.addMdlRequestAttribute(f);
            action.mdlActionInjection.addMdlResultAttribute(f);
            action.mdlActionInjection.addMdlStateAttribute(f);
            if (!action.inViewOnly) {
                f.addMdlImport("{ EtatMdl }", "waxant");
                f.addMdlStateAttribute("etat" + action.unameWithEntity, "createEtatInit()");
                f.addMdlSelectorAttribute("etat" + action.unameWithEntity, "Etat" + action.unameWithEntity);
            }
            if (action.resultIn != null) {
                f.addMdlStateAttribute(action.resultIn.lname + action.entity.uname, action.resultIn.jstype);
                f.addMdlSelectorAttribute(action.resultIn.lname + action.entity.uname, action.resultIn.uname + action.entity.uname);
            }
        }
        f.addMdlImport("{ createSelector, createSlice }", "@reduxjs/toolkit");
        f.addMdlImport("{ createEtatError, createEtatInit, createEtatPending, createEtatSuccess, IRequete, IResultat, IRootState }", "waxant");
        f.addMdlImport("Ctrl" + page.uc, "./Ctrl" + page.uc);
        /* *********************************************************************** */

        f.flushMdlImportBlock();

        f.L("");// --------------------------------------------

        f.L("export interface Req", page.uc, " extends IRequete {");
        f.flushMdlRequestAttributeBlock();
        f.L("}");

        f.L("");// --------------------------------------------

        f.L("export interface Res", page.uc, " extends IResultat {");
        f.flushMdlResultAttributeBlock();
        f.L("}");


        f.L("");// --------------------------------------------
        f.L("interface ", page.uc, "Type {");
        f.flushMdlStateTypeBlock();
        f.L("}");
        f.L("");// --------------------------------------------

        f.L("const initialState: ", page.uc, "Type = {");
        f.flushMdlStateAttributeBlock();
        f.L("};");


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
                f.L________("resetEtat" + action.unameWithEntity, "(state) {");
                f.L____________("state.etat" + action.unameWithEntity, " = createEtatInit();");
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
        // "Ctrl" + page.uc + "." + action.lnameWithEntity).collect(Collectors.joining(", "));


        f.__(";");
        f.L____("},");
        f.L("});");
        f.L("");
        f.L("export const Mdl", page.uc, " = Slice", page.uc, ".actions;");
        f.L("");
        f.L("const selectMdl", page.uc, " = (state: IRootState) => state.mdl", page.uc, ";");

        List<Attribute> mdlSelectorAttributeList = new ArrayList<>(f.mdlSelectorAttributeSet);
        mdlSelectorAttributeList.sort(new AttributeSorter());
        for (Attribute att : mdlSelectorAttributeList) {
            f.L("export const select", att.name, " = createSelector([selectMdl", page.uc, "], (state: ", page.uc, "Type) => state.", att.type, ");");
        }
        f.L("");
        f.L("export default Slice", page.uc, ".reducer;");

        /* *********************************************************************** */
        String s = f.toString();
        printFile(s, getBasePath() + "/fe/src/" + page.path + "/Mdl" + page.uc + ".ts");
    }
}
