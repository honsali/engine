package dev.cruding.engine.composant.bouton;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class Bouton extends Composant {

    public Action action;

    public Bouton(Action action) {
        super(action.element);
        this.action = action;
    }

    public void addImport(ViewFlow flow) {
        if (action.noUi()) {
            return;
        } else if (action.normale()) {
            flow.addJsImport("{ActionUcNormale}", "waxant");
        } else if (action.forte()) {
            flow.addJsImport("{ActionUcForte}", "waxant");
        } else if (action.ucConfirmer()) {
            flow.addJsImport("{ActionUcConfirmer}", "waxant");
        } else {
            flow.addJsImport("{ActionUc" + action.ucoreName + "}", "waxant");
        }

        if (action.nfc() && action.icone != null) {
            flow.addJsImport("{ " + action.icone + " }", "@ant-design/icons");
        }

        if (action.targetPage != null) {
            flow.addJsImport("{ " + action.targetPage.name + " }", action.targetPage.module.listePage(element.path, inElement));
        }
        String s = Util.getRelativePath(action.element.path, action.page.module.path, false);
        flow.addJsImport("{ Action" + action.page.module.unameLast + " }", s + "/Action" + action.page.module.unameLast);
    }

    public void addInlineTag(ViewFlow flow) {
        addOpenTag(flow, 0);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        String nomAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;
        if (action.noUi()) {
            return false;
        } else if (action.normale()) {
            indent(flow, level).append("<ActionUcNormale");
        } else if (action.forte()) {
            indent(flow, level).append("<ActionUcForte");
        } else if (action.ucConfirmer()) {
            indent(flow, level).append("<ActionUcConfirmer");
        } else {
            indent(flow, level).append("<ActionUc").append(action.ucoreName);
        }
        flow.addToUi(" nom={").append(nomAction).append("}");
        if (action.targetPage != null) {
            flow.addToUi(" page={").append(action.targetPage.name).append("}");
        }
        if (action.uca() && action.modele != null) {
            flow.addToUi(" modele={").append(action.modele).append("}");
        }
        if (!action.isVide) {
            flow.addToUi(" action={").append(action.lname).append("}");
        }
        if (action.nfc() && action.icone != null) {
            flow.addToUi(" icone={<").append(action.icone).append(" />}");
        }
        if (action.confirmer || action.hasReussi) {
            flow.addToUi(" rid={rid}");
        }
        flow.addToUi(" />");
        return false;
    }
}
