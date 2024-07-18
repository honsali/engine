package dev.cruding.engine.component.conteneur;

import org.apache.commons.lang3.StringUtils;
import dev.cruding.engine.component.Component;
import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;
import dev.cruding.engine.gen.Util;

public class FilAriane extends Component {

    private String uname;

    public FilAriane(Page page, String uname, Component... componentList) {
        super(page, componentList);
        this.uname = uname;
    }

    public FilAriane(Page page, Component... componentList) {
        super(page, componentList);
    }

    public void addImport(ViewFlow flow) {
        String path = Util.getRelativePath(page.path, page.moduleDefinition.path, inElement);
        flow.addJsImport("Fil" + uname, path + "/" + StringUtils.uncapitalize(uname) + "/Fil" + uname);
    }

    public void addScript(ViewFlow f) {
        f.usePret();
    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Fil").append(uname).append(" siPret={setPret}>");
    }

    public void addCloseTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("</Fil").append(uname).append(">");
    }

}
