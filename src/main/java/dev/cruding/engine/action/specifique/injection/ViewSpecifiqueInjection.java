package dev.cruding.engine.action.specifique.injection;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.action.Action;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;
import dev.cruding.engine.gen.LabelMapper;
import dev.cruding.engine.injection.ViewActionInjection;

public class ViewSpecifiqueInjection extends ViewActionInjection {

    public boolean addViewScript(ViewFlow flow) {
        List<Action> siReussiUiActionList = null;

        if (hasReussi()) {
            ArrayList<Action> siReussiActionList = siReussi();
            siReussiUiActionList = siReussiActionList;// .stream().filter(sr -> sr.inViewOnly).toList();
        }
        if (parEntite()) {
            flow.addProp(entite().lname);
        }


        if (!this.inViewOnly() && !this.noUi() && !this.ucDialogue()) {
            flow.addSelector(lnameAvecEntite());

            if (hasReussi() || confirmer()) {
                flow.addSelector("etat" + unameAvecEntite());
            }
            flow.totalScript().__("\n");

            if (this.parForm() || siReussi().stream().anyMatch(a -> a.parForm)) {
                flow.addParam("form");
                // flow.useForm();
            }
            if (parProp() != null) {
                flow.addParam(parProp());
            }
            if (element().parProp != null) {
                String p = StringUtils.substringBefore(element().parProp, ":");
                if (parId()) {
                    flow.addParam("id" + StringUtils.capitalize(p) + ": " + p + ".id");
                } else {
                    flow.addParam(p);
                }
            }
            if (parEntite()) {
                if (parId()) {
                    flow.addParam("id" + entite().uname + ": " + entite().lname + ".id");
                } else {
                    flow.addParam(entite().lname);
                }
            }
            if (parChamp() != null) {
                flow.addParam(parChamp().lname);
            }
            if (flow.hasParams()) {
                flow.totalScript().L____("const ", lnameSansEntite(), " = () => {");
                flow.totalScript().L________(lnameAvecEntite(), "({ ", flow.joinParams(), " });");
                flow.totalScript().L____("};");
            }

        } else if (this.inViewOnly()) {
            flow.totalScript().L____("const ", lnameAvecEntite(), " = () => {");
            flow.totalScript().L____("};");
        }

        if (siReussiUiActionList != null && siReussiUiActionList.size() > 0) {
            String args = null;
            if (resultatIn() != null) {
                flow.addSelector(resultatIn().lname + entite().uname);
                args = resultatIn().lname + entite().uname;
            }
            flow.useEffect();
            flow.totalScript().__("\n");
            flow.totalScript().L____("useEffect(() => {");
            flow.totalScript().L________("if (etat", unameAvecEntite(), ".succes) {");
            flow.totalScript().L____________("resetEtat", unameAvecEntite(), "();");
            for (Action siReussiAction : siReussiUiActionList) {
                if (siReussiAction.viewActionInjection != null) {
                    siReussiAction.viewActionInjection.addFlowScript(flow, 2, args);
                } else {
                    flow.totalScript().L____________(siReussiAction.lnameAvecEntite);
                    System.out.println(siReussiAction.lnameAvecEntite);
                }
            }
            flow.totalScript().L________("}");
            flow.totalScript().L____("}, [etat", unameAvecEntite(), ".succes]);");

            flow.addSelector("resetEtat" + unameAvecEntite());
        }
        return false;
    }

    public void addI18n(Flow f) {
        if (!noUi()) {

            Entite e = entite() == null ? Contexte.getInstance().getEntite(page().entiteUname) : entite();
            if (e != null) {

                String nomAction = LabelMapper.getInstance().nomAction(lnameSansEntite(), e);
                String titreConfirmation = LabelMapper.getInstance().titreConfirmation(lnameSansEntite(), e);
                String enteteConfirmation = LabelMapper.getInstance().enteteConfirmation(lnameSansEntite(), e);
                String messageSuccess = LabelMapper.getInstance().messageSuccess(lnameSansEntite(), e);

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
