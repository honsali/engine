package dev.cruding.engine.composant.conteneur;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.helper.Util;

public class FilAriane extends Composant {

    private String uname;

    public FilAriane(Element element, String uname, Composant... listeComposant) {
        super(element, listeComposant);
        this.uname = uname;
    }

    public FilAriane(Element element, Composant... listeComposant) {
        super(element, listeComposant);
    }

    public void addImport(ViewFlow flow) {
        String path = Util.getRelativePath(element.path, element.page.module.path, inElement);
        flow.addJsImport("Fil" + uname, path + "/" + StringUtils.uncapitalize(uname) + "/Fil" + uname);
    }

    public void addScript(ViewFlow f) {
        f.usePret();
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Fil").append(uname).append(" siPret={setPret}>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Fil").append(uname).append(">");
    }

}
