package dev.cruding.engine.component.bouton.bloc;

import dev.cruding.engine.component.Component;
import dev.cruding.engine.component.conteneur.PanneauMaitre;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class BlocAction extends Component {

    public BlocAction(Page page, Component... componentList) {
        super(page, componentList);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{BlocActionGauche}", "waxant");

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<BlocActionGauche");
        if (this.fatherComponent instanceof PanneauMaitre) {
            flow.addToUi(" key=\"footer\"");
        }
        flow.addToUi(">");

    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</BlocActionGauche>");
    }

}
