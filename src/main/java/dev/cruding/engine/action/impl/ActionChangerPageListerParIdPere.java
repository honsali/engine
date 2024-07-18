package dev.cruding.engine.action.impl;

import dev.cruding.engine.action.ActionAvecTable;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;

public class ActionChangerPageListerParIdPere extends ActionAvecTable {

    public ActionChangerPageListerParIdPere() {
        super("changerPage");
    }

    public void addCtrlImport(MCFlow f) {
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addMdlImport(MCFlow f) {
        f.addMdlImport("{ IListePaginee" + entity.uname + " }", "modele/" + entity.modulePath + "/Domaine" + entity.uname);
    }

    public void addMdlRequestAttribute(MCFlow f) {
        f.addMdlRequestAttribute("pageCourante", "number");
    }

    public void addMdlResultAttribute(MCFlow f) {
        f.addMdlResultAttribute("listePaginee" + entity.uname, "IListePaginee" + entity.uname);
    }

    public void addMdlStateAttribute(MCFlow f) {
        f.addMdlStateAttribute("listePaginee" + entity.uname, "IListePaginee" + entity.uname);
    }

    public void addCtrlImplementation(MCFlow f) {
        f.L("");
        f.L("const ", lname, "Impl = async (requete: Req", uc, ", resultat: Res", uc, ", thunkAPI) => {");
        f.L____("resultat.listePaginee", entity.uname, " = await Service", entity.uname, ".listerEnPageParId" + entity.ufather, "(");
        if (byGrandFatherId && entity.haveGrandFather) {
            f.__("requete.id" + entity.ugrandfather, ", ");
        }
        f.__("requete.id", entity.ufather);
        f.__(", requete.pageCourante);");
        f.L("};");
    }

    public void addCtrlDeclaration(MCFlow f) {
        f.L____("", lname, ": action<Req", uc, ", Res", uc, ">(", lname, "Impl, 'Ctrl", uc, "/", lname, "'),");
    }

    public boolean addMdlReducer(MCFlow flow) {
        return false;
    }

    public void addMdlExtraReducer(MCFlow f) {
        f.L____________(".addCase(Ctrl", uc, ".", lname, ".fulfilled, (state, action) => {");
        f.L____________("    state.resultat = action.payload;");
        f.L____________("    state.listePaginee", entity.uname, " = action.payload.listePaginee", entity.uname, ";");
        f.L____________("})");
    }

    public void addViewScript(ViewFlow f) {
        f.totalScript().L("");
        f.totalScript().L____("const actionChangementPage = (pageCourante: number) => {");
        f.totalScript().L____("    execute(Ctrl", uc, ".", lname, ", { pageCourante });");
        f.totalScript().L____("};");
        f.useExecute();
        f.addJsImport("Ctrl" + uc, mvcPath + "/Ctrl" + uc);
    }

}