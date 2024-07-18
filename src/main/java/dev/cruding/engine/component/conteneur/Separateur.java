package dev.cruding.engine.component.conteneur;

import dev.cruding.engine.flow.ViewFlow;
import dev.cruding.engine.gen.Page;

public class Separateur extends dev.cruding.engine.component.Component {

    private int height = 20;

    public Separateur(Page page) {
        super(page);
    }

    public Separateur(Page page, int height) {
        super(page);
        this.height = height;
    }

    public void addImport(ViewFlow flow) {
        flow.addJsImport("{Separateur" + height + "}", "waxant");

    }

    public void addOpenTag(ViewFlow flow, int level) {
        flow.addToUi(indent[level]).append("<Separateur" + height + " />");
    }

    public void addCloseTag(ViewFlow flow, int level) {

    }

}
