package dev.cruding.engine.composant.conteneur;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Contexte;

public class Onglet extends Conteneur {

    public Onglet(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Onglet}", "waxant");
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        String label = StringUtils.capitalize(StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(titre), " "));
        Contexte.getInstance().addLabel(element.page.module.uname, "onglet." + titre, label);
        indent(flow, level).append("<Onglet key=\"").append(titre).append("\" >");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Onglet>");
    }

}
