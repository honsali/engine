package dev.cruding.engine.composant.conteneur;

import dev.cruding.engine.composant.Composant;
import dev.cruding.engine.element.Element;
import dev.cruding.engine.flow.ViewFlow;

public class SiCondition extends Composant {

    public String condition = null;
    public String type = "siVrai";
    public boolean childInLine = false;
    public String nomVariable;

    public SiCondition(String nomVariable, Element element, String condition, String type, boolean childInLine, Composant... listeComposant) {
        super(element, listeComposant);
        this.condition = condition;
        this.type = type;
        this.childInLine = childInLine;
        this.nomVariable = nomVariable;
    }

    public SiCondition(Element element, String condition, String type, boolean childInLine, Composant... listeComposant) {
        super(element, listeComposant);
        this.condition = condition;
        this.type = type;
        this.childInLine = childInLine;
    }

    public boolean addOpenTag(ViewFlow flow, int level) {
        indent(flow, level).append("{");
        String open = childInLine ? "" : "(";
        if (type.equals("siVrai")) {
            flow.totalUi().__(condition).append(" && " + open);
        } else if (type.equals("siFaux")) {
            flow.totalUi().__("!").append(condition).append(" && (");
        } else {
            flow.totalUi().__("util." + type + "(").append(condition).append(") && " + open);
            flow.addJsImport("{ util }", "waxant");
        }
        if (nomVariable != null) {
            flow.addSelector(nomVariable);
        }
        return childInLine;
    }

    public void addCloseTag(ViewFlow flow, int level) {

        if (childInLine) {
            flow.totalUi().__("}");
        } else {
            indent(flow, level);
            flow.totalUi().__(")}");
        }
    }
}
