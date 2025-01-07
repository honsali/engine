package dev.cruding.engine.composant.bouton;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class Bouton extends Composant {

    public Actionnable actionnable;

    public Bouton(Actionnable actionnable) {
        super(actionnable.element);
        this.actionnable = actionnable;
    }

    public void addImport(ViewFlow flow) {
        if (actionnable.noUi()) {
            return;
        } else if (actionnable.normale()) {
            flow.addJsImport("{ActionUcNormale}", "waxant");
        } else if (actionnable.forte()) {
            flow.addJsImport("{ActionUcForte}", "waxant");
        } else if (actionnable.ucConfirmer()) {
            flow.addJsImport("{ActionUcConfirmer}", "waxant");
        } else {
            flow.addJsImport("{ActionUc" + actionnable.ucoreName + "}", "waxant");
        }

        if (actionnable.nfc() && actionnable.icone != null) {
            flow.addJsImport("{ " + actionnable.icone + " }", "@ant-design/icons");
        }

        if (actionnable.targetPage != null) {
            flow.addJsImport("{ " + actionnable.targetPage.name + " }", actionnable.targetPage.module.listePage(element.path, inElement));
        }
        String s = Util.getRelativePath(actionnable.element.path, actionnable.page.module.path, false);
        flow.addJsImport("{ Action" + actionnable.page.module.unameLast + " }", s + "/Action" + actionnable.page.module.unameLast);
    }

    public void addInlineTag(ViewFlow flow) {
        addOpenTag(flow, 0);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        String nomAction = "Action" + actionnable.page.module.unameLast + "." + "Uc" + actionnable.page.uc + "." + actionnable.actionKey;
        if (actionnable.noUi()) {
            return false;
        } else if (actionnable.normale()) {
            indent(flow, level).append("<ActionUcNormale");
        } else if (actionnable.forte()) {
            indent(flow, level).append("<ActionUcForte");
        } else if (actionnable.ucConfirmer()) {
            indent(flow, level).append("<ActionUcConfirmer");
        } else {
            indent(flow, level).append("<ActionUc").append(actionnable.ucoreName);
        }
        flow.addToUi(" nom={").append(nomAction).append("}");
        if (actionnable.targetPage != null) {
            flow.addToUi(" page={").append(actionnable.targetPage.name).append("}");
        }
        if (actionnable.uca() && actionnable.modele != null) {
            flow.addToUi(" modele={").append(actionnable.modele).append("}");
        }
        if (!actionnable.isVide && actionnable.action != null) {
            flow.addToUi(" action={").append(actionnable.action.lname()).append("}");
        }
        if (actionnable.nfc() && actionnable.icone != null) {
            flow.addToUi(" icone={<").append(actionnable.icone).append(" />}");
        }
        if (actionnable.confirmer || actionnable.hasReussi) {
            flow.addToUi(" rid={rid}");
        }
        flow.addToUi(" />");
        return false;
    }
}
