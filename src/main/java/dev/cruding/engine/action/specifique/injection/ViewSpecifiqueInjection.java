package dev.cruding.engine.action.specifique.injection;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewSpecifiqueInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow flow) {
        flow.addJsImport("Ctrl" + uc(), "../Ctrl" + uc());
        if (byEntite()) {
            flow.addProp(entite().lname);
        }
        if (!this.inViewOnly()) {
            flow.useExecute();
            flow.totalScript().L____("const " + lname() + " = () => {");
            flow.totalScript().L________("execute(Ctrl" + page().uc + "." + lname());
            if (this.byForm()) {
                flow.totalScript().__(", { form }");
            }
            if (byEntite()) {
                flow.totalScript().__(", { " + entite().lname + " }");
            }
            flow.totalScript().__(");");
            flow.totalScript().L____("};");
        }

        if (hasReussi()) {
            flow.useExecute("execute, success, rid");
        } else if (this.confirmer()) {
            flow.useExecute("execute, rid");
        } else {
            flow.useExecute("execute");
        }
        if (this.confirmer() || hasReussi()) {
            flow.addSpecificSelector("resultat", page().uc + "Resultat", "../Mdl" + page().uc);
        }

        if (hasReussi()) {
            flow.useEffect();
            flow.totalScript().__("\n");
            flow.totalScript().L____("useEffect(() => {");

            flow.totalScript().L________("if (success) {");
            for (Action siReussiAction : siReussi()) {
                siReussiAction.viewActionInjection.addFlowScript(flow, 2);
            }
            flow.totalScript().L________("}");
            flow.totalScript().L____("}, [success]);");
        }

        return false;
    }

    public void addFlowScript(ViewFlow flow, int level) {}



    public void addI18n(Flow f) {
        if (!noUi()) {

            Entite e = entite() == null ? Contexte.getInstance().getEntite(page().entiteUname) : entite();
            if (e != null) {

                String nomAction = LabelMapper.getInstance().nomAction(lcoreName(), e);
                String titreConfirmation = LabelMapper.getInstance().titreConfirmation(lcoreName(), e);
                String enteteConfirmation = LabelMapper.getInstance().enteteConfirmation(lcoreName(), e);
                String messageSuccess = LabelMapper.getInstance().messageSuccess(lcoreName(), e);


                String key = "Action" + page().module.unameLast + "." + "Uc" + uc() + "." + actionKey();
                f.L____("[", key, "]", ": '", nomAction, "',");

                if (confirmer()) {
                    f.L____("[titreConfirmation(", key, ")]: '", titreConfirmation, "',");
                    f.L____("[enteteConfirmation(", key, ")]: '", enteteConfirmation, "',");
                    f.L____("[messageSuccess(", key, ")]: '", messageSuccess, "',");
                }
            }
        }
    }
}
