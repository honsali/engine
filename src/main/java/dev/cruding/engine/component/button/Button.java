package dev.cruding.engine.component.button;

import dev.cruding.engine.action.Action;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.Flow;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Util;

public class Button extends Component {

    public Action action;
    public String typeButton;

    public Button(Action action) {
        super(action.element);
        this.action = action;
    }

    public void addImport(ViewFlow flow) {
        if (action.noUi()) {
            return;
        } else if (action.normal() && action.byPassSecurity) {
            typeButton = "ButtonNormal";
        } else if (action.normal()) {
            typeButton = "ActionUcNormale";
        } else if (action.primary() && action.byPassSecurity) {
            typeButton = "ButtonFort";
        } else if (action.primary()) {
            typeButton = "ActionUcForte";
        } else if (action.ucConfirmer()) {
            typeButton = "ActionUcConfirmer";
        } else if (action.ucDialogue()) {
            typeButton = "ActionUcDialogue";
        } else {
            typeButton = "ActionUc" + action.ucoreName;
        }

        flow.addJsImport("{ " + typeButton + " }", "waxant");
        if (action.nfc() && action.icon != null) {
            flow.addJsImport("{ " + action.icon + " }", "@ant-design/icons");
        }

        if (action.targetPage != null) {
            flow.addJsImport("{ " + action.targetPage.name + " }", action.targetPage.module.pageList(element.path, inElement));
        }
        String s = Util.getRelativePath(action.element.path, action.page.module.path, false);
        flow.addJsImport("{ Action" + action.page.module.unameLast + " }", s + "/Action" + action.page.module.unameLast);
    }

    public void appendContent(ViewFlow vf, Flow flow) {

        String nameAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;
        if (action.noUi()) {
            return;
        }
        flow.__("<").append(typeButton);

        flow.__(" nom={").append(nameAction).append("}");
        if (action.targetPage != null) {
            flow.__(" page={").append(action.targetPage.name).append("}");
        }
        if (action.uca() && action.model != null) {
            flow.__(" modele={").append(action.model).append("}");
        }
        if (!action.isEmpty) {
            flow.__(" action={").append(action.lnameWithEntity).append("}");
        }
        if (action.nfc() && action.icon != null) {
            flow.__(" icone={<").append(action.icon).append(" />}");
        }

        if (action.confirm || action.hasSuccessInViewOnly) {
            flow.__(" rid={rid}");
        }

        flow.__(" />");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        String nameAction = "Action" + action.page.module.unameLast + "." + "Uc" + action.page.uc + "." + action.actionKey;
        if (action.noUi()) {
            return false;
        }
        indent(flow, level).append("<").append(typeButton);

        flow.totalUi().__(" nom={").append(nameAction).append("}");
        if (action.targetPage != null) {
            flow.totalUi().__(" page={").append(action.targetPage.name).append("}");
        }
        if (action.uca() && action.model != null) {
            flow.totalUi().__(" modele={").append(action.model).append("}");
        }
        if (!action.isEmpty) {
            if (flow.hasParams() || action.delayedCall) {
                flow.totalUi().__(" action={").append(action.lnameWithoutEntity).append("}");
            } else {
                flow.totalUi().__(" action={").append(action.lnameWithEntity).append("}");
            }
        }
        if (action.nfc() && action.icon != null) {
            flow.totalUi().__(" icone={<").append(action.icon).append(" />}");
        }

        if (action.confirm || action.hasSuccessInViewOnly) {
            flow.totalUi().__(" rid={etat", action.unameWithEntity, ".rid}");
        }

        flow.totalUi().__(" />");
        return false;
    }
}
