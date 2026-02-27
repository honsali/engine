package dev.cruding.engine.component.container;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Util;

public class Breadcrumb extends Component {

    private String uname;

    public Breadcrumb(Element element, String uname, Component... componentList) {
        super(element, componentList);
        this.uname = uname;
    }

    public Breadcrumb(Element element, Component... componentList) {
        super(element, componentList);
    }

    public void addImport(ViewFlow flow) {
        String path = Util.getRelativePath(element.path, element.page.module.path, inElement);
        flow.addJsImport("Fil" + uname, path + "/" + StringUtils.uncapitalize(uname) + "/Fil" + uname);
    }

    public void addScript(ViewFlow f) {
        f.useReady();
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("<Fil").append(uname).append(" siPret={setPret}>");
        return false;
    }

    public void addCloseTag(ViewFlow flow, int level) {
        indent(flow, level).append("</Fil").append(uname).append(">");
    }

}
