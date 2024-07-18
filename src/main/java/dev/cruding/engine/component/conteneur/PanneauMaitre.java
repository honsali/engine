package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class PanneauMaitre extends Component {

    public PanneauMaitre(Page page, Component... componentList) {
        super(page, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ PanneauMaitre }", "waxant");

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<PanneauMaitre");

        for (Component component : componentList) {
            if (component instanceof PlaqueEtat) {
                flow.addToUi(" etat={").append(component.entity.lname).append("?.etat?.libelle}");
            } else if (component instanceof BlocActionPanneau) {
                flow.addToUi(" blocAction={ }");
            }
        }
        flow.addToUi(">");
    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</PanneauMaitre>");
    }

}
