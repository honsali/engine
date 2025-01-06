package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.action.impl.ActionChercher;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.bouton.Actionnable;
import dev.cruding.engine.component.bouton.Actionnable.ActionType;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;

public class PanneauFiltre extends Conteneur {

    public PanneauFiltre(Element element, Entite entite, Component... componentList) {
        super(element, entite, componentList);
        Actionnable actionnable = new Actionnable(ActionType.NOUI, "chercher", entite, element);
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
        flow.totalScript().L________("initialiserFiltre", entite.uname, "();");
        flow.totalScript().L____("}, []);");
        flow.totalScript().L("");
        flow.totalScript().L____("const initialiserFiltre", entite.uname, " = () => {");
        flow.totalScript().L________("form.resetChamps();");
        flow.totalScript().L________("appliquerFiltre", entite.uname, "();");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
        flow.totalScript().L____("const appliquerFiltre", entite.uname, " = () => {");
        flow.totalScript().L________("execute(Ctrl", element.page.uc, ".chercher", entite.uname, ", { form });");
        flow.totalScript().L____("};");
        flow.totalScript().L("");
    }
}
