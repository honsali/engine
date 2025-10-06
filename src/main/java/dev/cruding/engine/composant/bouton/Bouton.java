package dev.cruding.engine.composant.bouton;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class Bouton extends Composant {

    public Action action;
    public String typeBouton;

    public Bouton(Action action) {
        super(action.element);
        this.action = action;
    }

    public void addImport(ViewFlow flow) {
        if (action.noUi()) {
            return;
        } else if (action.normale() && action.sansSecurite) {
            typeBouton = "BoutonNormal";
        } else if (action.normale()) {
            typeBouton = "ActionUcNormale";
        } else if (action.forte() && action.sansSecurite) {
            typeBouton = "BoutonFort";
        } else if (action.forte()) {
            typeBouton = "ActionUcForte";
        } else if (action.ucConfirmer()) {
            typeBouton = "ActionUcConfirmer";
        } else if (action.ucDialogue()) {
            typeBouton = "ActionUcDialogue";
        } else {
            typeBouton = "ActionUc" + action.ucoreName;
        }

        flow.addJsImport("{ " + typeBouton + " }", "waxant");
        if (action.nfc() && action.icone != null) {
            flow.addJsImport("{ " + action.icone + " }", "@ant-design/icons");
        }

        if (action.targetPage != null) {
            flow.addJsImport("{ " + action.targetPage.name + " }", action.targetPage.module.listePage(element.path, inElement));
        }
        String s = Util.getRelativePath(action.element.path, action.page.module.path, false);
        flow.addJsImport("{ Action" + action.page.module.unameLast + " }", s + "/Action" + action.page.module.unameLast);
    }

    public void appendContent(ViewFlow vf, Flow flow) {

        String nomAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;
        if (action.noUi()) {
            return;
        }
        flow.__("<").append(typeBouton);

        flow.__(" nom={").append(nomAction).append("}");
        if (action.targetPage != null) {
            flow.__(" page={").append(action.targetPage.name).append("}");
        }
        if (action.uca() && action.modele != null) {
            flow.__(" modele={").append(action.modele).append("}");
        }
        if (!action.isVide) {
            flow.__(" action={").append(action.lnameAvecEntite).append("}");
        }
        if (action.nfc() && action.icone != null) {
            flow.__(" icone={<").append(action.icone).append(" />}");
        }
        if (action.confirmer || action.hasReussiInViewOnly) {
            flow.__(" rid={rid}");
        }
        flow.__(" />");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        String nomAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;
        if (action.noUi()) {
            return false;
        }
        indent(flow, level).append("<").append(typeBouton);

        flow.totalUi().__(" nom={").append(nomAction).append("}");
        if (action.targetPage != null) {
            flow.totalUi().__(" page={").append(action.targetPage.name).append("}");
        }
        if (action.uca() && action.modele != null) {
            flow.totalUi().__(" modele={").append(action.modele).append("}");
        }
        if (!action.isVide) {
            if (flow.hasParams() || action.appelDecale) {
                flow.totalUi().__(" action={").append(action.lnameSansEntite).append("}");
            } else {
                flow.totalUi().__(" action={").append(action.lnameAvecEntite).append("}");
            }
        }
        if (action.nfc() && action.icone != null) {
            flow.totalUi().__(" icone={<").append(action.icone).append(" />}");
        }
        if (action.confirmer || action.hasReussiInViewOnly) {
            flow.totalUi().__(" rid={etat", action.unameAvecEntite, ".rid}");
        }
        flow.totalUi().__(" />");
        return false;
    }
}
