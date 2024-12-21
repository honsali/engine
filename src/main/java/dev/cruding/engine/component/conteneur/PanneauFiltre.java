package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.action.impl.ActionChercher;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.component.bouton.Actionnable.ActionType;
import dev.cruding.engine.entity.Entity;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class PanneauFiltre extends Conteneur {

    public PanneauFiltre(Element element, Entity entity, Component... componentList) {
        super(element, entity, componentList);
        Actionnable actionnable = new Actionnable(ActionType.NOUI, "chercher", entity, element);
        actionnable.action(new ActionChercher());
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Filtre").append(titre());
        flow.addToUi(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Filtre>");
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Filtre }", "waxant");
        flow.useForm();
        flow.useExecute();
        flow.useEffect();
        flow.addJsImport("{ BlocAction }", "waxant");
        flow.addJsImport("{ ActionUcInitialiserFiltre }", "waxant");
        flow.addJsImport("{ ActionUcAppliquerFiltre }", "waxant");
        flow.addJsImport("Ctrl" + element.page.uc, "../Ctrl" + element.page.uc);
    }

    public void addScript(ViewFlow flow) {
        flow.totalScript().L____("useEffect(() => {");
        flow.totalScript().L________("initialiserFiltre", entity.uname, "();");
        flow.totalScript().L____("}, []);");
        flow.totalScript().L("");
        flow.totalScript().L____("const initialiserFiltre", entity.uname, " = () => {");
        flow.totalScript().L________("form.resetFields();");
        flow.totalScript().L________("appliquerFiltre", entity.uname, "();");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
        flow.totalScript().L____("const appliquerFiltre", entity.uname, " = () => {");
        flow.totalScript().L________("execute(Ctrl", element.page.uc, ".chercher", entity.uname, ", { form });");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
    }
}
