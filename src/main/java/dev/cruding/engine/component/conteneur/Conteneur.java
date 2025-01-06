package dev.cruding.engine.component.conteneur;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Context;
import dev.cruding.engine.gen.Element;

public class Conteneur extends Component {

    public String titre = null;

    public Conteneur(Element element, Component... componentList) {
        super(element, componentList);
    }

    public Conteneur(Element element, Entite entite, Component... componentList) {
        super(element, entite, componentList);
    }

    public void addImport(ViewFlow flow) {
        for (Component component : componentList) {
            component.addImport(flow);
        }
    }

    public Conteneur titre(String titre) {
        this.titre = titre;
        return this;

    }

    public String titre() {
        if (titre != null) {
            String label = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(titre), " "));
            Context.getInstance().addLabel(element.page.module.uname, "Uc" + element.page.uc + "." + titre, label);
        }
        return titre != null ? (" titre=\"" + titre + "\"") : "";
    }


}
