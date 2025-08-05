package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;

public class PanneauFiltre extends Conteneur {

    public PanneauFiltre(Element element, Entite entite, boolean initialiserRecherche, Composant... listeComposant) {
        super(element, entite, listeComposant);
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Filtre").append(titre());
        flow.totalUi().__(">");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Filtre>");
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{ Filtre }", "waxant");
        flow.useForm();
        flow.useEffect();
        flow.addJsImport("{ BlocAction }", "waxant");
        flow.addJsImport("{ ActionUcInitialiserFiltre }", "waxant");
        flow.addJsImport("{ ActionUcAppliquerFiltre }", "waxant");
    }


}
