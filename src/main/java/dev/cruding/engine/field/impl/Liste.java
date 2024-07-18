package dev.cruding.engine.field.impl;

import dev.cruding.engine.element.Element;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.MCFlow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;

public class Liste<T extends Entity> extends Ref<T> {

    public Liste(Ref<T> r) {
        super(r.type, r.lname);
    }

    public String getExtension() {
        return " liste={liste" + jtype + "}";

    }

    public void addCtrlImport(MCFlow f, String type) {
        Entity entity = Context.getInstance().getEntity(jtype);
        f.addCtrlImport("Service" + entity.uname, "modele/" + entity.modulePath + "/Service" + entity.uname);
    }

    public void addCtrlImplementation(MCFlow f, String typeAction) {

        Entity entity = Context.getInstance().getEntity(jtype);
        f.L____("const liste", jtype, " = await Service", jtype, ".lister(");
        if (entity.haveFather) {
            f.__("requete.id" + entity.ufather);
        }
        f.__(");");
        f.L________("resultat.liste", jtype, " = liste", jtype, ".map((x) => {");
        f.L________("return { code: x.id, libelle: x.nom + ' ' + x.prenom };");
        f.L____("});");

    }

    public void addViewScript(ViewFlow f, String uc, String mvcPath) {
        f.addSpecificSelector("liste" + jtype, mvcPath + "/Mdl" + uc);
    }

    public void addMdlImport(MCFlow f, String type) {
        f.addMdlImport("{ IReference }", "modele/commun/reference/DomaineReference");
    }

    public void addMdlResultAttribute(MCFlow f, String type) {
        f.addMdlResultAttribute("liste" + jtype, "IReference[]");
    }

    public void addMdlStateAttribute(MCFlow f, String type) {
        f.addMdlStateAttribute("liste" + jtype, "IReference[]");
    }

    public void addMdlSelector(MCFlow f, String uc) {
        f.L("export const selectListe", jtype, " = createSelector([selectMdl", uc, "], (state: ", uc, "Type) => state.liste", jtype, ");");
    }

    public void addMdlExtraReducer(MCFlow f, String type) {
        f.L____________("    state.liste", jtype, " = action.payload.liste", jtype, ";");
    }

    public String ui(String element) {

        switch (element) {
        case Element.FORM:
            return "ChampListe";
        case Element.DETAIL:
            return "reference";
        case Element.TABLEAU:
            return "Colonne tc=\"reference\"";
        default:
            return "";
        }

    }
}
