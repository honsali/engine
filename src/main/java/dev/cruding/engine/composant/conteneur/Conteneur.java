package dev.cruding.engine.composant.conteneur;

import org.apache.commons.lang3.StringUtils;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.entite.Entite;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;

public class Conteneur extends Composant {

    public String titre = null;

    public Conteneur(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public Conteneur(Element element, Entite entite, Composant... listeComposant) {
        super(element, entite, listeComposant);
    }

    public void addImport(ViewFlow flow) {
        for (Composant composant : listeComposant) {
            composant.addImport(flow);
        }
    }

    public Conteneur titre(String titre) {
        this.titre = titre;
        return this;

    }

    public String titre() {
        if (titre != null) {
            String label = StringUtils
                    .capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(titre), " "));
            Contexte.getInstance().addLabel(element.page.module.uname, "Uc" + element.page.uc + "." + titre, label);
        }
        return titre != null ? (" titre=\"" + titre + "\"") : "";
    }

}
