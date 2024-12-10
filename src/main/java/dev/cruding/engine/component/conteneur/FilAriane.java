package dev.cruding.engine.component.conteneur;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Element;
import dev.cruding.engine.gen.helper.Util;

public class FilAriane extends Component {

    private String uname;

    public FilAriane(Element element, String uname, Component... componentList) {
        super(element, componentList);
        this.uname = uname;
    }

    public FilAriane(Element element, Component... componentList) {
        super(element, componentList);
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
